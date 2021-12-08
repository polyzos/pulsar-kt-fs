plugins {
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.serialization") version "1.6.0"
    java
}

group = "io.ipolyzos"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

//    implementation("org.apache.pulsar:pulsar-client:2.8.1")
    implementation("org.apache.pulsar:pulsar-client-original:2.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
    implementation("com.github.avro-kotlin.avro4k:avro4k-core:1.5.0")
    implementation("com.google.protobuf:protobuf-java:3.8.0")
}