[![Build Status](https://semaphoreci.com/api/v1/jkly/gdx-box2d-kotlin/branches/master/shields_badge.svg)](https://semaphoreci.com/jkly/gdx-box2d-kotlin) [ ![Download](https://api.bintray.com/packages/jkly/releases/gdx-box2d-kotlin/images/download.svg) ](https://bintray.com/jkly/releases/gdx-box2d-kotlin/_latestVersion) [![Release](https://jitpack.io/v/Jkly/gdx-box2d-kotlin.svg)](https://jitpack.io/#Jkly/gdx-box2d-kotlin)

# Kotlin DSL for LibGDX Box2D
A DSL for creating rigidbodies.

## Example
```kotlin
val world = World(Vector2.Zero, false)

addTo(world) {
    body(def {
            type = BodyDef.BodyType.DynamicBody
            angle = angle
            bullet = true
        }) {
        circle {
            radius = 5f
            isSensor = true
            filter {
                groupIndex = 1
                categoryBits = 0x0000
            }
        }
    }
}
```
