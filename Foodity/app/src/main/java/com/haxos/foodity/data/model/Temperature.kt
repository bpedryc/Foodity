package com.haxos.foodity.data.model

import com.haxos.foodity.ui.main.tools.unitconverter.IMetricUnit

class Temperature(temperature: Double, unit: MetricUnit) : IMetricUnit {
    private val tempInCelsius = calculateToCelsius(temperature, unit)

    override fun getIn(metricUnit: MetricUnit): Double {
        return when (metricUnit) {
            MetricUnit.Fahrenheit -> tempInCelsius * 9.0/5 + 32
            MetricUnit.Celsius -> tempInCelsius
            else -> throw Exception("Unsupported unit for temperature")
        }
    }

    private fun calculateToCelsius(unitValue: Double, unit: MetricUnit): Double {
        return when (unit) {
            MetricUnit.Fahrenheit -> (unitValue - 32) * 5.0 / 9
            MetricUnit.Celsius -> unitValue
            else -> throw Exception("Unsupported unit for temperature")
        }
    }

}