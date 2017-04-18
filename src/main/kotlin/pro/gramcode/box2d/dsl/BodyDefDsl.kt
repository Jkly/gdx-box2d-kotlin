package pro.gramcode.box2d.dsl

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef

class BodyDefDsl(private val bodyDef: BodyDef) {
    var type: BodyDef.BodyType
        get() = bodyDef.type
        set(type) {
            bodyDef.type = type
        }

    var position: Vector2
        get() = bodyDef.position
        set(position) {
            bodyDef.position.set(position)
        }

    var x: Float
        get() = bodyDef.position.x
        set(x) {
            bodyDef.position.x = x
        }

    var y: Float
        get() = bodyDef.position.y
        set(y) {
            bodyDef.position.y = y
        }

    var angle: Float
        get() = bodyDef.angle
        set(angle) {
            bodyDef.angle = angle
        }

    var linearVelocity: Vector2
        get() = bodyDef.linearVelocity
        set(linearVelocity) {
            bodyDef.linearVelocity.set(linearVelocity)
        }

    var angularVelocity: Float
        get() = bodyDef.angularVelocity
        set(angularVelocity) {
            bodyDef.angularVelocity = angularVelocity
        }

    var linearDamping: Float
        get() = bodyDef.linearDamping
        set(linearDamping) {
            bodyDef.linearDamping = linearDamping
        }

    var angularDamping: Float
        get() = bodyDef.angularDamping
        set(angularDamping) {
            bodyDef.angularDamping = angularDamping
        }

    var allowSleep: Boolean
        get() = bodyDef.allowSleep
        set(allowSleep) {
            bodyDef.allowSleep = allowSleep
        }

    var awake: Boolean
        get() = bodyDef.awake
        set(awake) {
            bodyDef.awake = awake
        }

    var fixedRotation: Boolean
        get() = bodyDef.fixedRotation
        set(fixedRotation) {
            bodyDef.fixedRotation = fixedRotation
        }

    var bullet: Boolean
        get() = bodyDef.bullet
        set(bullet) {
            bodyDef.bullet = bullet
        }

    var active: Boolean
        get() = bodyDef.active
        set(active) {
            bodyDef.active = active
        }

    var gravityScale: Float
        get() = bodyDef.gravityScale
        set(gravityScale) {
            bodyDef.gravityScale = gravityScale
        }
}