# Kotlin DSL for LibGDX Box2D
A DSL for creating rigidbodies.

## Example
```kotlin
val world = World(Vector2.Zero, false)

addTo(world) {
    body(
        type = BodyDef.BodyType.DynamicBody,
        angle = angle,
        bullet = true) {
        circle {
            radius(5f)
            isSensor(true)
            filter {
                groupIndex(1)
                categoryBits(0x0000)
            }
        }
    }
}
  
```
