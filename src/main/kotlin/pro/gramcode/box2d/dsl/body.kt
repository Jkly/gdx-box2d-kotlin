package pro.gramcode.box2d.dsl

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.FixtureDef

fun Body.add(def: FixtureDef): Fixture {
    val fixture = this.createFixture(def)
    def.shape.dispose()
    return fixture
}

fun def(configure: BodyDefDsl.()->Unit): BodyDef {
    val bodyDef = BodyDef()
    configure(BodyDefDsl(bodyDef))
    return bodyDef
}

fun defineBody(configure: BodyDefDsl.()->Unit): BodyDef {
    return def(configure)
}

fun bodyDef(
        type: BodyDef.BodyType = BodyDef.BodyType.StaticBody,
        position: Vector2 = Vector2(),
        angle: Float = 0f,
        linearVelocity: Vector2= Vector2(),
        angularVelocity: Float = 0f,
        linearDamping: Float = 0f,
        angularDamping: Float = 0f,
        allowSleep: Boolean = true,
        awake: Boolean = true,
        fixedRotation: Boolean = false,
        bullet: Boolean = false,
        active: Boolean = true,
        gravityScale: Float = 1f):BodyDef {

    return BodyDef().apply {
        this.type = type
        this.position.set(position)
        this.angle = angle
        this.linearVelocity.set(linearVelocity)
        this.angularVelocity = angularVelocity
        this.linearDamping = linearDamping
        this.angularDamping = angularDamping
        this.allowSleep = allowSleep
        this.awake = awake
        this.fixedRotation =fixedRotation
        this.bullet = bullet
        this.active = active
        this.gravityScale = gravityScale
    }
}

