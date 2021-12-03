package io.ipolyzos.schema.json

import io.ipolyzos.models.ClickEvent
import io.ipolyzos.schema.json.JsonSerdes.JSchemaDefinition
import io.ipolyzos.utils.FileUtils
import org.apache.pulsar.client.api.HashingScheme
import org.apache.pulsar.client.api.Producer
import org.apache.pulsar.client.api.PulsarClient
import org.apache.pulsar.client.api.Schema

fun main() {
    val events = FileUtils
        .readFile("/Users/ipolyzos/Documents/datasets/user_behavior/small/events.csv")


    val client = PulsarClient.builder()
        .serviceUrl("pulsar://localhost:6650")
        .build()

    val producer: Producer<ClickEvent> = client.newProducer(Schema.JSON<ClickEvent>(JSchemaDefinition()))
        .topic("click-events-json")
        .producerName("test-producer")
        .hashingScheme(HashingScheme.Murmur3_32Hash)
        .blockIfQueueFull(true)
        .create()

    events.forEach { e ->
        println("Sending " + e)
        val messageId = producer
            .newMessage()
            .key(e.userId.toString())
            .value(e)
            .send()
        println("Send $messageId")
    }
    producer.close()
    client.close()
}