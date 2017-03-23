package pro.gramcode.box2d.dsl

import com.badlogic.gdx.physics.box2d.FixtureDef
import kotlin.reflect.KProperty

@Box2dDslMarker
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