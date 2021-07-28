package com.haxos.foodity.data.model

class Weight(weight: Double, weightUnit: WeightUnit) {
    private val weightInGrams = weight / getMultiplierFor(weightUnit)

    fun getIn(unit: WeightUnit) : Double {
        val multiplier = getMultiplierFor(unit)
        return weightInGrams * multiplier
    }

    private fun getMultiplierFor(unit: WeightUnit) : Double {
        return when (unit) {
            WeightUnit.Grams -> 1.0
            WeightUnit.Kilograms -> 1.0 / 1000
            WeightUnit.Pounds -> 1.0 / 453.59237
        }
    }
}
