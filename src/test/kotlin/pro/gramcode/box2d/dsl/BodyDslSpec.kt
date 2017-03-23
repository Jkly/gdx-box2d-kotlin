package pro.gramcode.box2d.dsl

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.google.common.truth.Truth.assertThat
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
                val body = addTo(world!!) {
                    body {
                    }
                }
                body.isActive shouldEqual true
            }

            it("should add a rigidbody with the bodydef") {
                val body = addTo(world!!) {
                    body(BodyDef().apply { active = false }) {
                    }
                }
                body.isActive shouldEqual false
            }

            it("should add a rigidbody with the bodydef convenience method") {
                val body = addTo(world!!) {
                    body(bodyDef(active = false)) {
                    }
                }
                body.isActive shouldEqual false
            }
        }

        context("adding a fixture to a body") {
            it("should add a fixture to body") {
                val body = addTo(world!!) {
                    body {
                        with(circleDef { })
                    }
                }
                body.fixtureList.size shouldEqual 1
                body.fixtureList[0].shape.type shouldEqual Shape.Type.Circle
            }
            it("should add multiple fixtures to body") {
                val body = addTo(world!!) {
                    body {
                        with(circleDef {  })
                        with(polygonDef {  })
                    }
                }
                body.fixtureList.size shouldEqual 2
                body.fixtureList[0].shape.type shouldEqual Shape.Type.Circle
                body.fixtureList[1].shape.type shouldEqual Shape.Type.Polygon
            }
            context("add fixtures to body after body is created") {
                it("should be able to add a fixture") {
                    val body = addTo(world!!) {
                        body {}
                    }
                    body.add(circleDef {  })
                    body.fixtureList.size shouldEqual 1
                    body.fixtureList[0].shape.type shouldEqual Shape.Type.Circle
                }
                it("should be dispose of the shape") {
                    val body = addTo(world!!) {
                        body {}
                    }

                    val circle = CircleShape()
                    val shape = spy(circle)

                    val fixtureDef = FixtureDef()
                    fixtureDef.shape = shape
                    body.add(fixtureDef)

                    Verify on shape that shape.dispose() was called
                }
            }

            it("should add circle fixture to body with shortcut DSL") {
                val body = addTo(world!!) {
                    body {
                        circle { }
                    }
                }
                body.fixtureList.size shouldEqual 1
                body.fixtureList[0].shape.type shouldEqual Shape.Type.Circle
            }
            it("should add circle fixture to body with shortcut DSL") {
                val body = addTo(world!!) {
                    body {
                        polygon {  }
                    }
                }
                body.fixtureList.size shouldEqual 1
                body.fixtureList[0].shape.type shouldEqual Shape.Type.Polygon
            }
        }

        context("creating a circle fixture") {
            it("should set the type of the circle") {
                val circle = circleDef {
                }
                circle.shape.type shouldBe Shape.Type.Circle
            }
            it("should set the position of the circle shape via a function") {
                val circle = circleDef {
                    position(Vector2(1f, 1f))
                }
                (circle.shape as CircleShape).position shouldEqual Vector2(1f, 1f)
            }
            it("should set the position of the circle shape via a field") {
                val circle = circleDef {
                    position = Vector2(1f, 1f)
                }
                (circle.shape as CircleShape).position shouldEqual Vector2(1f, 1f)
            }
            it("should set the radius of the circle shape via a function") {
                val circle = circleDef {
                    radius(50f)
                }
                (circle.shape as CircleShape).radius shouldEqual 50f
            }
            it("should set the radius of the circle shape via a field") {
                val circle = circleDef {
                    radius = 50f
                }
                (circle.shape as CircleShape).radius shouldEqual 50f
            }
        }

        context("creating a polygon fixture") {
            it("should set the type of the polygon") {
                val fixtureDef = polygonDef {  }
                fixtureDef.shape.type shouldBe Shape.Type.Polygon
            }
            it("should create a box positioned at the origin") {
                val fixtureDef = polygonDef {
                    setAsBox(1f, 1f)
                }

                (fixtureDef.shape as PolygonShape).vertexCount shouldBe 4

                val vec = Vector2()
                (fixtureDef.shape as PolygonShape).getVertex(0, vec)
                vec shouldEqual Vector2(-1f, -1f)

                (fixtureDef.shape as PolygonShape).getVertex(1, vec)
                vec shouldEqual Vector2(1f, -1f)

                (fixtureDef.shape as PolygonShape).getVertex(2, vec)
                vec shouldEqual Vector2(1f, 1f)

                (fixtureDef.shape as PolygonShape).getVertex(3, vec)
                vec shouldEqual Vector2(-1f, 1f)
            }
            it("should create a box at the specified position") {
                val fixtureDef = polygonDef {
                    setAsBox(1f, 1f, Vector2(1f,1f), 0f)
                }

                (fixtureDef.shape as PolygonShape).vertexCount shouldBe 4

                val vec = Vector2()
                (fixtureDef.shape as PolygonShape).getVertex(0, vec)
                vec shouldEqual Vector2(0f, 0f)

                (fixtureDef.shape as PolygonShape).getVertex(1, vec)
                vec shouldEqual Vector2(2f, 0f)

                (fixtureDef.shape as PolygonShape).getVertex(2, vec)
                vec shouldEqual Vector2(2f, 2f)

                (fixtureDef.shape as PolygonShape).getVertex(3, vec)
                vec shouldEqual Vector2(0f, 2f)
            }
            it("should create a box with the specified angle") {
                val fixtureDef = polygonDef {
                    setAsBox(2f, 1f, Vector2.Zero, (180f * (Math.PI / 180f)).toFloat())
                }

                (fixtureDef.shape as PolygonShape).vertexCount shouldBe 4

                val vec = Vector2()
                (fixtureDef.shape as PolygonShape).getVertex(0, vec)
                assertThat(vec.x).isWithin(0.1f).of(2f)
                assertThat(vec.y).isWithin(0.1f).of(1f)

                (fixtureDef.shape as PolygonShape).getVertex(1, vec)
                assertThat(vec.x).isWithin(0.1f).of(-2f)
                assertThat(vec.y).isWithin(0.1f).of(1f)

                (fixtureDef.shape as PolygonShape).getVertex(2, vec)
                assertThat(vec.x).isWithin(0.1f).of(-2f)
                assertThat(vec.y).isWithin(0.1f).of(-1f)

                (fixtureDef.shape as PolygonShape).getVertex(3, vec)
                assertThat(vec.x).isWithin(0.1f).of(2f)
                assertThat(vec.y).isWithin(0.1f).of(-1f)
            }
        }

    }
})