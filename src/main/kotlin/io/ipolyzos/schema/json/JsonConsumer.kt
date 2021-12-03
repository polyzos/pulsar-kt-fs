package io.ipolyzos.schema.json

import io.ipolyzos.models.ClickEvent
import io.ipolyzos.schema.json.JsonSerdes.JSchemaDefinition
import org.apache.pulsar.client.api.PulsarClient
import org.apache.pulsar.client.api.Schema

fun main() {
    val client = PulsarClient.builder()
        .serviceUrl("pulsar://localhost:6650")
        .build()


    val consumer = client.newConsumer(Schema.JSON<ClickEvent>(JSchemaDefinition()))
        .topic("click-events-json")
        .consumerName("test-consumer")
        .subscriptionName("test-subs")
        .subscribe()

    while (!consumer.hasReachedEndOfTopic()) {
        val message = consumer.receive()
        println("Received '${message.value}' - ${message.messageId}")
    }
}