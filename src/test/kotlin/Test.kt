import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import jkly.box2d.*
import jkly.box2d.dsl.*

object Test {
    init {
        val world = World(Vector2.Zero, false)
        val newBody = addTo(world) {
            body {
                circle {

                }
            }
        }

        val bodyDef = world.define().body(active = false) {

            body.add {
                circle {  }
            } // TODO: this creates the body and you cannot modify the def afterwards
        }
        bodyDef.bodyDef.active = true // TODO: this has no effect

        val anotherBody = bodyDef.createBody {
            this.bodyDef.active = true
        }

        anotherBody.add {
            circle {
                radius(5f)
                isSensor(true)
                filter {
                    groupIndex(1)
                    categoryBits(0x000)
                }
            }
        }

        addTo(world) {
            body {
                circle {


                }
            }
        }
    }
}