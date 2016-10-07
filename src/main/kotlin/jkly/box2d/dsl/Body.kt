package jkly.box2d.dsl

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.FixtureDef

fun def(configure: BodyDefDsl.()->Unit): BodyDef {
    val bodyDef = BodyDef()
    configure(BodyDefDsl(bodyDef))
    return bodyDef
}


class BodyDefDsl(private val bodyDef: BodyDef) {
    fun type(type: BodyDef.BodyType):BodyDefDsl {
        bodyDef.type = type
        return this
    }
//    def.type = type
//    def.position.set(position)
//    def.angle = angle
//    def.linearVelocity.set(linearVelocity)
//    def.angularVelocity = angularVelocity
//    def.linearDamping = linearDamping
//    def.angularDamping = angularDamping
//    def.allowSleep = allowSleep
//    def.awake = awake
//    def.fixedRotation = fixedRotation
//    def.bullet = bullet
//    def.active = active
//    def.gravityScale = gravityScale
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

//fun Body.add(addFixture: BodyDsl.() -> Fixture): Fixture {
//    val bodyDsl = ExistingBodyDsl(this)
//    return addFixture(bodyDsl)
//}
