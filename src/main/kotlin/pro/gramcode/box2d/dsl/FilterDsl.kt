package pro.gramcode.box2d.dsl

import com.badlogic.gdx.physics.box2d.Filter

@Box2dDslMarker
class FilterDsl(private val filter: Filter) {
    var categoryBits: Short
        get() = filter.categoryBits
        set(categoryBits) {
            filter.categoryBits = categoryBits
        }
    var maskBits: Short
        get() = filter.maskBits
        set(maskBits) {
            filter.maskBits = maskBits
        }
    var groupIndex: Short
        get() = filter.groupIndex
        set(groupIndex) {
            filter.groupIndex = groupIndex
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