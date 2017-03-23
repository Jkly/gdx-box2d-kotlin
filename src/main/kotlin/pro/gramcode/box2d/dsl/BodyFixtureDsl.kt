package pro.gramcode.box2d.dsl

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.FixtureDef

@Box2dDslMarker
class BodyFixtureDsl(val body: Body) {
    fun with(fixtureDef: FixtureDef, fixtureHandler: (Fixture) -> Unit = {}) {
        fixtureHandler(body.add(fixtureDef))
    }

    fun circle(fixtureHandler: (Fixture)->Unit = {}, configure: CircleFixtureDsl.()->Unit = {}) {
        with(circleDef(configure), fixtureHandler)
    }

    fun polygon(fixtureHandler: (Fixture)->Unit = {}, configure: PolygonFixtureDsl.()->Unit = {}) {
        with(polygonDef(configure), fixtureHandler)
    }
}