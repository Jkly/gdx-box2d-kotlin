package pro.gramcode.box2d.dsl

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.CircleShape
import com.badlogic.gdx.physics.box2d.FixtureDef
import kotlin.reflect.KProperty

class CircleFixtureDsl(val circle: CircleShape, fixtureDef: FixtureDef) : FixtureDsl(fixtureDef) {
    var position: Vector2 by object {
        operator fun getValue(dsl: CircleFixtureDsl, property: KProperty<*>): Vector2 = circle.position
        operator fun setValue(dsl: CircleFixtureDsl, property: KProperty<*>, position: Vector2) {
            circle.position = position
        }
    }

    var radius: Float by object {
        operator fun getValue(dsl: FixtureDsl, property: KProperty<*>): Float = circle.radius
        operator fun setValue(dsl: FixtureDsl, property: KProperty<*>, radius: Float) {
            circle.radius = radius
        }
    }

    fun position(pos: Vector2) {
        circle.position = pos
    }

    fun radius(radius: Float) {
        circle.radius = radius
    }
}