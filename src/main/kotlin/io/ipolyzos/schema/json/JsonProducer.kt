package io.ipolyzos.schema.json

import io.ipolyzos.models.JEventKt
import io.ipolyzos.utils.FileUtils
import org.apache.pulsar.client.api.HashingScheme
import org.apache.pulsar.client.api.Producer
import org.apache.pulsar.client.api.PulsarClient
import org.apache.pulsar.client.api.Schema
import org.apache.pulsar.client.api.schema.SchemaDefinition

fun main() {
    val events: List<JEventKt> = FileUtils
        .loadJEvents("/Users/ipolyzos/Documents/datasets/user_behavior/small/events.csv")

    fun eventSchema(): SchemaDefinition<JEventKt> = JsonSerdes.JSchemaDefinition()

    val client = PulsarClient.builder()
        .serviceUrl("pulsar://localhost:6650")
        .build()

    val producer: Producer<JEventKt> = client.newProducer(Schema.JSON<JEventKt>(eventSchema()))
        .topic("events-json")
        .producerName("jevents-producer")
        .hashingScheme(HashingScheme.Murmur3_32Hash)
        .blockIfQueueFull(true)
//        .batchingMaxMessages(2000)
//        .batchingMaxBytes(1000000)
//        .batchingMaxPublishDelay(5, TimeUnit.SECONDS)
        .create()

    events.forEach { e ->
        producer
            .newMessage()
            .key(e.userid)
            .value(e)
            .sendAsync().whenComplete { id, ex ->
                ex?.let {
                    println("Exception occured - $ex")
                } ?: println("Acked for message with id '$id'")
            }
    }
    producer.close()
    client.close()
}