package jkly.box2d.dsl

import com.badlogic.gdx.physics.box2d.*

fun World.add(rigidBodyFrom: Dsl.() -> BodyFactoryDsl): Body = addTo(this, rigidBodyFrom)
fun World.define(): Dsl = Dsl(this)

fun addTo(world: World, rigidBodyFrom: Dsl.() -> BodyFactoryDsl): Body {
    val dsl = Dsl(world)
    return world.createBody(rigidBodyFrom(dsl).bodyDef)
}

class Dsl(val world: World)





