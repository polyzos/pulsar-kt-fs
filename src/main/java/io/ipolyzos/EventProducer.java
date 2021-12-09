package io.ipolyzos;

import io.ipolyzos.config.AppConfig;
import io.ipolyzos.models.ClickEvent;
import io.ipolyzos.models.JEvent;
import io.ipolyzos.utils.AppUtils;
import io.ipolyzos.utils.ClientUtils;
import java.io.IOException;
import java.util.List;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.client.impl.schema.JSONSchema;

public class EventProducer {
    public static void main(String[] args) throws IOException {
        List<JEvent> events =
                AppUtils.loadJEvents("/Users/ipolyzos/Documents/datasets/user_behavior/small/events.csv");

        PulsarClient pulsarClient = ClientUtils.initPulsarClient(AppConfig.DEV_TOKEN);

        Producer<JEvent> producer = pulsarClient.newProducer(JSONSchema.of(JEvent.class))
                .producerName("event-producer")
                .topic("events-json")
                .blockIfQueueFull(true)
                .create();


        for (JEvent event : events) {
            System.out.println(event);
            producer.newMessage()
                    .value(event)
                    .eventTime(System.currentTimeMillis())
                    .sendAsync();
        }
        producer.close();
        pulsarClient.close();
    }
}
