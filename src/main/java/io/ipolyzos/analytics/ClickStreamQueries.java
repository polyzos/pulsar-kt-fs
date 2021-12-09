package io.ipolyzos.analytics;

public class ClickStreamQueries {
    public static String SHOW_DATABASES = "SHOW DATABASES";
    public static String SHOW_CATALOGS = "SHOW CATALOGS";
    public static String SHOW_TABLES = "SHOW TABLES";
    public static String DESCRIBE_TABLE = "DESCRIBE click_events";
    public static String DROP_TABLE = "DROP TABLE click_events";

    public static String SELECT_ALL_EVENTS = "SELECT * from click_events";

    public static String USER_SESSION_INTERACTIONS_PER_HOUR = "SELECT userSession, TUMBLE_START(`eventTime`, INTERVAL '1' HOUR) AS startT, TUMBLE_END(`eventTime`, INTERVAL '1' HOUR) AS endT, count(eventType) "
            + "FROM click_events "
            + "GROUP BY TUMBLE(`eventTime`, INTERVAL '1' HOUR), userSession";

    // if the user has an event_type of `cart` but no `purchase` event_type we can consider an abandoned cart
    // TODO: Need to Check FLINK SQL Collection Functions
    public static String USER_SESSION_EVENT_TYPES_COUNT = "SELECT userSession, COLLECT(eventType) as userSessionEventTypesCount, LISTAGG(eventType, ',') "
            + "FROM click_events "
            + "GROUP BY userSession";

    public static String CREATE_CLICK_EVENTS_TABLE = "CREATE TABLE click_events (\n"
            + "  eventType STRING,\n"
            + "  productId STRING,\n"
            + "  categoryId STRING,\n"
            + "  categoryCode STRING,\n"
            + "  brand STRING,\n"
            + "  price DOUBLE,\n"
            + "  userid STRING,\n"
            + "  userSession STRING,\n"
            + " `eventTime` TIMESTAMP_LTZ(3) METADATA\n,"
            + "  `key` STRING\n,"
            + "     WATERMARK FOR `eventTime` AS `eventTime` - INTERVAL '1' SECOND\n"
            + ") WITH (\n"
            + "  'connector' = 'pulsar',\n"
            + "  'topic' = 'persistent://public/default/events-json',\n"
            + "  'key.format' = 'raw',\n"
            + "  'key.fields' = 'key',\n"
            + "  'value.format' = 'json',\n"
            + "  'service-url' = 'pulsar://localhost:6650',\n"
            + "  'admin-url' = 'http://localhost:8080',\n"
            + "  'scan.startup.mode' = 'earliest' \n"
            + ")\n";
}