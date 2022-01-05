package io.ipolyzos.schema.json

import io.ipolyzos.models.ClickEvent
import io.ipolyzos.models.JEventKt
import org.apache.pulsar.client.api.PulsarClient
import org.apache.pulsar.client.api.Schema
import org.apache.pulsar.client.api.schema.SchemaDefinition

fun main() {
    val client = PulsarClient.builder()
        .serviceUrl("pulsar://localhost:6650")
        .build()

    fun eventSchema(): SchemaDefinition<JEventKt> = JsonSerdes.JSchemaDefinition()

    val consumer = client.newConsumer(Schema.JSON<ClickEvent>(eventSchema()))
        .topic("events-json")
        .consumerName("test-consumer")
        .subscriptionName("test-subs")
        .subscribe()

    while (!consumer.hasReachedEndOfTopic()) {
        val message = consumer.receive()
        println("Received '${message.value}' - ${message.messageId}")
    }
}