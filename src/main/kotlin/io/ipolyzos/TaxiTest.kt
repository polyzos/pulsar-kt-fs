package io.ipolyzos

import java.io.File

fun main() {
    File("/Users/ipolyzos/Documents/datasets/nyc_taxi/yellow_tripdata_2021-01.csv")
        .useLines { lines ->
            lines
                .drop(1)
                .forEach(::println)
    }
}