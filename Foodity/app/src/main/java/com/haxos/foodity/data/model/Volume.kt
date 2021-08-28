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
            MetricUnit.UKFluidOunce -> 1.0 / 28.4131
            MetricUnit.USFluidOunce -> 1.0 / 29.5735
            MetricUnit.UKTablespoon -> 1.0 / 17.7582
            MetricUnit.USTablespoon -> 1.0 / 14.8
            MetricUnit.UKTeaspoon -> 1.0 / 5.9194
            MetricUnit.USTeaspoon -> 1.0 / 4.93
            else -> 1.0 //TODO: handle as error
        }
    }
}
