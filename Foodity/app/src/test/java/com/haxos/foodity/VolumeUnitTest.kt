package com.haxos.foodity

import com.haxos.foodity.data.model.Volume
import com.haxos.foodity.data.model.VolumeUnit
import org.junit.Test

class VolumeUnitTest {

    @Test
    fun convertCupsToMilliliters() {
        val volume = Volume(1.0, VolumeUnit.Cups)
        val milliliters = volume.getIn(VolumeUnit.Milliliters)
        assert(milliliters == 250.0)
    }

    @Test
    fun convertMillilitersToCups() {
        val volume = Volume(250.0, VolumeUnit.Milliliters)
        val cups = volume.getIn(VolumeUnit.Cups)
        assert(cups == 1.0)
    }
}