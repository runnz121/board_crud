plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("plugin.jpa") version "1.9.25"
}

group = "kuku"
version = "0.0.1-SNAPSHOT"

// 모든 프로젝트에서 적용
allprojects {

	repositories {
		mavenCentral()
	}

	tasks.withType<JavaCompile> {
		sourceCompatibility = "21"
		targetCompatibility = "21"
	}

//	plugins.withType<JavaPlugin> {
//		extensions.configure<JavaPluginExtension> {
//			toolchain {
//				languageVersion.set(JavaLanguageVersion.of(21))
//			}
//		}
//	}

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter-data-jpa")
		implementation("org.springframework.boot:spring-boot-starter-web")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.springframework.kafka:spring-kafka")
		compileOnly("org.projectlombok:lombok")
		runtimeOnly("com.mysql:mysql-connector-j")
		annotationProcessor("org.projectlombok:lombok")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
		testImplementation("org.springframework.kafka:spring-kafka-test")
		testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	}

	kotlin {
		compilerOptions {
			freeCompilerArgs.addAll("-Xjsr305=strict")
		}
	}

	allOpen {
		annotation("jakarta.persistence.Entity")
		annotation("jakarta.persistence.MappedSuperclass")
		annotation("jakarta.persistence.Embeddable")
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}

