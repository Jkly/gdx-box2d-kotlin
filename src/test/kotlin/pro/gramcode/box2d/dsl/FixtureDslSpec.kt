package pro.gramcode.box2d.dsl

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
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