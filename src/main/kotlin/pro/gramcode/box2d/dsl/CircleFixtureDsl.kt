package pro.gramcode.box2d.dsl

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.CircleShape
import com.badlogic.gdx.physics.box2d.FixtureDef

class CircleFixtureDsl(val circle: CircleShape, fixtureDef: FixtureDef) : FixtureDsl(fixtureDef) {
    var position: Vector2
        get() = circle.position
        set(position) {
            circle.position = position
        }

    var radius: Float
        get() = circle.radius
        set(radius) {
            circle.radius = radius
        }

    fun position(pos: Vector2) {
        circle.position = pos
    }

    fun radius(radius: Float) {
        circle.radius = radius
    }
}