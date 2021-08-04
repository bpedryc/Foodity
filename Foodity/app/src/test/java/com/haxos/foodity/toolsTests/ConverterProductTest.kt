package com.haxos.foodity.toolsTests

import com.haxos.foodity.R
import com.haxos.foodity.data.model.*
import org.junit.Test

class ConverterProductTest {

    @Test
    fun convertGramsOfRiceToCup() {
        val shortWhiteRice = getShortWhiteRice()
        val weightInGrams = Weight(200.0, MetricUnit.Grams)
        shortWhiteRice.setWeight(weightInGrams)
        val volumeInCups = shortWhiteRice.getVolume().getIn(MetricUnit.Cups)

        assert(volumeInCups == 1.0)
    }

    @Test
    fun convertRiceCupToGrams() {
        val shortWhiteRice = getShortWhiteRice()
        val volumeInCups = Volume(1.0, MetricUnit.Cups)
        shortWhiteRice.setVolume(volumeInCups)
        val gramsOfRice = shortWhiteRice.getWeight().getIn(MetricUnit.Grams)

        assert(gramsOfRice == 200.0)
    }

    private fun getShortWhiteRice() : ConverterProduct =
        ConverterProductFactory.products.first {
            it.name == R.string.product_white_short_rice
        }
}