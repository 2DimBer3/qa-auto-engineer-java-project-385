plugins {
    java
    checkstyle
    id("org.sonarqube") version "7.3.0.8198"
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.40.0")
    testImplementation("org.aeonbits.owner:owner:1.0.12")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
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

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
    systemProperties = System.getProperties().toMap()
        .filterKeys { it is String }
        .mapKeys { it.key as String }
}