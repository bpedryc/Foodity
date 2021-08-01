package com.haxos.foodity.ui.main.tools.weightconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.haxos.foodity.data.model.*
import com.haxos.foodity.databinding.FragmentWeightconverterBinding
import com.haxos.foodity.ui.main.tools.unitconverter.UnitAdapter
import com.haxos.foodity.utils.enableBackButton
import com.haxos.foodity.utils.toPrettyString
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WeightConverterFragment : Fragment()
{
    private lateinit var binding: FragmentWeightconverterBinding
    @Inject lateinit var converterViewModel: WeightConverterViewModel

    private lateinit var selectedProduct: ConverterProduct
    private var lastProcessedVolume: Volume = Volume(0.0, MetricUnit.Milliliters)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentWeightconverterBinding.inflate(inflater)

        binding.apply {
            toolsToolbar.enableBackButton(requireActivity())

            spinnerVolumeunit.adapter = UnitAdapter(requireContext(),
                    listOf(MetricUnit.Milliliters, MetricUnit.Liters, MetricUnit.Cups))
            spinnerVolumeunit.onItemSelectedListener = OnUnitSelectedListener(ConverterField.Volume)

            spinnerWeightunit.adapter = UnitAdapter(requireContext(),
                    listOf(MetricUnit.Grams, MetricUnit.Kilograms, MetricUnit.Pounds))
            spinnerWeightunit.onItemSelectedListener = OnUnitSelectedListener(ConverterField.Weight)

            editWeight.addTextChangedListener(ConverterFieldTextListener(ConverterField.Weight))
            editVolume.addTextChangedListener(ConverterFieldTextListener(ConverterField.Volume))

            spinnerProduct.adapter = ProductsAdapter(
                    requireActivity(), ConverterProductFactory.products)
            spinnerProduct.onItemSelectedListener = ProductSpinnerClickListener()
        }

        return binding.root
    }

    inner class ProductSpinnerClickListener : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            selectedProduct = parent.selectedItem as ConverterProduct
            selectedProduct.setVolume(lastProcessedVolume)

            val volume = selectedProduct.getVolume()
                    .getIn(binding.spinnerVolumeunit.selectedItem as MetricUnit)
            binding.editVolume.setText(volume.toPrettyString())

            val weight =  selectedProduct.getWeight()
                    .getIn(binding.spinnerWeightunit.selectedItem as MetricUnit)
            binding.editWeight.setText(weight.toPrettyString())
        }
        override fun onNothingSelected(p0: AdapterView<*>?) {}
    }

    inner class ConverterFieldTextListener(private val field: ConverterField) : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(editable: Editable) {
            var fieldEditText = binding.editVolume
            if (field == ConverterField.Weight) {
                fieldEditText = binding.editWeight
            }
            if (!fieldEditText.hasFocus()) {
                return
            }

            try {
                if (field == ConverterField.Volume) {
                    refreshWeightField()
                } else {
                    refreshVolumeField()
                }
            } catch (ex: NumberFormatException) { }
        }
    }

    inner class OnUnitSelectedListener(private val field: ConverterField) : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, p1: View?, p2: Int, p3: Long) {
            if (field == ConverterField.Volume) {
                val volume = selectedProduct
                        .getVolume()
                        .getIn(parent.selectedItem as MetricUnit)
                binding.editVolume.setText(volume.toPrettyString())
            } else {
                val weight = selectedProduct
                        .getWeight()
                        .getIn(parent.selectedItem as MetricUnit)
                binding.editWeight.setText(weight.toPrettyString())
            }
        }
        override fun onNothingSelected(p0: AdapterView<*>?) {}
    }

    fun refreshVolumeField() {
        val weightValue = binding.editWeight.text.toString().toDouble()
        val weightUnit = binding.spinnerWeightunit.selectedItem as MetricUnit
        val weight = Weight(weightValue, weightUnit)

        selectedProduct.setWeight(weight)
        lastProcessedVolume = selectedProduct.getVolume()

        val volumeUnit = binding.spinnerVolumeunit.selectedItem as MetricUnit
        val volume = lastProcessedVolume.getIn(volumeUnit)
        binding.editVolume.setText(volume.toPrettyString())
    }

    fun refreshWeightField() {
        val volumeValue = binding.editVolume.text.toString().toDouble()
        val volumeUnit = binding.spinnerVolumeunit.selectedItem as MetricUnit

        lastProcessedVolume = Volume(volumeValue, volumeUnit)
        selectedProduct.setVolume(lastProcessedVolume)

        val weightUnit = binding.spinnerWeightunit.selectedItem as MetricUnit
        val weight = selectedProduct.getWeight().getIn(weightUnit)
        binding.editWeight.setText(weight.toPrettyString())
    }

    enum class ConverterField {
        Weight,
        Volume
    }
}