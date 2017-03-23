package pro.gramcode.box2d.dsl

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.Shape

@Box2dDslMarker
class BodyFixtureDsl(val body: Body) {
    fun with(fixtureDef: FixtureDef): Fixture {
        return addFixture(fixtureDef.shape, body, fixtureDef)
    }

    fun circle(configure: CircleFixtureDsl.()->Unit = {}): Fixture {
        return with(circleDef(configure))
    }

    fun polygon(configure: PolygonFixtureDsl.()->Unit = {}): Fixture {
        return with(polygonDef(configure))
    }

    private fun <T: Shape> addFixture(shape: T, body: Body, fixtureDef: FixtureDef): Fixture {
        val fixture = body.createFixture(fixtureDef)
        shape.dispose()
        return fixture
    }

}