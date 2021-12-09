package io.ipolyzos.schema.proto

import io.ipolyzos.models.ClickEvent
import org.apache.pulsar.client.api.Consumer
import org.apache.pulsar.client.api.PulsarClient
import org.apache.pulsar.client.api.Schema

fun main() {
    val client = PulsarClient.builder()
        .serviceUrl("pulsar://localhost:6650")
        .build()


    val consumer: Consumer<ClickEvent> = client.newConsumer(Schema.PROTOBUF(ClickEvent::class.java))
        .topic("click-events-proto")
        .consumerName("test-consumer")
        .subscriptionName("test-subs")
        .subscribe()

    while (!consumer.hasReachedEndOfTopic()) {
        val message = consumer.receive()
        println("Received '${message.value}' - ${message.messageId}")
    }
}