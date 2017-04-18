package pro.gramcode.box2d.dsl

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.utils.Array
import com.nhaarman.mockito_kotlin.argThat
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import org.amshove.kluent.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

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

            it("should pass the body to the callback") {
                val callback = spy({ _:Body -> })
                world!!add {
                    body(bodyDef(active = false), callback) {
                    }
                }
                verify(callback).invoke(argThat { !isActive })
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
                addTo(world!!) {
                    body {
                    }
                }
                val bodies = Array<Body>()
                world!!.getBodies(bodies)
                bodies[0].isActive shouldEqual true
            }

            it("should add a rigidbody with the bodydef") {
                addTo(world!!) {
                    body(BodyDef().apply { active = false }) {
                    }
                }
                val bodies = Array<Body>()
                world!!.getBodies(bodies)
                bodies[0].isActive shouldEqual false
            }

            it("should add a rigidbody with the bodydef convenience method") {
                addTo(world!!) {
                    body(bodyDef(active = false)) {
                    }
                }
                val bodies = Array<Body>()
                world!!.getBodies(bodies)
                bodies[0].isActive shouldEqual false
            }

            it("should add multiple rigidbodies") {
                addTo(world!!) {
                    body {
                    }
                    body {
                    }
                }
                val bodies = Array<Body>()
                world!!.getBodies(bodies)
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
                addTo(world!!) {
                    body {
                        with(circleDef { })
                    }
                }
                val bodies = Array<Body>()
                world!!.getBodies(bodies)
                bodies[0].fixtureList.size shouldEqual 1
                bodies[0].fixtureList[0].shape.type shouldEqual Shape.Type.Circle
            }
            it("should add multiple fixtures to body") {
                addTo(world!!) {
                    body {
                        with(circleDef {  })
                        with(polygonDef {  })
                    }
                }
                val bodies = Array<Body>()
                world!!.getBodies(bodies)
                bodies[0].fixtureList.size shouldEqual 2
                bodies[0].fixtureList[0].shape.type shouldEqual Shape.Type.Circle
                bodies[0].fixtureList[1].shape.type shouldEqual Shape.Type.Polygon
            }
            context("add fixtures to body after body is created") {
                it("should be able to add a fixture via a definition") {
                    addTo(world!!) {
                        body {}
                    }
                    val bodies = Array<Body>()
                    world!!.getBodies(bodies)
                    bodies[0].add(circleDef {  })
                    bodies[0].fixtureList.size shouldEqual 1
                    bodies[0].fixtureList[0].shape.type shouldEqual Shape.Type.Circle
                }
                it("should be dispose of the shape") {
                    addTo(world!!) {
                        body {}
                    }

                    val circle = CircleShape()
                    val shape = spy(circle)

                    val fixtureDef = FixtureDef()
                    fixtureDef.shape = shape
                    val bodies = Array<Body>()
                    world!!.getBodies(bodies)
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
                addTo(world!!) {
                    body {
                        circle { }
                    }
                }
                val bodies = Array<Body>()
                world!!.getBodies(bodies)
                bodies[0].fixtureList.size shouldEqual 1
                bodies[0].fixtureList[0].shape.type shouldEqual Shape.Type.Circle
            }
            it("should add polygon fixture to body with shortcut DSL") {
                addTo(world!!) {
                    body {
                        polygon {  }
                    }
                }
                val bodies = Array<Body>()
                world!!.getBodies(bodies)
                bodies[0].fixtureList.size shouldEqual 1
                bodies[0].fixtureList[0].shape.type shouldEqual Shape.Type.Polygon
            }
        }
    }
})