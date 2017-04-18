package pro.gramcode.box2d.dsl

import com.badlogic.gdx.physics.box2d.FixtureDef

@Box2dDslMarker
abstract class FixtureDsl(private val fixtureDef: FixtureDef) {
    fun filter(configureFilter: FilterDsl.() -> Unit = {}) {
        configureFilter(FilterDsl(fixtureDef.filter))
    }

    var friction: Float
        get() = fixtureDef.friction
        set(friction) {
            fixtureDef.friction = friction
        }

    var restitution: Float
        get() = fixtureDef.restitution
        set(restitution) {
            fixtureDef.restitution = restitution
        }

    var density: Float
        get() = fixtureDef.density
        set(density) {
            fixtureDef.density = density
        }

    var isSensor: Boolean
        get() = fixtureDef.isSensor
        set(isSensor) {
            fixtureDef.isSensor = isSensor
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