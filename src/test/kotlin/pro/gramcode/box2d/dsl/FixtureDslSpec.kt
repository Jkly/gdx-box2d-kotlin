package pro.gramcode.box2d.dsl

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.CircleShape
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.Shape
import com.badlogic.gdx.physics.box2d.World
import com.google.common.truth.Truth
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

object FixtureDslSpec : Spek({
    describe("a fixture creation DSL") {
        var world: World? = null
        beforeEachTest {
            world = World(Vector2.Zero, false)
        }
        afterEachTest {
            world?.dispose()
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
            it("should create a box at the specified position with default angle") {
                val fixtureDef = polygonDef {
                    setAsBox(1f, 1f, Vector2(1f,1f))
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
                Truth.assertThat(vec.x).isWithin(0.1f).of(2f)
                Truth.assertThat(vec.y).isWithin(0.1f).of(1f)

                (fixtureDef.shape as PolygonShape).getVertex(1, vec)
                Truth.assertThat(vec.x).isWithin(0.1f).of(-2f)
                Truth.assertThat(vec.y).isWithin(0.1f).of(1f)

                (fixtureDef.shape as PolygonShape).getVertex(2, vec)
                Truth.assertThat(vec.x).isWithin(0.1f).of(-2f)
                Truth.assertThat(vec.y).isWithin(0.1f).of(-1f)

                (fixtureDef.shape as PolygonShape).getVertex(3, vec)
                Truth.assertThat(vec.x).isWithin(0.1f).of(2f)
                Truth.assertThat(vec.y).isWithin(0.1f).of(-1f)
            }
        }

        context("creating a fixture") {
            it("should set the friction via a function") {
                val circle = circleDef {
                    friction(100f)
                }
                circle.friction shouldEqual 100f
            }
            it("should set the friction via a field") {
                val circle = circleDef {
                    friction = 100f
                }
                circle.friction shouldEqual 100f
            }
            it("should set the restitution via a function") {
                val circle = circleDef {
                    restitution(100f)
                }
                circle.restitution shouldEqual 100f
            }
            it("should set the restitution via a field") {
                val circle = circleDef {
                    restitution = 100f
                }
                circle.restitution shouldEqual 100f
            }
            it("should set the density via a function") {
                val circle = circleDef {
                    density(100f)
                }
                circle.density shouldEqual 100f
            }
            it("should set the density via a field") {
                val circle = circleDef {
                    density = 100f
                }
                circle.density shouldEqual 100f
            }
            it("should set the sensor via a function") {
                val circle = circleDef {
                    isSensor(true)
                }
                circle.isSensor shouldEqual true
            }
            it("should set the sensor via a field") {
                val circle = circleDef {
                    isSensor = true
                }
                circle.isSensor shouldEqual true
            }
            context("setting filters") {
                it("should use default filters if not specified") {
                    val circle = circleDef()
                    circle.filter.categoryBits shouldEqual 1.toShort()
                    circle.filter.maskBits shouldEqual (-1).toShort()
                    circle.filter.groupIndex shouldEqual 0.toShort()
                }
                it("should set the category bits via function") {
                    val circle = circleDef {
                        filter {
                            categoryBits(2)
                        }
                    }
                    circle.filter.categoryBits shouldEqual 2.toShort()
                }
                it("should set the category bits via field") {
                    val circle = circleDef {
                        filter {
                            categoryBits = 2
                        }
                    }
                    circle.filter.categoryBits shouldEqual 2.toShort()
                }
                it("should set the group index via function") {
                    val circle = circleDef {
                        filter {
                            groupIndex(1)
                        }
                    }
                    circle.filter.groupIndex shouldEqual 1.toShort()
                }
                it("should set the group index  via field") {
                    val circle = circleDef {
                        filter {
                            groupIndex = 1
                        }
                    }
                    circle.filter.groupIndex shouldEqual 1.toShort()
                }
                it("should set the mask bits via function") {
                    val circle = circleDef {
                        filter {
                            maskBits(1)
                        }
                    }
                    circle.filter.maskBits shouldEqual 1.toShort()
                }
                it("should set the mask bits via field") {
                    val circle = circleDef {
                        filter {
                            maskBits = 1
                        }
                    }
                    circle.filter.maskBits shouldEqual 1.toShort()
                }
            }
        }

    }
})