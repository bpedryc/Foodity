package com.haxos.foodity.toolsTests

import com.haxos.foodity.data.model.MetricUnit
import com.haxos.foodity.data.model.Temperature
import org.junit.Test

class TempMetricUnitTest {

    @Test
    fun celsiusToFahrenheit() {
        val celsius = 100.0
        val temp = Temperature(celsius, MetricUnit.Celsius)
        assert(temp.getIn(MetricUnit.Fahrenheit) == 212.0)
    }

    @Test
    fun fahrenheitToCelsius() {
        val fahrenheit = 293.0
        val temp = Temperature(fahrenheit, MetricUnit.Fahrenheit)
        assert(temp.getIn(MetricUnit.Celsius) == 145.0)
    }
}