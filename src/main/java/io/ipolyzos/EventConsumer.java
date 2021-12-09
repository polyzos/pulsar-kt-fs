package io.ipolyzos;

import io.ipolyzos.config.AppConfig;
import io.ipolyzos.models.ClickEvent;
import io.ipolyzos.utils.ClientUtils;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.client.api.SubscriptionInitialPosition;

public class EventConsumer {

    public static void main(String[] args) throws PulsarClientException {
        PulsarClient pulsarClient = ClientUtils.initPulsarClient(AppConfig.DEV_TOKEN);

        Consumer<ClickEvent> consumer = pulsarClient
                .newConsumer(Schema.PROTOBUF(ClickEvent.class))
                .topic("events-proto")
                .subscriptionName("proto-test")
                .subscriptionInitialPosition(SubscriptionInitialPosition.Earliest)
                .subscribe();

        while (true) {
            Message<ClickEvent> msg = consumer.receive();
            System.out.println("Received msg: " + msg.getValue());
            consumer.acknowledge(msg);
        }
    }
}
