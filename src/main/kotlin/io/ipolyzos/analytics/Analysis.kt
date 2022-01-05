package io.ipolyzos.analytics

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment

fun main() {
    val env = StreamExecutionEnvironment
        .getExecutionEnvironment()

    val tableEnvironment = StreamTableEnvironment.create(env)

    tableEnvironment.executeSql(Queries.CREATE_CLICK_EVENTS_TABLE)
    tableEnvironment.executeSql(Queries.DESCRIBE_TABLE)
//    tableEnvironment.executeSql(Queries.USER_SESSION_INTERACTIONS_PER_HOUR)
//        .print()
    tableEnvironment.executeSql("SELECT * FROM click_events").print()
}