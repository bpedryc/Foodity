import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.0-M1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.jetbrains.kotlin.plugin.noarg") version "1.4.21"
	kotlin("jvm") version "1.4.21-2"
	kotlin("plugin.spring") version "1.4.21-2"

}

noArg {
	annotation("javax.persistence.Entity")
}

group = "com.haxos"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web:2.4.0")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.xerial:sqlite-jdbc:3.34.0")
	implementation("com.github.gwenn:sqlite-dialect:0.1.2")
	implementation("org.hibernate:hibernate-core:5.4.27.Final")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	annotationProcessor("android.arch.persistence.room:compiler:1.1.1")
	implementation("org.springframework.boot:spring-boot-starter-webflux:2.4.2")
	implementation("org.keycloak:keycloak-spring-boot-starter:11.0.3")
	implementation("org.keycloak:keycloak-admin-client:11.0.3")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
