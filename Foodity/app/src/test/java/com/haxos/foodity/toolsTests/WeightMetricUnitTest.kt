package com.haxos.foodity.toolsTests

import com.haxos.foodity.data.model.MetricUnit
import com.haxos.foodity.data.model.Weight
import org.junit.Test

class WeightMetricUnitTest {

    @Test
    fun convertGramsToPounds() {
        val weight = Weight(453.59237, MetricUnit.Grams)
        val pounds = weight.getIn(MetricUnit.Pounds)
        assert(pounds == 1.0)
    }

    @Test
    fun convertPoundsToGrams() {
        val weight = Weight(1.0, MetricUnit.Pounds)
        val grams = weight.getIn(MetricUnit.Grams)
        assert(grams == 453.59237)
    }
}