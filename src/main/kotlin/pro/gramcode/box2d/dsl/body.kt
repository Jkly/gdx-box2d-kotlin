package pro.gramcode.box2d.dsl

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.FixtureDef

fun def(configure: BodyDefDsl.()->Unit): BodyDef {
    val bodyDef = BodyDef()
    configure(BodyDefDsl(bodyDef))
    return bodyDef
}

fun defineBody(configure: BodyDefDsl.()->Unit): BodyDef {
    return def(configure)
}

fun Dsl.body(bodyDef: BodyDef = BodyDef(),
             configure: BodyFixtureDsl.() -> Unit = {}): BodyDsl {
    val body = world.createBody(bodyDef)
    configure(BodyFixtureDsl(body))
    return BodyDsl(body)
}

class BodyDsl(val body: Body)

fun Body.add(def: FixtureDef): Fixture {
    val fixture = this.createFixture(def)
    def.shape.dispose()
    return fixture

}

class BodyFixtureDsl(val body: Body)
