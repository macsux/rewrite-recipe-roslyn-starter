plugins {
    id("org.openrewrite.build.recipe-library-base") version "latest.release"
    id("org.openrewrite.build.recipe-repositories") version "latest.release"
    id("maven-publish")
    id("org.openrewrite.build.moderne-source-available-license") version "latest.release"
}

group = "com.almirex"
description = "Rewrite C# recipes based on Roslyn transformations."
version = (project.findProperty("version") as String?) ?: System.getenv("INJECTED_VERSION") ?: "unspecified"

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

tasks.register("licenseFormat") {
    println("License format task not implemented for rewrite-recipe-roslyn-starter")
}

val sourcesJar = tasks.register<Jar>("emptySourceJar") {
    from("README.md")
    archiveClassifier = "sources"
}

publishing {
    repositories {
        maven {
            val releaseRepo = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            val snapshotRepo = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")

            name = "OSSRH"
            url = if(project.hasProperty("releasing")) {
                releaseRepo
            } else {
                snapshotRepo
            }

            credentials {
                username = (findProperty("OSSRH_USERNAME") ?: System.getenv("OSSRH_USERNAME")) as String?
                password = (findProperty("OSSRH_PASSWORD") ?: System.getenv("OSSRH_PASSWORD")) as String?
            }
        }
    }

    publications {
        create<MavenPublication>("maven") {
            groupId = project.group as String
            artifactId = project.name
            version = project.version as String

            from(components["java"])
            artifact(sourcesJar)
            pom {
                url = "https://github.com/macsux/rewrite-recipe-roslyn-starter"

                scm {
                    url = "https://github.com/macsux/rewrite-recipe-roslyn-starter"
                    connection = "scm:git://github.com:macsux/rewrite-recipe-roslyn-starter.git"
                    developerConnection = "scm:git://github.com:macsux/rewrite-recipe-roslyn-starter.git"
                }

                developers {
                    developer {
                        id = "macsux"
                        name = "Andrew Stakhov"
                        email = "team@moderne.io"
                        organizationUrl = "https://moderne.io/"
                    }
                }
            }
        }
    }
}
