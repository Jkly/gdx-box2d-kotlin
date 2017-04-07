package pro.gramcode.box2d.dsl

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape

class PolygonFixtureDsl(val polygon: PolygonShape, fixtureDef: FixtureDef) : FixtureDsl(fixtureDef) {
    fun setAsBox(hx: Float, hy: Float, center: Vector2, angle: Float = 0f) {
        polygon.setAsBox(hx, hy, center, angle)
    }
    fun setAsBox(hx: Float, hy: Float) {
        polygon.setAsBox(hx, hy)
    }
}
