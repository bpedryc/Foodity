package com.haxos.foodity

import com.haxos.foodity.data.model.Weight
import com.haxos.foodity.data.model.WeightUnit
import org.junit.Test

class WeightUnitTest {

    @Test
    fun convertGramsToPounds() {
        val weight = Weight(453.59237, WeightUnit.Grams)
        val pounds = weight.getIn(WeightUnit.Pounds)
        assert(pounds == 1.0)
    }

    @Test
    fun convertPoundsToGrams() {
        val weight = Weight(1.0, WeightUnit.Pounds)
        val grams = weight.getIn(WeightUnit.Grams)
        assert(grams == 453.59237)
    }
}