package io.ipolyzos.analytics

object Queries {
    var SHOW_DATABASES  =   "SHOW DATABASES"
    var SHOW_CATALOGS   =   "SHOW CATALOGS"
    var SHOW_TABLES     =   "SHOW TABLES"
    var DESCRIBE_TABLE  =   "DESCRIBE click_events"
    var DROP_TABLE      =   "DROP TABLE click_events"

    var SELECT_ALL_EVENTS = "SELECT * from click_events"

    var USER_SESSION_INTERACTIONS_PER_HOUR = """
        SELECT userSession, TUMBLE_START(`eventTime`, INTERVAL '1' HOUR) AS startT, TUMBLE_END(`eventTime`, INTERVAL '1' HOUR) AS endT, count(eventType)
        FROM click_events 
        GROUP BY TUMBLE(`eventTime`, INTERVAL '1' HOUR), userSession
        """.trimIndent()

    // if the user has an event_type of `cart` but no `purchase` event_type we can consider an abandoned cart
    // TODO: Need to Check FLINK SQL Collection Functions
    var USER_SESSION_EVENT_TYPES_COUNT = """
        SELECT userSession, COLLECT(eventType) as userSessionEventTypesCount, LISTAGG(eventType, ',') 
        FROM click_events 
        GROUP BY userSession
    """.trimIndent()

    var CREATE_CLICK_EVENTS_TABLE = """
        CREATE TABLE click_events (
                eventType STRING,
                productId STRING,
                categoryId STRING,
                categoryCode STRING,
                brand STRING,
                price DOUBLE,
                userid STRING,
                userSession STRING,
                `eventTime` TIMESTAMP_LTZ(3) METADATA,
                `key` STRING,
                   WATERMARK FOR `eventTime` AS `eventTime` - INTERVAL '1' SECOND
            ) WITH (
                'connector' = 'pulsar',
                'topic' = 'persistent://public/default/events-json',
                'key.format' = 'raw',
                'key.fields' = 'key',
                'value.format' = 'json',
                'service-url' = 'pulsar://localhost:6650',
                'admin-url' = 'http://localhost:8080',
                'scan.startup.mode' = 'earliest' 
            )
    """.trimIndent()
}