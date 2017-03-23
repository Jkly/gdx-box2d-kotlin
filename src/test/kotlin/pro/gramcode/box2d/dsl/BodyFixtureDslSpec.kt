package pro.gramcode.box2d.dsl

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.Shape
import com.badlogic.gdx.physics.box2d.World
import com.nhaarman.mockito_kotlin.argThat
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

object BodyFixtureDslSpec : Spek({
    describe("a body fixture DSL") {
        var world: World? = null
        beforeEachTest {
            world = World(Vector2.Zero, false)
        }
        afterEachTest {
            world?.dispose()
        }

        context("adding a body fixture") {
            it("should call the callback function with newly created fixture") {
                val callback = spy({ _:Fixture -> })
                val callback2 = spy({ _:Fixture -> })
                world!!.createBody {
                    circle(callback) { }
                    polygon(callback2) { }
                }
                verify(callback).invoke(argThat { shape.type == Shape.Type.Circle})
                verify(callback2).invoke(argThat { shape.type == Shape.Type.Polygon})
            }
        }
    }
})