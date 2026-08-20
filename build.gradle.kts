plugins {
    java
    checkstyle
    id("org.sonarqube") version "7.3.0.8198"
    id("io.qameta.allure") version "4.1.0"
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.40.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("org.aeonbits.owner:owner:1.0.12")

    testImplementation("org.slf4j:slf4j-api:2.0.9")
    testImplementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.22.0")
    testImplementation("org.apache.logging.log4j:log4j-core:2.22.0")

    testImplementation("io.qameta.allure:allure-junit5:2.29.1")
    testImplementation("org.aspectj:aspectjweaver:1.9.25")
}

sonar {
    properties {
        property("sonar.projectKey", "2DimBer3_qa-auto-engineer-java-project-385")
        property("sonar.organization", "2dimber3")
        property("sonar.sources", "src/test/java")
        property("sonar.tests", "")
        property("sonar.java.binaries", "build/classes/java/test")
    }
}

allure {
    version.set("2.28.0")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
    systemProperties = (System.getProperties().toMap()
        .filterKeys { it is String }
        .mapKeys { it.key as String })
    systemProperty("log4j2.configurationFile", "src/test/resources/config/log4j2.properties")
    systemProperty("allure.results.directory", "build/allure-results")
}