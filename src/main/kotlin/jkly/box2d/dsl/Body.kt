package jkly.box2d.dsl

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.World

fun Dsl.body(type: BodyDef.BodyType = BodyDef.BodyType.StaticBody,
             position: Vector2 = Vector2.Zero,
             angle:Float = 0f,
             linearVelocity: Vector2 = Vector2.Zero,
             angularVelocity:Float = 0f,
             linearDamping:Float = 0f,
             angularDamping:Float = 0f,
             allowSleep:Boolean = true,
             awake:Boolean = true,
             fixedRotation:Boolean = false,
             bullet:Boolean = false,
             active:Boolean = true,
             gravityScale:Float = 1f,
             configure: BodyDsl.() -> Unit = {}): BodyFactoryDsl {
    val bodyDef = BodyDef()
    bodyDef.type = type
    bodyDef.position.set(position)
    bodyDef.angle = angle
    bodyDef.linearVelocity.set(linearVelocity)
    bodyDef.angularVelocity = angularVelocity
    bodyDef.linearDamping = linearDamping
    bodyDef.angularDamping = angularDamping
    bodyDef.allowSleep = allowSleep
    bodyDef.awake = awake
    bodyDef.fixedRotation = fixedRotation
    bodyDef.bullet = bullet
    bodyDef.active = active
    bodyDef.gravityScale = gravityScale

    val bodyDsl = BodyFactoryDsl(world, bodyDef)
    configure(bodyDsl)
    return bodyDsl

}

abstract class BodyDsl {
    val body: Body by lazy {
        body()
    }

    abstract fun body(): Body
}

class ExistingBodyDsl(private val initBody: Body): BodyDsl() {
    override fun body(): Body = initBody
}

class BodyFactoryDsl(private val world: World, val bodyDef: BodyDef): BodyDsl() {
    override fun body(): Body = world.createBody(bodyDef)
}

class ConfigureBodyDefDsl(val bodyDef: BodyDef)

fun BodyFactoryDsl.create(configure: ConfigureBodyDefDsl.() -> Unit): Body {
    configure(ConfigureBodyDefDsl(this.bodyDef))
    return this.body
}

fun Body.add(addFixture: BodyDsl.() -> Fixture): Fixture {
    val bodyDsl = ExistingBodyDsl(this)
    return addFixture(bodyDsl)
}
