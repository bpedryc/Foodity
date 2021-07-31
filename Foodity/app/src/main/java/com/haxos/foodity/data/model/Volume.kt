package com.haxos.foodity.data.model

import com.haxos.foodity.ui.main.tools.unitconverter.IMetricUnit

class Volume(volume: Double, metricUnit: MetricUnit) : IMetricUnit {

    private val volumeInMilliliters = volume / getMultiplierFor(metricUnit)

    override fun getIn(metricUnit: MetricUnit) : Double {
        val multiplier = getMultiplierFor(metricUnit)
        return volumeInMilliliters * multiplier
    }

    private fun getMultiplierFor(metricUnit: MetricUnit) : Double {
        return when (metricUnit) {
            MetricUnit.Milliliters -> 1.0
            MetricUnit.Liters -> 1.0 / 1000
            MetricUnit.Cups -> 1.0 / 250
            else -> 1.0 //TODO: handle as error
        }
    }
}
