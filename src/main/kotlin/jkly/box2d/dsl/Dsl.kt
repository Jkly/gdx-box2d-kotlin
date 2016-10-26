package jkly.box2d.dsl

import com.badlogic.gdx.physics.box2d.*

infix fun World.add(rigidBodyFrom: Dsl.() -> BodyDsl): Body = addTo(this, rigidBodyFrom)

fun addTo(world: World, rigidBodyFrom: Dsl.() -> BodyDsl): Body {
    val dsl = Dsl(world)
    return rigidBodyFrom(dsl).body
}

class Dsl(val world: World)





