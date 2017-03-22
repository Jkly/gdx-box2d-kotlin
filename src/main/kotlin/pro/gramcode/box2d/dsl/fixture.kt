package pro.gramcode.box2d.dsl

import com.badlogic.gdx.physics.box2d.*

fun circleDef(configure: CircleFixtureDsl.()->Unit = {}): FixtureDef {
    return fixtureDef(CircleShape()) { shape, fixtureDef ->
        configure(CircleFixtureDsl(shape, fixtureDef))
    }
}

fun polygonDef(configure: PolygonFixtureDsl.()->Unit = {}): FixtureDef {
    return fixtureDef(PolygonShape()) { shape, fixtureDef ->
        configure(PolygonFixtureDsl(shape, fixtureDef))
    }
}

fun BodyFixtureDsl.with(fixtureDef: FixtureDef): Fixture {
    return addFixture(fixtureDef.shape, body, fixtureDef)
}

fun BodyFixtureDsl.circle(configure: CircleFixtureDsl.()->Unit = {}): Fixture {
    return with(circleDef(configure))
}

fun BodyFixtureDsl.polygon(configure: PolygonFixtureDsl.()->Unit = {}): Fixture {
    return with(polygonDef(configure))
}

private fun <T: Shape> addFixture(shape: T, body: Body, fixtureDef: FixtureDef): Fixture {
    val fixture = body.createFixture(fixtureDef)
    shape.dispose()
    return fixture
}

private fun <T: Shape> fixtureDef(shape: T,
                                  configure: (T, FixtureDef) -> Unit): FixtureDef {
    val fixtureDef = FixtureDef()
    fixtureDef.shape = shape
    configure(shape, fixtureDef)

    return fixtureDef
}



