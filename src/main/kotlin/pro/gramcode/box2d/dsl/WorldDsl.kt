package pro.gramcode.box2d.dsl

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World

@Box2dDslMarker
class WorldDsl(val world: World) {
    val bodies: MutableList<Body> = mutableListOf()

    fun body(bodyDef: BodyDef = BodyDef(),
                 configure: BodyFixtureDsl.() -> Unit = {}) {
        bodies.add(world.createBody(bodyDef, configure))
    }
}





