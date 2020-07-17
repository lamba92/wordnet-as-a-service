import com.github.lamba92.gradle.utils.TRAVIS_TAG

buildscript {
    repositories {
        google()
        jcenter()
        maven("https://dl.bintray.com/lamba92/com.github.lamba92")
    }
    dependencies {
        classpath("com.github.lamba92", "lamba-gradle-utils", "1.0.6")
    }
}

plugins {
    java
    application
}

group = "com.github.lamba92"
version = TRAVIS_TAG ?: "1.0.0"

repositories {
    jcenter()
    mavenCentral()
}

application {
    mainClassName = "com.github.jacopofar.wordnetservice.Server"
}

dependencies {
    implementation("org.json", "json", "20090211")
    implementation("com.sparkjava", "spark-core", "2.7.2")
    implementation("com.fasterxml.jackson.core", "jackson-databind", "2.10.0.pr1")
    implementation("net.sf.extjwnl", "extjwnl", "1.8.0")
    implementation("net.sf.extjwnl", "extjwnl-data-wn31", "1.2")
    implementation("com.mashape.unirest", "unirest-java", "1.4.9")
    implementation("org.atteo", "evo-inflector", "1.2")
}

val installDist: Sync by tasks

task<Exec>("buildDockerImages") {
    dependsOn(installDist)
    group = "docker"
    commandLine(
        "docker",
        "buildx",
        "build",
        "-t",
        "lamba92/wordnet-as-a-service:nightly",
        "--platform=linux/amd64,linux/arm64,linux/arm",
        ".",
        "--push"
    )
}

task<Exec>("publishDockerImages") {
    dependsOn(installDist)
    group = "docker"
    commandLine(
        "docker",
        "buildx",
        "build",
        "-t",
        "lamba92/wordnet-as-a-service:$version",
        "--platform=linux/amd64,linux/arm64,linux/arm",
        ".",
        "--push"
    )
}
