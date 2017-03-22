package pro.gramcode.box2d.dsl

import com.badlogic.gdx.physics.box2d.Filter
import kotlin.reflect.KProperty

class FilterDsl(private val filter: Filter) {
    var categoryBits: Short by object {
        operator fun getValue(dsl: FilterDsl, property: KProperty<*>): Short = filter.categoryBits
        operator fun setValue(dsl: FilterDsl, property: KProperty<*>, categoryBits: Short) {
            filter.categoryBits = categoryBits
        }
    }
    var maskBits: Short by object {
        operator fun getValue(dsl: FilterDsl, property: KProperty<*>): Short = filter.maskBits
        operator fun setValue(dsl: FilterDsl, property: KProperty<*>, maskBits: Short) {
            filter.maskBits = maskBits
        }
    }
    var groupIndex: Short by object {
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