package com.haxos.foodity.ui.main.tools.unitconverter

import com.haxos.foodity.data.model.MetricUnit

interface IMetricUnit {
    fun getIn(metricUnit: MetricUnit): Double
}
