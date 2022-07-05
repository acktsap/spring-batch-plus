plugins {
    id("spring.batch.plus.java-library-conventions")
    id("spring.batch.plus.kotlin-conventions")
    id("spring.batch.plus.maven-publish-conventions")
    id("spring.batch.plus.spring-conventions")
}

dependencies {
    compileOnly("org.springframework.batch:spring-batch-core")
    implementation("org.slf4j:slf4j-api")

    testImplementation("org.springframework.batch:spring-batch-core")
    testRuntimeOnly("org.apache.logging.log4j:log4j-slf4j-impl")
}