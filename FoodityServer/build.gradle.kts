import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.0-M1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.jetbrains.kotlin.plugin.noarg") version "1.4.21"
	kotlin("jvm") version "1.4.21-2"
	kotlin("plugin.spring") version "1.4.21-2"
	kotlin("kapt") version "1.5.0"

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
	google()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-configuration-processor")
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web:2.4.0")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.xerial:sqlite-jdbc:3.34.0")
	implementation("com.github.gwenn:sqlite-dialect:0.1.2")
	implementation("org.hibernate:hibernate-core:5.4.27.Final")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework.boot:spring-boot-starter-webflux:2.4.2")
	implementation("org.keycloak:keycloak-spring-boot-starter:11.0.3")
	implementation("org.keycloak:keycloak-admin-client:11.0.3")
	implementation("com.squareup.okhttp3:okhttp:4.9.1")
	implementation("com.google.code.gson:gson:2.8.5")
	implementation("org.danilopianini:gson-extras:0.2.1")
}

configure<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension> {
	imports {
		mavenBom("org.keycloak.bom:keycloak-adapter-bom:11.0.3")
	}
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