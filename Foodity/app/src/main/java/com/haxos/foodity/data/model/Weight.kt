package com.haxos.foodity.data.model

import com.haxos.foodity.ui.main.tools.unitconverter.IMetricUnit

class Weight(weight: Double, metricUnit: MetricUnit) : IMetricUnit {
    private val weightInGrams = weight / getMultiplierFor(metricUnit)

    override fun getIn(metricUnit: MetricUnit) : Double {
        val multiplier = getMultiplierFor(metricUnit)
        return weightInGrams * multiplier
    }

    private fun getMultiplierFor(metricUnit: MetricUnit) : Double {
        return when (metricUnit) {
            MetricUnit.Grams -> 1.0
            MetricUnit.Kilograms -> 1.0 / 1000
            MetricUnit.Pounds -> 1.0 / 453.59237
            MetricUnit.Ounces -> 1.0 / 28.34952
            else -> 1.0 //TODO: handle as error?
        }
    }
}
