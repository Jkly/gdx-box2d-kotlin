package jkly.box2d.dsl

import com.badlogic.gdx.physics.box2d.Filter
import kotlin.reflect.KProperty

fun FixtureDsl.filter(configure: FilterDsl.() -> Unit) {
    configure(FilterDsl(this.filter))
}

class FilterDsl(private val filter: Filter) {
    val categoryBits: Short by object {
        operator fun getValue(dsl: FilterDsl, property: KProperty<*>): Short = filter.categoryBits
        operator fun setValue(dsl: FilterDsl, property: KProperty<*>, categoryBits: Short) {
            filter.categoryBits = categoryBits
        }
    }
    val maskBits: Short by object {
        operator fun getValue(dsl: FilterDsl, property: KProperty<*>): Short = filter.maskBits
        operator fun setValue(dsl: FilterDsl, property: KProperty<*>, maskBits: Short) {
            filter.maskBits = maskBits
        }
    }
    val groupIndex: Short by object {
        operator fun getValue(dsl: FilterDsl, property: KProperty<*>): Short = filter.groupIndex
        operator fun setValue(dsl: FilterDsl, property: KProperty<*>, groupIndex: Short) {
            filter.groupIndex = groupIndex
        }
    }

    fun categoryBits(bits: Short) {
        filter.categoryBits = bits
    }

    fun maskBits(bits: Short) {
        filter.maskBits = bits
    }

    fun groupIndex(group: Short) {
        filter.groupIndex = group
    }
}