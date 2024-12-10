plugins {
    `java-library`
}

group = "com.eternalcode"
version = "1.3.3"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.compileJava {
    options.compilerArgs = listOf("-Xlint:deprecation", "-parameters")
    options.encoding = "UTF-8"
    options.release = 17
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
