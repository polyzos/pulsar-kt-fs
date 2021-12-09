package io.ipolyzos.analytics;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

public class ClickStreamAnalysis {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment
                .getExecutionEnvironment();

        StreamTableEnvironment tableEnvironment = StreamTableEnvironment.create(env);

        tableEnvironment.executeSql(ClickStreamQueries.CREATE_CLICK_EVENTS_TABLE);
        tableEnvironment.executeSql(ClickStreamQueries.DESCRIBE_TABLE);
        tableEnvironment.executeSql(ClickStreamQueries.USER_SESSION_INTERACTIONS_PER_HOUR)
                .print();
    }
}