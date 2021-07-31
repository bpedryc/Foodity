package com.haxos.foodity.data.model

import androidx.annotation.StringRes

class ConverterProduct (
    @StringRes val name: Int,
    private val milliliterToGrams: Double
) {
    var weightInGrams : Double = 0.0

    fun setWeight(weight: Weight) {
        weightInGrams = weight.getIn(MetricUnit.Grams)
    }
    fun getWeight(): Weight {
        return Weight(weightInGrams, MetricUnit.Grams)
    }

    fun setVolume(volume: Volume) {
        val milliliters = volume.getIn(MetricUnit.Milliliters)
        val grams = toGrams(milliliters)
        weightInGrams = grams
    }
    fun getVolume(): Volume {
        val milliliters = toMilliliters(weightInGrams)
        return Volume(milliliters, MetricUnit.Milliliters)
    }

    private fun toGrams(milliliters: Double) : Double {
        return milliliterToGrams * milliliters
    }
    private fun toMilliliters(grams: Double) : Double {
        return grams / milliliterToGrams
    }
}