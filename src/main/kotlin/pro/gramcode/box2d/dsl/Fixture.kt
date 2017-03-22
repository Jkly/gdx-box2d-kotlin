package pro.gramcode.box2d.dsl

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import kotlin.reflect.KProperty

fun circleDef(configure: CircleFixtureDsl.()->Unit = {}): FixtureDef {
    return fixtureDef(CircleShape()) { shape, fixtureDef ->
        configure(CircleFixtureDsl(shape, fixtureDef))
    }
}

fun polygonDef(configure: PolygonFixtureDsl.()->Unit = {}): FixtureDef {
    return fixtureDef(PolygonShape()) { shape, fixtureDef ->
        configure(PolygonFixtureDsl(shape, fixtureDef))
    }
}

fun BodyFixtureDsl.with(fixtureDef: FixtureDef): Fixture {
    return addFixture(fixtureDef.shape, body, fixtureDef)
}

fun BodyFixtureDsl.circle(configure: CircleFixtureDsl.()->Unit = {}): Fixture {
    return with(circleDef(configure))
}

fun BodyFixtureDsl.polygon(configure: PolygonFixtureDsl.()->Unit = {}): Fixture {
    return with(polygonDef(configure))
}

private fun <T: Shape> addFixture(shape: T, body: Body, fixtureDef: FixtureDef): Fixture {
    val fixture = body.createFixture(fixtureDef)
    shape.dispose()
    return fixture
}

private fun <T: Shape> fixtureDef(shape: T,
                                  configure: (T, FixtureDef) -> Unit): FixtureDef {
    val fixtureDef = FixtureDef()
    fixtureDef.shape = shape
    configure(shape, fixtureDef)

    return fixtureDef
}

abstract class FixtureDsl(private val fixtureDef: FixtureDef) {
    fun filter(configureFilter: FilterDsl.() -> Unit = {}) {
        configureFilter(FilterDsl(fixtureDef.filter))
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

class PolygonFixtureDsl(val polygon: PolygonShape, fixtureDef: FixtureDef) : FixtureDsl(fixtureDef) {
    fun setAsBox(hx: Float, hy: Float, center: Vector2, angle: Float) {
        polygon.setAsBox(hx, hy, center, angle)
    }
    fun setAsBox(hx: Float, hy: Float) {
        polygon.setAsBox(hx, hy)
    }
}
