package pro.gramcode.box2d.dsl

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World

@Box2dDslMarker
class Dsl(val world: World) {
    fun body(bodyDef: BodyDef = BodyDef(),
                 configure: BodyFixtureDsl.() -> Unit = {}): BodyDsl {
        val body = world.createBody(bodyDef)
        configure(BodyFixtureDsl(body))
        return BodyDsl(body)
    }
}





