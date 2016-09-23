project.group = "jkly"

buildscript {
    repositories {
        mavenCentral()
        gradleScriptKotlin()
        maven {
            setUrl("https://plugins.gradle.org/m2/")
        }

    }
    dependencies {
        classpath(kotlinModule("gradle-plugin"))
        classpath("gradle.plugin.com.zoltu.gradle.plugin:git-versioning:2.0.28")
    }
}

apply {
    plugin("com.zoltu.git-versioning")
    plugin("kotlin")
    plugin("maven")
}

repositories {
    mavenCentral()
    gradleScriptKotlin()
}

val gdxVersion = "1.7.1"

dependencies {
    compile(kotlinModule("stdlib"))
    compile("com.badlogicgames.gdx:gdx-box2d:$gdxVersion")
}
