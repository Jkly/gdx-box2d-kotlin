package pro.gramcode.box2d.dsl

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

object BodyDefDslSpec : Spek({
    describe("a rigidbody definition DSL") {
        context("creating a body def") {
            it("should set the type") {
                val bodyDef = def {
                    type = BodyDef.BodyType.DynamicBody
                }
                bodyDef.type shouldEqual BodyDef.BodyType.DynamicBody
            }
            it("should set whether the body is active when started") {
                val bodyDef = def {
                    active = false
                }
                bodyDef.active shouldEqual false
            }
            it("should set whether the body should never fall asleep") {
                val bodyDef = def {
                    allowSleep = false
                }
                bodyDef.allowSleep shouldEqual false
            }
            it("should set whether the body should be initially awake") {
                val bodyDef = def {
                    awake = false
                }
                bodyDef.awake shouldEqual false
            }
            it("should set the world angle of the body") {
                val bodyDef = def {
                    angle = 90f
                }
                bodyDef.angle shouldEqual 90f
            }
            it("should set angular damping effect") {
                val bodyDef = def {
                    angularDamping = 90f
                }
                bodyDef.angularDamping shouldEqual 90f
            }
            it("should set angular velocity") {
                val bodyDef = def {
                    angularVelocity = 90f
                }
                bodyDef.angularVelocity shouldEqual 90f
            }
            it("should prevent tunneling") {
                val bodyDef = def {
                    bullet = true
                }
                bodyDef.bullet shouldEqual true
            }
            it("should prevent body from rotating") {
                val bodyDef = def {
                    fixedRotation = true
                }
                bodyDef.fixedRotation shouldEqual true
            }
            it("should set the amount gravity effects the body") {
                val bodyDef = def {
                    gravityScale = 0f
                }
                bodyDef.gravityScale shouldEqual 0f
            }
            it("should set the linear damping") {
                val bodyDef = def {
                    linearDamping = 90f
                }
                bodyDef.linearDamping shouldEqual 90f
            }
            it("should set the linear velocity") {
                val bodyDef = def {
                    linearVelocity = Vector2(1f,1f)
                }
                bodyDef.linearVelocity shouldEqual Vector2(1f,1f)
            }
            it("should set the linear velocity") {
                val bodyDef = def {
                    linearDamping = 90f
                }
                bodyDef.linearDamping shouldEqual 90f
            }
            it("should set the linear velocity") {
                val bodyDef = def {
                    position = Vector2(1f,1f)
                }
                bodyDef.position shouldEqual Vector2(1f,1f)
            }
            it("should have a DSL that reads more naturally for creating standalone body definitions") {
                val bodyDef = defineBody {
                    type = BodyDef.BodyType.DynamicBody
                }
                bodyDef.type shouldEqual BodyDef.BodyType.DynamicBody
            }
        }

        context("creating a body def via a convenience factory method") {
            it("should set the type") {
                val bodyDef = bodyDef(
                    type = BodyDef.BodyType.DynamicBody
                )
                bodyDef.type shouldEqual BodyDef.BodyType.DynamicBody
            }
            it("should set whether the body is active when started") {
                val bodyDef = bodyDef(
                    active = false
                )
                bodyDef.active shouldEqual false
            }
            it("should set whether the body should never fall asleep") {
                val bodyDef = bodyDef(
                    allowSleep = false
                )
                bodyDef.allowSleep shouldEqual false
            }
            it("should set whether the body should be initially awake") {
                val bodyDef = bodyDef(
                    awake = false
                )
                bodyDef.awake shouldEqual false
            }
            it("should set the world angle of the body") {
                val bodyDef = bodyDef(
                    angle = 90f
                )
                bodyDef.angle shouldEqual 90f
            }
            it("should set angular damping effect") {
                val bodyDef = bodyDef(
                    angularDamping = 90f
                )
                bodyDef.angularDamping shouldEqual 90f
            }
            it("should set angular velocity") {
                val bodyDef = bodyDef(
                    angularVelocity = 90f
                )
                bodyDef.angularVelocity shouldEqual 90f
            }
            it("should prevent tunneling") {
                val bodyDef = bodyDef(
                    bullet = true
                )
                bodyDef.bullet shouldEqual true
            }
            it("should prevent body from rotating") {
                val bodyDef = bodyDef(
                    fixedRotation = true
                )
                bodyDef.fixedRotation shouldEqual true
            }
            it("should set the amount gravity effects the body") {
                val bodyDef = bodyDef(
                    gravityScale = 0f
                )
                bodyDef.gravityScale shouldEqual 0f
            }
            it("should set the linear damping") {
                val bodyDef = bodyDef(
                    linearDamping = 90f
                )
                bodyDef.linearDamping shouldEqual 90f
            }
            it("should set the linear velocity") {
                val bodyDef = bodyDef(
                    linearVelocity = Vector2(1f,1f)
                )
                bodyDef.linearVelocity shouldEqual Vector2(1f,1f)
            }
            it("should set the linear velocity") {
                val bodyDef = bodyDef(
                    linearDamping = 90f
                )
                bodyDef.linearDamping shouldEqual 90f
            }
            it("should set the linear velocity") {
                val bodyDef = bodyDef(
                    position = Vector2(1f,1f)
                )
                bodyDef.position shouldEqual Vector2(1f,1f)
            }
        }
    }
})