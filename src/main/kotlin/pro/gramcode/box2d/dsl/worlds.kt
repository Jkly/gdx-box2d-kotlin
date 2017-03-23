package pro.gramcode.box2d.dsl

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World

infix fun World.add(rigidBodyFrom: WorldDsl.() -> Unit): List<Body> = addTo(this, rigidBodyFrom)

fun addTo(world: World, configure: WorldDsl.() -> Unit): List<Body> {
    val worldDsl = WorldDsl(world)
    configure(worldDsl)
    return worldDsl.bodies.toList()
}

fun World.createBody(bodyDef: BodyDef, configure: BodyFixtureDsl.() -> Unit = {}): Body {
    val body = this.createBody(bodyDef)
    configure(BodyFixtureDsl(body))
    return body
}