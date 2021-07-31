package com.haxos.foodity.ui.main.tools.unitconverter

import android.content.Context
import com.haxos.foodity.R
import com.haxos.foodity.data.model.MetricUnit
import com.haxos.foodity.data.model.Volume
import com.haxos.foodity.data.model.Weight

class UnitFactory (
        private val context: Context
) {
    fun create(selectedUnitCategory: String, metricUnit: MetricUnit, unitValue: Double): IUnit {
        return when (selectedUnitCategory) {
            context.getString(R.string.unittype_weight) -> {
                Weight(unitValue, metricUnit)
            }
            context.getString(R.string.unittype_volume) -> {
                Volume(unitValue, metricUnit)
            }
            context.getString(R.string.unittype_temperature) -> {
                TODO()
            }
            else -> TODO()
        }
    }
}
