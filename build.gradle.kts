import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES
import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel
import name.remal.gradle_plugins.sonarlint.SonarLintExtension

plugins {
    idea
    id("io.spring.dependency-management")
    id("org.springframework.boot") apply false  // apply false - записали, но не применяем
    id("name.remal.sonarlint") apply false
    id("com.diffplug.spotless") apply false
}
//описание плагина IntelliJ IDEA
idea {
    project {
        languageLevel = IdeaLanguageLevel(17) // уровень Java
    }
    module {
        isDownloadJavadoc = true // Скачивать JavaDoc
        isDownloadSources = true // Скачивать исходники
    }
}
// Общие настройки для всех проектов
allprojects {
    group = "ru.otus"

    repositories { // репозитории, откуда брать зависимости
        mavenLocal()
        mavenCentral()
    }

    val testcontainersBom: String by project
    val protobufBom: String by project
    val guava: String by project

    apply(plugin = "io.spring.dependency-management")
    dependencyManagement {
        dependencies {
            imports {
                mavenBom(BOM_COORDINATES)
                mavenBom("org.testcontainers:testcontainers-bom:$testcontainersBom")
                mavenBom("com.google.protobuf:protobuf-bom:$protobufBom")
            }
            dependency("com.google.guava:guava:$guava")
        }
    }
//    запрещает автоматич разрешение конфликтов версий зависимостей
    configurations.all {
        resolutionStrategy {
            failOnVersionConflict()
//            принудительно указываем версию для конфликтующих зависимостей
            force("com.google.guava:guava:32.1.3-jre")
            force("org.sonarsource.analyzer-commons:sonar-analyzer-commons:2.8.0.2699")
            force("org.sonarsource.analyzer-commons:sonar-xml-parsing:2.8.0.2699")
            force("org.sonarsource.sslr:sslr-core:1.24.0.633")
            force("org.sonarsource.analyzer-commons:sonar-analyzer-recognizers:2.8.0.2699")
            force("com.google.code.findbugs:jsr305:3.0.2")
            force("commons-io:commons-io:2.15.1")
        }
    }
// Настройки для дочерних проектов
    subprojects {
        plugins.apply(JavaPlugin::class.java)
        extensions.configure<JavaPluginExtension> {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        tasks.withType<JavaCompile> {
            options.encoding = "UTF-8"
            options.compilerArgs.addAll(listOf("-parameters", "-Xlint:all,-serial,-processing"))
            dependsOn("spotlessApply")
        }

//        форматирование кода
        apply<com.diffplug.gradle.spotless.SpotlessPlugin>()
        configure<com.diffplug.gradle.spotless.SpotlessExtension> {
            java {
                palantirJavaFormat("2.39.0")
            }
        }
    }

    tasks {
        val managedVersions by registering {
            doLast {
                project.extensions.getByType<DependencyManagementExtension>()
                    .managedVersions
                    .toSortedMap()
                    .map { "${it.key}:${it.value}" }
                    .forEach(::println)
            }
        }
    }
}
