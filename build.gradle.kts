plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("com.codeborne:selenide:6.19.0")
    testImplementation("org.testng:testng:7.8.0")
}

tasks.test {
    useTestNG()
}