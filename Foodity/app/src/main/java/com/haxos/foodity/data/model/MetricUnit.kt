package com.haxos.foodity.data.model

import androidx.annotation.StringRes
import com.haxos.foodity.R

enum class MetricUnit(@StringRes val stringRes: Int) {
    Milliliters(R.string.volumeunit_mililiters),
    Liters(R.string.volumeunit_liters),
    Cups(R.string.volumeunit_cups),
    Grams(R.string.weightunit_grams),
    Kilograms(R.string.weightunit_kilograms),
    Pounds(R.string.weightunit_pounds),
    Celsius(R.string.tempunit_celsius),
    Fahrenheit(R.string.tempunit_fahrenheit);
}