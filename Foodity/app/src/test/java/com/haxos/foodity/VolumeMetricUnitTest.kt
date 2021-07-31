package com.haxos.foodity

import com.haxos.foodity.data.model.MetricUnit
import com.haxos.foodity.data.model.Volume
import org.junit.Test

class VolumeMetricUnitTest {

    @Test
    fun convertCupsToMilliliters() {
        val volume = Volume(1.0, MetricUnit.Cups)
        val milliliters = volume.getIn(MetricUnit.Milliliters)
        assert(milliliters == 250.0)
    }

    @Test
    fun convertMillilitersToCups() {
        val volume = Volume(250.0, MetricUnit.Milliliters)
        val cups = volume.getIn(MetricUnit.Cups)
        assert(cups == 1.0)
    }
}