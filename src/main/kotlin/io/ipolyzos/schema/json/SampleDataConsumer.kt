package io.ipolyzos.schema.json

import org.apache.pulsar.client.api.PulsarClient
import org.apache.pulsar.client.api.Schema
import org.apache.pulsar.client.api.SubscriptionInitialPosition

fun main() = SampleDataConsumer.runConsumer()

object SampleDataConsumer {
    fun runConsumer() {
        val client = PulsarClient.builder()
            .serviceUrl("pulsar://localhost:6650")
            .build()


        val consumer = client.newConsumer(Schema.JSON<PageEvent>(SampleDataProducer.pageEventSchema()))
            .topic("page_events")
            .consumerName("test-consumer")
            .subscriptionName("test-sub1s")
            .subscriptionInitialPosition(SubscriptionInitialPosition.Earliest)
            .subscribe()

        while (true) {
            val message = consumer.receive()
            println("Received '${message.value}' - ${message.messageId}")
        }
    }
}