plugins {
    java
    id("org.openjfx.javafxplugin") version "0.0.10"
}



group = "com.github.c3002"
version = "1.0"

repositories {
    mavenCentral()
}



dependencies {
    implementation("org.jetbrains:annotations:20.1.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

javafx {
    version = "17"
    modules("javafx.controls", "javafx.fxml")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

java {
    // this enables Java Modularity in Gradle (version 6.7 and above)
    modularity.inferModulePath.set(true)
}
