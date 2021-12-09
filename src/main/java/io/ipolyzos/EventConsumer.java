package io.ipolyzos;

import io.ipolyzos.config.AppConfig;
import io.ipolyzos.models.JEvent;
import io.ipolyzos.utils.ClientUtils;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.SubscriptionInitialPosition;
import org.apache.pulsar.client.impl.schema.JSONSchema;

public class EventConsumer {

    public static void main(String[] args) throws PulsarClientException {
        PulsarClient pulsarClient = ClientUtils.initPulsarClient(AppConfig.DEV_TOKEN);

        Consumer<JEvent> consumer = pulsarClient
                .newConsumer(JSONSchema.of(JEvent.class))
                .topic("events-json")
                .subscriptionName("proto-test")
                .subscriptionInitialPosition(SubscriptionInitialPosition.Earliest)
                .subscribe();

        while (true) {
            Message<JEvent> msg = consumer.receive();
            System.out.println("Received msg: " + msg.getValue());
            consumer.acknowledge(msg);
        }
    }
}
