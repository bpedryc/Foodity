package com.haxos.foodity.data.model

import androidx.annotation.StringRes

class ConverterProduct (
    @StringRes val name: Int,
    private val milliliterToGrams: Double
) {
    var weightInGrams : Double = 0.0

    fun setWeight(weight: Weight) {
        weightInGrams = weight.getIn(WeightUnit.Grams)
    }
    fun getWeight(): Weight {
        return Weight(weightInGrams, WeightUnit.Grams)
    }

    fun setVolume(volume: Volume) {
        val milliliters = volume.getIn(VolumeUnit.Milliliters)
        val grams = toGrams(milliliters)
        weightInGrams = grams
    }
    fun getVolume(): Volume {
        val milliliters = toMilliliters(weightInGrams)
        return Volume(milliliters, VolumeUnit.Milliliters)
    }

    private fun toGrams(milliliters: Double) : Double {
        return milliliterToGrams * milliliters
    }
    private fun toMilliliters(grams: Double) : Double {
        return grams / milliliterToGrams
    }
}