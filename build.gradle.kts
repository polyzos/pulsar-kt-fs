plugins {
    kotlin("jvm") version "1.5.10"
    kotlin("plugin.serialization") version "1.5.10"
    java

//    id("com.google.protobuf") version "0.8.18"
}

group = "io.ipolyzos"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("org.apache.pulsar:pulsar-client:2.9.1")
    implementation("org.apache.pulsar:pulsar-client-original:2.9.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
    implementation("com.github.avro-kotlin.avro4k:avro4k-core:1.5.0")

    implementation("com.google.protobuf:protobuf-kotlin:3.17.3")

    // Flink Dependencies
    implementation("io.streamnative.connectors:pulsar-flink-sql-connector_2.12:1.13.1.1")

    implementation("org.apache.flink:flink-streaming-java_2.12:1.13.1") // compileOnly
    implementation("org.apache.flink:flink-clients_2.12:1.13.1")
    implementation("org.apache.flink:flink-table-api-java-bridge_2.12:1.13.1")
    implementation("org.apache.flink:flink-table-planner-blink_2.12:1.13.1")

}


//protobuf {
//    this.protobuf.generatedFilesBaseDir = "$projectDir/src/"
//}