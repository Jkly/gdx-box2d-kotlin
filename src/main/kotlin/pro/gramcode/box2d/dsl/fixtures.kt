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

private fun <T: Shape> fixtureDef(shape: T,
                                  configure: (T, FixtureDef) -> Unit): FixtureDef {
    val fixtureDef = FixtureDef()
    fixtureDef.shape = shape
    configure(shape, fixtureDef)

    return fixtureDef
}



