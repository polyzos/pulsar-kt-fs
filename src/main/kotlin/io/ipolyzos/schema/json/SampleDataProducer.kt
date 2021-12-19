package io.ipolyzos.schema.json

import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import org.apache.pulsar.client.api.HashingScheme
import org.apache.pulsar.client.api.Producer
import org.apache.pulsar.client.api.PulsarClient
import org.apache.pulsar.client.api.Schema
import org.apache.pulsar.client.api.schema.SchemaDefinition
import org.apache.pulsar.client.api.schema.SchemaReader
import org.apache.pulsar.client.api.schema.SchemaWriter
import java.io.InputStream
import java.nio.charset.Charset
import java.sql.Timestamp

/**
 * pulsar-admin namespaces get-retention public/default
 * */
fun main() = SampleDataProducer.runProducer()

@Serializable
data class PageEvent(
    val userName: String,
    val clickTime: Long,
    val url: String
    )

object SampleDataProducer {

    fun pageEventSchema(): SchemaDefinition<PageEvent> =
        SchemaDefinition.builder<PageEvent>()
            .withPojo(PageEvent::class.java)
            .withSchemaReader(GenericSchemaReader(PageEvent.serializer()))
            .withSchemaWriter(GenericSchemaWriter(PageEvent.serializer()))
            .withSupportSchemaVersioning(true)
            .build()

    fun runProducer() {
        val client = PulsarClient.builder()
            .serviceUrl("pulsar://localhost:6650")
            .build()

//        val pageEvents = listOf(
//            Triple("Mary", "2021-12-13 12:00:00", "./home"),
//            Triple("Bob", "2021-12-13 12:00:00", "./cart"),
//            Triple("Mary", "2021-12-13 12:00:05", "./prod?id=1"),
//            Triple("Liz", "2021-12-13 12:01:00", "./home"),
//            Triple("Bob", "2021-12-13 12:01:30", "./prod?id=3"),
//            Triple("Mary", "2021-12-13 12:01:45", "./prod?id=7")
//        ).map {
//            val clickTime: Long = Result.runCatching {
//                Timestamp.valueOf(it.second)
//            }.getOrElse { Timestamp(System.currentTimeMillis()) }.time
//            PageEvent(it.first, clickTime, it.third)
//        }

        val pageEvents = listOf(
            Triple("Mary", "2021-12-13 12:00:00", "./home"),
            Triple("Bob", "2021-12-13 12:00:00", "./cart"),
            Triple("Mary", "2021-12-13 12:02:00", "./prod?id=2"),
            Triple("Mary", "2021-12-13 12:55:00", "./home"),

            Triple("Bob", "2021-12-13 13:01:00", "./prod?id=4"),
            Triple("Liz", "2021-12-13 13:30:45", "./cart"),
            Triple("Liz", "2021-12-13 13:59:45", "./home"),

            Triple("Mary", "2021-12-13 14:00:00", "./prod?id=1"),
            Triple("Liz", "2021-12-13 14:02:00", "./prod?id=8"),
            Triple("Bob", "2021-12-13 14:30:00", "./prod?id=7"),
            Triple("Bob", "2021-12-13 14:40:00", "./home"),
        ).map {
            val clickTime: Long = Result.runCatching {
                Timestamp.valueOf(it.second)
            }.getOrElse { Timestamp(System.currentTimeMillis()) }.time
            PageEvent(it.first, clickTime, it.third)
        }

        val producer: Producer<PageEvent> = client.newProducer(Schema.JSON<PageEvent>(pageEventSchema()))
            .topic("page_events")
            .producerName("page-events-producer")
            .hashingScheme(HashingScheme.Murmur3_32Hash)
            .blockIfQueueFull(true)
            .create()

        pageEvents.forEach { e ->
            producer
                .newMessage()
                .key(e.userName)
                .value(e)
                .eventTime(e.clickTime)
                .sendAsync().whenComplete { id, ex ->
                    ex?.let {
                        println("Exception occured - $ex")
                    } ?: println("Acked for message with id '$id'")
                }
        }
    }

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