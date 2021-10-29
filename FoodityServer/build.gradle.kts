import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version("1.5.31")
	id("org.springframework.boot") version "2.5.6"
	id("org.jetbrains.kotlin.plugin.jpa") version("1.5.31")
	id("org.jetbrains.kotlin.plugin.spring") version("1.5.31")
}

apply(plugin = "io.spring.dependency-management")

group = "com.haxos"
version = "1.0"

repositories {
	mavenCentral()
	maven("https://repo.spring.io/milestone")
	google()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-configuration-processor:2.5.0")
	implementation("org.springframework.boot:spring-boot-starter:2.5.0")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.5.0")
	implementation("org.springframework.boot:spring-boot-starter-web:2.5.0")
	implementation("org.springframework.boot:spring-boot-starter-webflux:2.5.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.0")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.xerial:sqlite-jdbc:3.34.0")
	implementation("com.github.gwenn:sqlite-dialect:0.1.2")
	implementation("org.hibernate:hibernate-core:5.4.27.Final")
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