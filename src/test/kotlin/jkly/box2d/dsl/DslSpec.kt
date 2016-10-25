package jkly.box2d.dsl

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class DslSpec : Spek({
    describe("Box2D Rigidbody DSL") {
        val world = World(Vector2.Zero, false)

        describe("adding a body via an extension method") {
            it("should add a rigidbody to the world") {
                world.add {
                    body {
                    }
                }

                world.bodyCount shouldEqual 1
            }
        }
    }
})