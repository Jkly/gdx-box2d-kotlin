import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import jkly.box2d.*
import jkly.box2d.dsl.*

object Test {
    init {
        val world = World(Vector2.Zero, false)
        val body = addTo(world) {
            body {

            }
        }

        val bodyDef = world.define().body() {

        }

        bodyDef.bodyDef

        body.add {
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