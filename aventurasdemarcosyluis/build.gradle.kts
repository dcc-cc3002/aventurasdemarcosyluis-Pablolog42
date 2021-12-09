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
    implementation(group = "org.jetbrains", name = "annotations", version = "19.0.0")
    implementation(group = "org.openjfx", name = "javafx-controls", version = "17")
    implementation("junit:junit:4.13.1")
    implementation("org.junit.jupiter:junit-jupiter:5.7.0")
    implementation("org.jetbrains:annotations:20.1.0")
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
