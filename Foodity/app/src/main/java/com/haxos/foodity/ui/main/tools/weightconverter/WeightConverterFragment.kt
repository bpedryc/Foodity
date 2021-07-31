package com.haxos.foodity.ui.main.tools.weightconverter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.haxos.foodity.data.model.*
import com.haxos.foodity.data.model.MetricUnit
import com.haxos.foodity.databinding.FragmentWeightconverterBinding
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

            editWeight.addTextChangedListener {
                if (!editWeight.hasFocus()) {
                    return@addTextChangedListener
                }
                try {
                    val weightValue = editWeight.text.toString().toDouble()
                    val weight = Weight(weightValue, MetricUnit.Grams)
                    selectedProduct.setWeight(weight)
                    lastProcessedVolume = selectedProduct.getVolume()
                    val volume = lastProcessedVolume.getIn(MetricUnit.Milliliters)
                    editVolume.setText(volume.toPrettyString())
                } catch (ex: NumberFormatException) { }
            }

            editVolume.addTextChangedListener {
                if (!editVolume.hasFocus()) {
                    return@addTextChangedListener
                }
                try {
                    val volumeValue = editVolume.text.toString().toDouble()
                    lastProcessedVolume = Volume(volumeValue, MetricUnit.Milliliters)
                    selectedProduct.setVolume(lastProcessedVolume)
                    val weight = selectedProduct.getWeight().getIn(MetricUnit.Grams)
                    editWeight.setText(weight.toPrettyString())
                } catch (ex: NumberFormatException) { }
            }

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
            val volume = lastProcessedVolume.getIn(MetricUnit.Milliliters)
            binding.editVolume.setText(volume.toPrettyString())

            val weight =  selectedProduct.getWeight().getIn(MetricUnit.Grams)
            binding.editWeight.setText(weight.toPrettyString())
        }
        override fun onNothingSelected(p0: AdapterView<*>?) {}
    }

    /*inner class ConverterEditTextWatcher(
            private val editedEditText: EditText,
            private val affectedEditText: EditText
    ) : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(editable: Editable) {
            if (!editedEditText.hasFocus()) {
                return
            }
            try {
                val editedValue = editedConverterField.ge



                val weightValue = editWeight.text.toString().toDouble()
                val weight = Weight(weightValue, WeightUnit.Grams)
                selectedProduct.setWeight(weight)
                lastProcessedVolume = selectedProduct.getVolume()
                val volume = lastProcessedVolume.getIn(VolumeUnit.Milliliters)
                editVolume.setText(volume.toPrettyString())
            } catch (ex: NumberFormatException) { }
        }
    }*/
}