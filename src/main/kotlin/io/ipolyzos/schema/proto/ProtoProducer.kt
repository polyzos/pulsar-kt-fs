package io.ipolyzos.schema.proto

import io.ipolyzos.models.Event.JEvent
import io.ipolyzos.utils.FileUtils
import org.apache.pulsar.client.api.HashingScheme
import org.apache.pulsar.client.api.Producer
import org.apache.pulsar.client.api.PulsarClient
import org.apache.pulsar.client.api.Schema


fun main() {
    val events = FileUtils
        .readFileJ("/Users/ipolyzos/Documents/datasets/user_behavior/small/events.csv")


    events.forEach(::println)

    val client = PulsarClient.builder()
        .serviceUrl("pulsar://localhost:6650")
        .build()

    val producer: Producer<JEvent> = client.newProducer<JEvent>(Schema.PROTOBUF(JEvent::class.java))
        .topic("click-events-avro")
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