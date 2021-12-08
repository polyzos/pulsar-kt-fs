package io.ipolyzos;

import io.ipolyzos.models.TaxiRecord;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Runner {
    public static void main(String[] args) throws IOException {
        AtomicInteger counter = new AtomicInteger(0);

        Files.lines(Paths.get("/Users/ipolyzos/Documents/datasets/nyc_taxi/yellow_tripdata_2021-01.csv"))
//                .map(line -> {
//                    System.out.println(counter.incrementAndGet());
//                    return Arrays.asList(line.split(","));
//                })
                .forEach(line ->{
                    List<String> strings = Arrays.asList(line.split(","));
                    if (!strings.get(0).trim().isEmpty()) {
                        counter.incrementAndGet();
                    }
                    TaxiRecord taxiRecord = new TaxiRecord(
                            strings.get(0),
                            strings.get(1),
                            strings.get(2),
                            strings.get(3),
                            strings.get(4),
                            strings.get(5),
                            strings.get(6),
                            strings.get(7),
                            strings.get(8),
                            strings.get(9),
                            strings.get(10),
                            strings.get(11),
                            strings.get(12),
                            strings.get(13),
                            strings.get(14),
                            strings.get(15),
                            strings.get(16),
                            strings.get(17)
                    );
                    System.out.println(taxiRecord);
                });

        //1369766
        // 1271414
        System.out.println("Done");
        System.out.println(counter.get());
//        try(BufferedReader br = new BufferedReader(new FileReader("/Users/ipolyzos/Documents/datasets/nyc_taxi/yellow_tripdata_2021-01.csv"))) {
//            for(String line; (line = br.readLine()) != null; ) {
//                System.out.println(counter.incrementAndGet());
//                System.out.println(Arrays.asList(line.split(",")));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try (Scanner sc = new Scanner(new File("/Users/ipolyzos/Documents/datasets/nyc_taxi/yellow_tripdata_2021-01.csv"), "UTF-8")) {
//            while (sc.hasNextLine()) {
//                String line = sc.nextLine();
//                System.out.println(counter.incrementAndGet());
//                System.out.println(Arrays.asList(line.split(",")));
//            }
//            // note that Scanner suppresses exceptions
//            if (sc.ioException() != null) {
//                throw sc.ioException();
//            }
//        }
    }
}
