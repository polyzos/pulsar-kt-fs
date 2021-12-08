package io.ipolyzos;

import io.ipolyzos.config.AppConfig;
import io.ipolyzos.models.Event;
import io.ipolyzos.utils.AppUtils;
import io.ipolyzos.utils.ClientUtils;
import java.io.IOException;
import java.util.List;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.Schema;

public class EventProducer {
    public static void main(String[] args) throws IOException {
        List<Event.JEvent> events =
                AppUtils.loadStockTickerData("/Users/ipolyzos/Documents/datasets/user_behavior/small/events.csv");

        PulsarClient pulsarClient = ClientUtils.initPulsarClient(AppConfig.DEV_TOKEN);

        Producer<Event.JEvent> producer = pulsarClient.newProducer(Schema.PROTOBUF(Event.JEvent.class))
                .producerName("jevent-producer")
                .topic("events-proto")
                .blockIfQueueFull(true)
                .create();


        for (Event.JEvent event : events) {
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