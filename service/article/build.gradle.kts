plugins {
    kotlin("jvm") version "1.8.21"
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.mysql:mysql-connector-j")
//    implementation(project(":common:snowflake"))
//    implementation(project(":common:outbox-message-relay"))
//    implementation(project(":common:event"))
}
