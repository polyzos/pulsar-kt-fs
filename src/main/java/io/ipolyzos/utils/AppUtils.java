package io.ipolyzos.utils;

import io.ipolyzos.models.ClickEvent;
import io.ipolyzos.models.JEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppUtils {

    public static Stream<String> readData(String inputPath) throws IOException {
        Path path = Paths.get(inputPath);
        return Files.lines(path);
    }


    public static List<JEvent> loadJEvents(String inputPath) throws IOException {
        return  readData(inputPath)
                .filter(line -> !line.contains("event_time"))
                .map(AppUtils::toJEvent)
                .collect(Collectors.toList());
    }

    public static List<ClickEvent> loadProtoEvents(String inputPath) throws IOException {
        return  readData(inputPath)
                .filter(line -> !line.contains("event_time"))
                .map(AppUtils::toClickEvent)
                .collect(Collectors.toList());
    }

    public static ClickEvent toClickEvent(String str) {
        String[] tokens = str.split(",");

        return ClickEvent.newBuilder()
                .setEventTime(tokens[0])
                .setEventType(tokens[1])
                .setProductId(tokens[2])
                .setCategoryId(tokens[3])
                .setCategoryCode(tokens[4])
                .setBrand(tokens[5])
                .setPrice(tokens[6])
                .setUserId(tokens[7])
                .setUserSession(tokens[8])
                .build();
    }

    public static JEvent toJEvent(String str) {
        String[] tokens = str.split(",");

        Timestamp eventTime = Timestamp.valueOf(tokens[0].replace(" UTC", ""));

        return new JEvent(
                tokens[7],
                eventTime.getTime(),
                tokens[1],
                tokens[2],
                tokens[3],
                tokens[4],
                tokens[5],
                Double.parseDouble(tokens[6]),
                tokens[8]
        );
    }
}
