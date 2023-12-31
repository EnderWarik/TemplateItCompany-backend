val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val postgres_version: String by project
val flyway_core_version: String by project
val HikariCP_version: String by project
val h2_version: String by project
val ktorm_version: String by project
val koin_version: String by project
plugins {
    kotlin("jvm") version "1.8.22"
    id("io.ktor.plugin") version "2.3.2"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.22"
    id("org.flywaydb.flyway") version "9.4.0"

}

group = "ru.itcompany"
version = "0.0.1"
application {
    mainClass.set("ru.itcompany.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-server-auth:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jwt:$ktor_version")
    implementation("io.ktor:ktor-server-call-logging-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-jackson:$ktor_version")
    implementation("org.ktorm:ktorm-jackson:$ktorm_version")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.4")
    implementation ("com.fasterxml.jackson.datatype:jackson-datatype-jdk8")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-server-websockets-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-cio-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("org.postgresql:postgresql:$postgres_version")
    implementation("com.zaxxer:HikariCP:$HikariCP_version")
    implementation("org.flywaydb:flyway-core:$flyway_core_version")
    implementation("org.ktorm:ktorm-core:${ktorm_version}")
    implementation("org.ktorm:ktorm-support-postgresql:${ktorm_version}")

    implementation("org.mindrot:jbcrypt:0.4")
    implementation("io.ktor:ktor-server-host-common-jvm:2.3.2")
    implementation("io.ktor:ktor-server-status-pages-jvm:2.3.2")


    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("io.insert-koin:koin-core:$koin_version")
    implementation("io.ktor:ktor-server-status-pages:$ktor_version")

    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
    testImplementation ("io.mockk:mockk:1.12.4")


    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
//    implementation("io.ktor:ktor-server-core:$ktor_version")
//    implementation("io.ktor:ktor-server-netty:$ktor_version")
//    implementation("ch.qos.logback:logback-classic:$logback_version")
//    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")
//    testImplementation("org.jetbrains.kotlin:kotlin-test")
//    testImplementation(kotlin("test"))
//    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

flyway {
    url = System.getenv("DB_URL")
    user = System.getenv("DB_USER")
    password = System.getenv("DB_PASSWORD")
    baselineOnMigrate = true
    locations = arrayOf("filesystem: resources / db / migration")
    schemas = arrayOf("public")
}

tasks.test {
    useJUnitPlatform()
}