package com.haxos.foodity.data.model

import androidx.annotation.StringRes
import com.haxos.foodity.R

enum class MetricUnit(@StringRes val stringRes: Int) {
    Milliliters(R.string.volumeunit_mililiters),
    Liters(R.string.volumeunit_liters),
    Cups(R.string.volumeunit_cups),
    USTeaspoon(R.string.volumeunit_us_teaspoon),
    USTablespoon(R.string.volumeunit_us_tablespoon),
    USFluidOunce(R.string.volumeunit_us_fluidounce),
    UKTeaspoon(R.string.volumeunit_uk_teaspoon),
    UKTablespoon(R.string.volumeunit_uk_tablespoon),
    UKFluidOunce(R.string.volumeunit_uk_fluidounce),
    Grams(R.string.weightunit_grams),
    Kilograms(R.string.weightunit_kilograms),
    Pounds(R.string.weightunit_pounds),
    Ounces(R.string.weightunit_ounces),
    Celsius(R.string.tempunit_celsius),
    Fahrenheit(R.string.tempunit_fahrenheit),

}