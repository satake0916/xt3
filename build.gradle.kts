import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

val exposedVersion: String by project
val mysqlConnectorVersion: String by project
val kotestVersion: String by project

plugins {
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    id("org.flywaydb.flyway") version "8.2.0"
    id("io.gitlab.arturbosch.detekt") version "1.23.0"
    id("org.openapi.generator") version "7.1.0"
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.spring") version "1.8.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

kotlin {
    jvmToolchain {
        (this).languageVersion.set(JavaLanguageVersion.of(17))
        vendor = JvmVendorSpec.AMAZON
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.jetbrains.exposed:exposed-spring-boot-starter:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
    implementation("mysql:mysql-connector-java:$mysqlConnectorVersion")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-mysql")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.0")
    implementation("org.springframework.boot:spring-boot-starter-security")
    compileOnly("io.swagger.core.v3:swagger-annotations:2.2.4")
    compileOnly("io.swagger.core.v3:swagger-models:2.2.4")
    compileOnly("jakarta.annotation:jakarta.annotation-api:2.1.1")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
    testImplementation("com.ninja-squad:springmockk:4.0.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

flyway {
    url = "jdbc:mysql://localhost:3306"
    user = "local"
    password = "local"
    schemas = arrayOf("app")
}

detekt {
    source.setFrom(
            "src/main/kotlin/com/example/xt3/common",
            "src/main/kotlin/com/example/xt3/controller",
            "src/main/kotlin/com/example/xt3/domain"
    )
    config.setFrom("config/detekt/detekt.yml")
    buildUponDefaultConfig = true
}

tasks.bootBuildImage {
    builder.set("paketobuildpacks/builder-jammy-base:latest")
}

task<GenerateTask>("generateApiDoc") {
    generatorName.set("html2")
    inputSpec.set("$projectDir/openapi.yaml")
    outputDir.set("$projectDir/docs/openapi/")
}

task<GenerateTask>("generateApiServer") {
    generatorName.set("kotlin-spring")
    inputSpec.set("$projectDir/openapi.yaml")
    outputDir.set("$projectDir/src/main/kotlin/com/example/xt3/openapi/generated")
    apiPackage.set("com.example.xt3.openapi.generated.controller") // 各自のアプリケーションに合わせてパス名を変更する
    modelPackage.set("com.example.xt3.openapi.generated.model") // 各自のアプリケーションに合わせてパス名を変更する
    configOptions.set(
            mapOf(
                    "interfaceOnly" to "true",
                    "useSpringBoot3" to "true"
            )
    )
    additionalProperties.set(
            mapOf(
                    "useTags" to "true",
                    "gradleBuildFile" to "false",
                    "useSwaggerUI" to "false",
            )
    )
    typeMappings.set(
            mapOf(
                    "DateTime" to "java.time.LocalDateTime"
            )
    )
}

task<GenerateTask>("generateApiClient") {
    generatorName.set("typescript-fetch")
    inputSpec.set("$projectDir/openapi.yaml")
    outputDir.set("$projectDir/front/src/openapi/generated")
    additionalProperties.set(
            mapOf(
                    "useTags" to "true",
                    "gradleBuildFile" to "false",
                    "useSwaggerUI" to "false",
            )
    )
}
