package io.ipolyzos.utils;

import io.ipolyzos.models.Event;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppUtils {
    public static List<Event.JEvent> loadStockTickerData(String inputPath) throws IOException {
        return  readData(inputPath)
                .map(AppUtils::strToStockTicker)
                .collect(Collectors.toList());
    }

    public static Stream<String> readData(String inputPath) throws IOException {
        Path path = Paths.get(inputPath);
        return Files.lines(path);
    }

    public static Event.JEvent strToStockTicker(String str) {
        String[] tokens = str.split(",");

        return Event.JEvent.newBuilder()
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
//        (
//                tokens[0],
//                tokens[1],
//                tokens[2],
//                tokens[3],
//                tokens[4],
//                tokens[5],
//                tokens[6],
//                tokens[7],
//                tokens[8]
//        );
    }

    private static Double strToDoubleParser(String str) {
        return Optional.of(Double.parseDouble(str))
                .orElse(Double.NaN);
    }
}
