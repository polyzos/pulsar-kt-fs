package io.ipolyzos.schema.json

import com.github.avrokotlin.avro4k.Avro
import com.github.avrokotlin.avro4k.io.AvroEncodeFormat
import io.ipolyzos.models.ClickEvent
import io.ipolyzos.models.ClickEventKt
import io.ipolyzos.models.JEventKt
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import org.apache.avro.file.SeekableByteArrayInput
import org.apache.avro.generic.GenericDatumReader
import org.apache.avro.generic.GenericRecord
import org.apache.avro.io.DecoderFactory
import org.apache.pulsar.client.api.schema.SchemaDefinition
import org.apache.pulsar.client.api.schema.SchemaReader
import org.apache.pulsar.client.api.schema.SchemaWriter
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.nio.charset.Charset


object JsonSerdes {
    @OptIn(InternalSerializationApi::class)
    inline fun <reified T: Any> JSchemaDefinition(): SchemaDefinition<T> =
        SchemaDefinition.builder<T>()
            .withPojo(T::class.java)
            .withSchemaReader(GenericSchemaReader(T::class.serializer()))
            .withSchemaWriter(GenericSchemaWriter(T::class.serializer()))
            .withSupportSchemaVersioning(true)
            .build()

    // custom Pulsar SchemaReader
    class GenericSchemaReader<T>(private val serializer: KSerializer<T>): SchemaReader<T> {
        override fun read(bytes: ByteArray?, offset: Int, length: Int): T {
            return Json.decodeFromString(serializer, String(bytes!!, offset, length))
        }

        override fun read(inputStream: InputStream?): T {
            return Json.decodeFromString(serializer, String(inputStream?.readBytes()!!, Charset.defaultCharset()))
        }
    }

    // custom Pulsar SchemaWriter
    class GenericSchemaWriter<T>(private val serializer: KSerializer<T>) : SchemaWriter<T> {
        override fun write(message: T): ByteArray {
            return Json.encodeToString(serializer, message).toByteArray()
        }
    }
}

object AvroSerdes {
    @OptIn(InternalSerializationApi::class)
    inline fun <reified T: Any> AvroSchemaDefinition(): SchemaDefinition<T> =
        SchemaDefinition.builder<T>()
            .withJsonDef(Avro.default.schema(T::class.serializer()).toString())
            .withSchemaReader(AvroSchemaReader(T::class.serializer()))
            .withSchemaWriter(AvroSchemaWriter(T::class.serializer()))
            .withSupportSchemaVersioning(true)
            .build()


    class AvroSchemaReader<T>(val kSerializer: KSerializer<T>) : SchemaReader<T> {
        override fun read(bytes: ByteArray, offset: Int, length: Int): T {
            return read(bytes.inputStream(offset, length))
        }

        override fun read(inputStream: InputStream): T {
            return readBinary(inputStream.readBytes(), kSerializer)
        }
    }

    class AvroSchemaWriter<T>(val kSerializer: KSerializer<T>) : SchemaWriter<T> {
        override fun write(message: T): ByteArray {
            return writeBinary(message, kSerializer)
        }
    }

    // Convert T instance to Avro schemaless binary format
    private fun <T> writeBinary(t: T, serializer: KSerializer<T>): ByteArray {
        val out = ByteArrayOutputStream()
        Avro.default.openOutputStream(serializer) {
            encodeFormat = AvroEncodeFormat.Binary
            schema = Avro.default.schema(serializer)
        }.to(out).write(t).close()

        return out.toByteArray()
    }

    // Convert Avro schemaless byte array to T instance
    private fun <T> readBinary(bytes: ByteArray, serializer: KSerializer<T>): T {
        val datumReader = GenericDatumReader<GenericRecord>(Avro.default.schema(serializer))
        val decoder = DecoderFactory.get().binaryDecoder(SeekableByteArrayInput(bytes), null)

        return Avro.default.fromRecord(serializer, datumReader.read(null, decoder))
    }
}