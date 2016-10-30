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
        with(circle(withFilter {
                groupIndex = 1
                categoryBits = 0x0000
            }) {
            radius = 5f
            isSensor = true
            
        })
    }
}
```
