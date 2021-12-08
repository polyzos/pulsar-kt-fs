package io.ipolyzos.schema.proto

import io.ipolyzos.models.Event.JEvent
import org.apache.pulsar.client.api.Consumer
import org.apache.pulsar.client.api.PulsarClient
import org.apache.pulsar.client.api.Schema

fun main() {
    val client = PulsarClient.builder()
        .serviceUrl("pulsar://localhost:6650")
        .build()


    val consumer: Consumer<JEvent> = client.newConsumer(Schema.PROTOBUF(JEvent::class.java))
        .topic("click-events-avro")
        .consumerName("test-consumer")
        .subscriptionName("test-subs")
        .subscribe()

    while (!consumer.hasReachedEndOfTopic()) {
        val message = consumer.receive()
        println("Received '${message.value}' - ${message.messageId}")
    }
}