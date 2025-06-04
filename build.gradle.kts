plugins {
    id("org.openrewrite.build.recipe-library") version "latest.release"
    id("org.openrewrite.build.moderne-source-available-license") version "latest.release"
}

group = "com.almirex"
description = "Rewrite C# recipes based on Roslyn transformations."

dependencies {
    implementation(platform("org.openrewrite.recipe:rewrite-recipe-bom:latest.release"))

    implementation("org.openrewrite:rewrite-java")
    implementation("org.openrewrite.recipe:rewrite-java-dependencies")
    implementation("org.openrewrite:rewrite-yaml")
    implementation("org.openrewrite.meta:rewrite-analysis")
    implementation("org.assertj:assertj-core:latest.release")
    runtimeOnly("org.openrewrite:rewrite-java-17")

    testImplementation("org.openrewrite:rewrite-test")
}

publishing {
    repositories {
        maven {
            name = "moderne"
            url = uri("https://artifactory.moderne.ninja/artifactory/moderne-recipe/")
            credentials {
                username = project.findProperty("moderne.artifactory.username") as String? ?: System.getenv("MODERNE_ARTIFACTORY_USERNAME")
                password = project.findProperty("moderne.artifactory.password") as String? ?: System.getenv("MODERNE_ARTIFACTORY_PASSWORD")
            }
        }
    }
}
