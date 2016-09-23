project.group = "jkly"
project.version ="0.1.0"

buildscript {
    repositories {
        mavenCentral()
        gradleScriptKotlin()

    }
    dependencies {
        classpath(kotlinModule("gradle-plugin"))
    }
}

apply {
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
