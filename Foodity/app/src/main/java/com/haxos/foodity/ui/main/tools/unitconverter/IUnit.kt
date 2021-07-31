package com.haxos.foodity.ui.main.tools.unitconverter

import com.haxos.foodity.data.model.MetricUnit

interface IUnit {
    fun getIn(metricUnit: MetricUnit): Double
}
