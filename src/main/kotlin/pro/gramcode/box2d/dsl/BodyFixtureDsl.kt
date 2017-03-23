package pro.gramcode.box2d.dsl

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.Shape

@Box2dDslMarker
class BodyFixtureDsl(val body: Body) {
    fun with(fixtureDef: FixtureDef, fixtureHandler: (Fixture) -> Unit = {}) {
        fixtureHandler(addFixture(fixtureDef.shape, body, fixtureDef))
    }

    fun circle(fixtureHandler: (Fixture)->Unit = {}, configure: CircleFixtureDsl.()->Unit = {}) {
        return with(circleDef(configure), fixtureHandler)
    }

    fun polygon(fixtureHandler: (Fixture)->Unit = {}, configure: PolygonFixtureDsl.()->Unit = {}) {
        return with(polygonDef(configure), fixtureHandler)
    }

    private fun <T: Shape> addFixture(shape: T, body: Body, fixtureDef: FixtureDef): Fixture {
        val fixture = body.createFixture(fixtureDef)
        shape.dispose()
        return fixture
    }

}