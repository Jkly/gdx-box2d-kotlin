package pro.gramcode.box2d.dsl

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World

@Box2dDslMarker
class WorldDsl(val world: World) {
    fun body(bodyDef: BodyDef = BodyDef(), bodyHandler: (Body) -> Unit = {_:Body -> },
             configure: BodyFixtureDsl.() -> Unit = {}) {
        bodyHandler(world.createBody(bodyDef, configure))
    }
}





