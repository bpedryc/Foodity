package com.haxos.foodity.data.model

import com.haxos.foodity.R

class ConverterProductFactory {
    companion object Factory {
        val products: List<ConverterProduct> = listOf(
            ConverterProduct(R.string.product_white_long_rice, 0.74),
            ConverterProduct(R.string.product_white_short_rice, 0.8),
            ConverterProduct(R.string.product_sugar, 0.85),
            ConverterProduct(R.string.product_butter, 0.955),
            ConverterProduct(R.string.product_almonds, 0.613),
            ConverterProduct(R.string.product_canola_oil, 0.909),
            ConverterProduct(R.string.product_olive_oil, 0.9),
            ConverterProduct(R.string.product_soy_sauce, 1.12),
            ConverterProduct(R.string.product_honey, 1.44),
            ConverterProduct(R.string.product_maple_syrup, 1.01)
        )
    }
}