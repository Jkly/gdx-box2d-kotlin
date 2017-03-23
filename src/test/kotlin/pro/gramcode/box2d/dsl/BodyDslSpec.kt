package pro.gramcode.box2d.dsl

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import org.amshove.kluent.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.mockito.Mockito.*

object BodyDslSpec : Spek({
    describe("a rigidbody DSL") {
        var world: World? = null
        beforeEachTest {
            world = World(Vector2.Zero, false)
        }
        afterEachTest {
            world?.dispose()
        }

        context("adding a body via an extension method") {
            it("should add a rigidbody to the world") {
                world!!add {
                    body {
                    }
                }
                world!!.bodyCount shouldEqual 1
            }
        }

        context("adding a body via a fluent style API") {
            it("should add a rigidbody to the world") {
                addTo(world!!) {
                    body {
                    }
                }
                world!!.bodyCount shouldEqual 1
            }
        }

        context("creating a body") {
            it("should add a rigidbody with a default bodydef") {
                val bodies = addTo(world!!) {
                    body {
                    }
                }
                bodies[0].isActive shouldEqual true
            }

            it("should add a rigidbody with the bodydef") {
                val bodies = addTo(world!!) {
                    body(BodyDef().apply { active = false }) {
                    }
                }
                bodies[0].isActive shouldEqual false
            }

            it("should add a rigidbody with the bodydef convenience method") {
                val bodies = addTo(world!!) {
                    body(bodyDef(active = false)) {
                    }
                }
                bodies[0].isActive shouldEqual false
            }

            it("should add multiple rigidbodies") {
                val bodies = addTo(world!!) {
                    body {
                    }
                    body {
                    }
                }
                bodies.size shouldEqual 2
            }

            it("should add a rigidbody to the world and return it") {
                val body = world!!.createBody(bodyDef(active = false)) {
                }
                world!!.bodyCount shouldEqual 1
                body.isActive shouldBe false
            }
        }

        context("adding a fixture to a body") {
            it("should add a fixture to body") {
                val bodies = addTo(world!!) {
                    body {
                        with(circleDef { })
                    }
                }
                bodies[0].fixtureList.size shouldEqual 1
                bodies[0].fixtureList[0].shape.type shouldEqual Shape.Type.Circle
            }
            it("should add multiple fixtures to body") {
                val bodies = addTo(world!!) {
                    body {
                        with(circleDef {  })
                        with(polygonDef {  })
                    }
                }
                bodies[0].fixtureList.size shouldEqual 2
                bodies[0].fixtureList[0].shape.type shouldEqual Shape.Type.Circle
                bodies[0].fixtureList[1].shape.type shouldEqual Shape.Type.Polygon
            }
            context("add fixtures to body after body is created") {
                it("should be able to add a fixture via a definition") {
                    val bodies = addTo(world!!) {
                        body {}
                    }
                    bodies[0].add(circleDef {  })
                    bodies[0].fixtureList.size shouldEqual 1
                    bodies[0].fixtureList[0].shape.type shouldEqual Shape.Type.Circle
                }
                it("should be dispose of the shape") {
                    val bodies = addTo(world!!) {
                        body {}
                    }

                    val circle = CircleShape()
                    val shape = spy(circle)

                    val fixtureDef = FixtureDef()
                    fixtureDef.shape = shape
                    bodies[0].add(fixtureDef)

                    Verify on shape that shape.dispose() was called
                }
                it("should be able to add multiple fixtures") {
                    val body = world!!.createBody()
                    body.addFixtures {
                        polygon {  }
                        circle {  }
                    }
                    body.fixtureList.size shouldEqual 2
                    body.fixtureList[0].shape.type shouldEqual Shape.Type.Polygon
                    body.fixtureList[1].shape.type shouldEqual Shape.Type.Circle
                }
            }

            it("should add circle fixture to body with shortcut DSL") {
                val bodies = addTo(world!!) {
                    body {
                        circle { }
                    }
                }
                bodies[0].fixtureList.size shouldEqual 1
                bodies[0].fixtureList[0].shape.type shouldEqual Shape.Type.Circle
            }
            it("should add circle fixture to body with shortcut DSL") {
                val bodies = addTo(world!!) {
                    body {
                        polygon {  }
                    }
                }
                bodies[0].fixtureList.size shouldEqual 1
                bodies[0].fixtureList[0].shape.type shouldEqual Shape.Type.Polygon
            }
        }
    }
})