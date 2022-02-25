plugins {
    kotlin("jvm") version "1.5.10"
    java
}

group = "me.giverplay"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    api("org.json:json:20211205")
    api("org.java-websocket:Java-WebSocket:1.5.2")
}
