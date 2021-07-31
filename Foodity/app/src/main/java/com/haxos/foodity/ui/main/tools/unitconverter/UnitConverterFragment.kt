package com.haxos.foodity.ui.main.tools.unitconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.haxos.foodity.R
import com.haxos.foodity.data.model.MetricUnit
import com.haxos.foodity.databinding.FragmentUnitconverterBinding
import com.haxos.foodity.utils.enableBackButton
import com.haxos.foodity.utils.toPrettyString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UnitConverterFragment : Fragment() {

    private lateinit var binding : FragmentUnitconverterBinding
    private lateinit var unitFactory: UnitFactory
    private lateinit var selectedUnitCategory : String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUnitconverterBinding.inflate(inflater)

        unitFactory = UnitFactory(requireContext())

        binding.apply {
            toolsToolbar.enableBackButton(requireActivity())

            spinnerFirstunitType.onItemSelectedListener = OnUnitSelectedListener(
                    fieldToRefresh = ConverterField.First)
            spinnerSecondunitType.onItemSelectedListener = OnUnitSelectedListener(
                    fieldToRefresh = ConverterField.Second)

            editFirstunit.addTextChangedListener(
                    ConverterFieldTextListener(ConverterField.First))

            editSecondunit.addTextChangedListener(
                            ConverterFieldTextListener(ConverterField.Second))

            spinnerUnitcategory.adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.unittypes,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerUnitcategory.adapter = adapter
                spinnerUnitcategory.onItemSelectedListener = CategorySpinnerSelectListener()
            }
        }
        return binding.root
    }

    inner class OnUnitSelectedListener(private val fieldToRefresh: ConverterField)
        : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            refreshConverterField(fieldToRefresh)
        }
        override fun onNothingSelected(p0: AdapterView<*>?) {}
    }

    inner class ConverterFieldTextListener (private val field: ConverterField) : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(editable: Editable) {
            var fieldEditText = binding.editFirstunit
            if (field == ConverterField.Second) {
                fieldEditText = binding.editSecondunit
            }
            if (!fieldEditText.hasFocus()) {
                return
            }

            var fieldToUpdate = ConverterField.Second
            if (field == ConverterField.Second) {
                fieldToUpdate = ConverterField.First
            }
            refreshConverterField(fieldToUpdate)
        }
    }

    inner class CategorySpinnerSelectListener : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {}
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            selectedUnitCategory = parent.selectedItem as String

            val availableMetricUnits : Array<MetricUnit> = when (selectedUnitCategory) {
                getString(R.string.unittype_weight) -> arrayOf(
                        MetricUnit.Grams, MetricUnit.Kilograms, MetricUnit.Pounds)
                getString(R.string.unittype_volume) -> arrayOf(
                        MetricUnit.Milliliters, MetricUnit.Liters, MetricUnit.Cups)
                getString(R.string.unittype_temperature) -> arrayOf(
                        MetricUnit.Celsius, MetricUnit.Fahrenheit)
                else -> TODO()
            }
            val adapter = ArrayAdapter(this@UnitConverterFragment.requireContext(),
                    android.R.layout.simple_list_item_1, availableMetricUnits)

            binding.spinnerFirstunitType.adapter = adapter
            binding.spinnerSecondunitType.adapter = adapter

            binding.editFirstunit.setText("0")
            binding.editSecondunit.setText("0")
        }
    }

    private fun refreshConverterField(fieldToUpdate: ConverterField) {
        var affectedEditText = binding.editSecondunit
        var affectedUnitSelector = binding.spinnerSecondunitType
        var editedEditText = binding.editFirstunit
        var editedUnitSelector = binding.spinnerFirstunitType

        if (fieldToUpdate == ConverterField.First) {
            affectedEditText = binding.editFirstunit
            affectedUnitSelector = binding.spinnerFirstunitType
            editedEditText = binding.editSecondunit
            editedUnitSelector = binding.spinnerSecondunitType
        }

        try {
            val editedUnitValue = editedEditText.text.toString().toDouble()
            val editedUnit = editedUnitSelector.selectedItem as MetricUnit

            val unit = unitFactory.create(selectedUnitCategory, editedUnit, editedUnitValue)

            val affectedUnit = affectedUnitSelector.selectedItem as MetricUnit
            val affectedUnitValue = unit.getIn(affectedUnit)

            affectedEditText.setText(affectedUnitValue.toPrettyString())
        } catch (ex: NumberFormatException) { }
    }

    enum class ConverterField {
        First,
        Second
    }
}