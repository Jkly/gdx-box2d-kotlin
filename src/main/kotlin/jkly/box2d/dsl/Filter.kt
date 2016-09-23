package jkly.box2d.dsl

import com.badlogic.gdx.physics.box2d.Filter

fun FixtureDsl.filter(configure: FilterDsl.() -> Unit) {
    configure(FilterDsl(this.filter))
}

class FilterDsl(private val filter: Filter) {
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