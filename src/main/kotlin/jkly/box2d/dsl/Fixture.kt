package jkly.box2d.dsl

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import kotlin.reflect.KProperty


fun BodyFixtureDsl.withCircle(configure: CircleFixtureDsl.() -> Unit): Fixture {
    return configureFixture(CircleShape(), body) { shape, fixtureDef ->
        configure(CircleFixtureDsl(shape, fixtureDef))
    }
}

fun BodyFixtureDsl.withPolygon(configure: PolygonFixtureDsl.() -> Unit): Fixture {
    return configureFixture(PolygonShape(), body) { shape, fixtureDef ->
        configure(PolygonFixtureDsl(shape, fixtureDef))
    }
}

private fun <T:Shape> configureFixture(shape: T, body: Body, configure: (T, FixtureDef) -> Unit): Fixture {
    val fixtureDef = FixtureDef()
    fixtureDef.shape = shape
    configure(shape, fixtureDef)
    val fixture = body.createFixture(fixtureDef)
    shape.dispose()
    return fixture
}

fun circle(configure: CircleFixtureDsl.()->Unit): FixtureDef {
    val fixtureDef = FixtureDef()
    val shape = CircleShape()
    fixtureDef.shape = shape
    configure(CircleFixtureDsl(shape, fixtureDef))
    return fixtureDef
}

abstract class FixtureDsl(private val fixtureDef: FixtureDef) {
    val filter: Filter
    init {
        filter = fixtureDef.filter
    }
    var friction: Float by object {
        operator fun getValue(dsl: FixtureDsl, property: KProperty<*>): Float = fixtureDef.friction
        operator fun setValue(dsl: FixtureDsl, property: KProperty<*>, friction: Float) {
            fixtureDef.friction = friction
        }
    }

    var restitution: Float by object {
        operator fun getValue(dsl: FixtureDsl, property: KProperty<*>): Float = fixtureDef.restitution
        operator fun setValue(dsl: FixtureDsl, property: KProperty<*>, restitution: Float) {
            fixtureDef.restitution = restitution
        }
    }

    var density: Float by object {
        operator fun getValue(dsl: FixtureDsl, property: KProperty<*>): Float = fixtureDef.density
        operator fun setValue(dsl: FixtureDsl, property: KProperty<*>, density: Float) {
            fixtureDef.density = density
        }
    }

    var isSensor: Boolean by object {
        operator fun getValue(dsl: FixtureDsl, property: KProperty<*>): Boolean = fixtureDef.isSensor
        operator fun setValue(dsl: FixtureDsl, property: KProperty<*>, isSensor: Boolean) {
            fixtureDef.isSensor = isSensor
        }
    }

    fun friction(friction:Float) {
        fixtureDef.friction = friction
    }

    fun restitution(restitution: Float) {
        fixtureDef.restitution = restitution
    }

    fun density(density: Float) {
        fixtureDef.density = density
    }

    fun isSensor(isSensor: Boolean) {
        fixtureDef.isSensor = isSensor
    }
}

class CircleFixtureDsl(val circle: CircleShape, fixtureDef: FixtureDef) : FixtureDsl(fixtureDef) {

    fun position(pos: Vector2) {
        circle.position = pos
    }

    fun radius(radius: Float) {
        circle.radius = radius
    }
}

class PolygonFixtureDsl(val polygon: PolygonShape, fixtureDef: FixtureDef) : FixtureDsl(fixtureDef) {
    fun setAsBox(hx: Float, hy: Float, center: Vector2, angle: Float) {
        polygon.setAsBox(hx, hy, center, angle)
    }
    fun setAsBox(hx: Float, hy: Float) {
        polygon.setAsBox(hx, hy)
    }
}
