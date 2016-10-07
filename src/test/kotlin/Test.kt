import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import jkly.box2d.*
import jkly.box2d.dsl.*

object Test {
    init {
        val world = World(Vector2.Zero, false)
        val newBody = addTo(world) {
            body {
                withCircle {

                }
            }
        }

        def {

        }

        addTo(world) {
            body(def { type(BodyDef.BodyType.DynamicBody) }) {
                withCircle {  }
            }
        }

//        val def = world.define().body(active = false) {
//
//            body.add {
//                withCircle {  }
//            } // TODO: this creates the body and you cannot modify the def afterwards
//        }
//        def.def.active = true // TODO: this has no effect
//
//        val anotherBody = def.createBody {
//            this.def.active = true
//        }

        // TODO: create a fixture
        val circle = circle {

        }

        val theFixture = newBody.add(circle)
        // TODO: later on, add the fixture to the body

        world.add {
            body {
                withCircle {
                    radius(1f)
                }
            }
        }

        newBody.add (circle {
            radius(1f)
        })
//        newBody.add {
//            withCircle {
//                radius(5f)
//                isSensor(true)
//                filter {
//                    groupIndex(1)
//                    categoryBits(0x000)
//                }
//            }
//        }

        addTo(world) {
            body {
                withCircle {


                }
            }
        }
    }
}