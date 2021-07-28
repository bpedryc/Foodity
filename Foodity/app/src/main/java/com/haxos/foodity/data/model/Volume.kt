package com.haxos.foodity.data.model

import com.haxos.foodity.VolumeUnit

class Volume(volume: Double, volumeUnit: VolumeUnit) {

    private val volumeInMilliliters = volume / getMultiplierFor(volumeUnit)

    fun getIn(unit: VolumeUnit) : Double {
        val multiplier = getMultiplierFor(unit)
        return volumeInMilliliters * multiplier
    }

    private fun getMultiplierFor(unit: VolumeUnit) : Double {
        return when (unit) {
            VolumeUnit.Milliliters -> 1.0
            VolumeUnit.Liters -> 1.0 / 1000
            VolumeUnit.Cups -> 1.0 / 250
        }
    }
}
