package jkly.box2d.dsl

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.FixtureDef
import kotlin.reflect.KProperty

fun def(configure: BodyDefDsl.()->Unit): BodyDef {
    val bodyDef = BodyDef()
    configure(BodyDefDsl(bodyDef))
    return bodyDef
}


class BodyDefDsl(private val bodyDef: BodyDef) {
    var type: BodyDef.BodyType by object {
        operator fun getValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>): BodyDef.BodyType = bodyDef.type
        operator fun setValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>, type: BodyDef.BodyType) {
            bodyDef.type = type
        }
    }
    var position: Vector2 by object {
        operator fun getValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>): Vector2 = bodyDef.position
        operator fun setValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>, position: Vector2) {
            bodyDef.position.set(position)
        }
    }

    var angle: Float by object {
        operator fun getValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>): Float = bodyDef.angle
        operator fun setValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>, angle: Float) {
            bodyDef.angle = angle
        }
    }

    var linearVelocity: Vector2 by object {
        operator fun getValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>): Vector2 = bodyDef.linearVelocity
        operator fun setValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>, linearVelocity: Vector2) {
            bodyDef.linearVelocity.set(linearVelocity)
        }
    }

    var angularVelocity: Float by object {
        operator fun getValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>): Float = bodyDef.angularVelocity
        operator fun setValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>, angularVelocity: Float) {
            bodyDef.angularVelocity = angularVelocity
        }
    }

    var linearDamping: Float by object {
        operator fun getValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>): Float = bodyDef.linearDamping
        operator fun setValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>, linearDamping: Float) {
            bodyDef.linearDamping = linearDamping
        }
    }

    var angularDamping: Float by object {
        operator fun getValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>): Float = bodyDef.angularDamping
        operator fun setValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>, angularDamping: Float) {
            bodyDef.angularDamping = angularDamping
        }
    }

    var allowSleep: Boolean by object {
        operator fun getValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>): Boolean = bodyDef.allowSleep
        operator fun setValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>, allowSleep: Boolean) {
            bodyDef.allowSleep = allowSleep
        }
    }

    var awake: Boolean by object {
        operator fun getValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>): Boolean = bodyDef.awake
        operator fun setValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>, awake: Boolean) {
            bodyDef.awake = allowSleep
        }
    }

    var fixedRotation: Boolean by object {
        operator fun getValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>): Boolean = bodyDef.fixedRotation
        operator fun setValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>, fixedRotation: Boolean) {
            bodyDef.fixedRotation = fixedRotation
        }
    }

    var bullet: Boolean by object {
        operator fun getValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>): Boolean = bodyDef.bullet
        operator fun setValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>, bullet: Boolean) {
            bodyDef.bullet = bullet
        }
    }

    var active: Boolean by object {
        operator fun getValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>): Boolean = bodyDef.active
        operator fun setValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>, active: Boolean) {
            bodyDef.active = active
        }
    }

    var gravityScale: Float by object {
        operator fun getValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>): Float = bodyDef.gravityScale
        operator fun setValue(bodyDefDsl: BodyDefDsl, property: KProperty<*>, gravityScale: Float) {
            bodyDef.gravityScale = gravityScale
        }
    }
}


fun Dsl.body(bodyDef: BodyDef = BodyDef(),
             configure: BodyFixtureDsl.() -> Unit = {}): BodyDsl {
    val body = world.createBody(bodyDef)
    configure(BodyFixtureDsl(body))
    return BodyDsl(body)
}

class BodyDsl(val body: Body)

fun Body.add(fixture: FixtureDef): Fixture {
    return this.createFixture(fixture)
}

class BodyFixtureDsl(val body: Body)
