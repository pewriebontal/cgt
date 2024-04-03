plugins {
    id("java")
    application
    id("org.graalvm.buildtools.native") version "0.10.1"

}

group = "net.bontal"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass = "net.bontal.cgt.CgtInterface" 
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
    args("--console=plain")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register("generateJavadoc", Javadoc::class) {
    source = fileTree("src/main/java/net/bontal/cgt")
    destinationDir = file("doc")
}

graalvmNative {
    binaries {
        named("main") {
            imageName.set("cgt-calc")
            mainClass.set("net.bontal.cgt.CgtInterface")
            buildArgs.add("-O4")
        }
        named("test") {
            buildArgs.add("-O0")
        }
    }
    binaries.all {
        buildArgs.add("--verbose")
    }
}