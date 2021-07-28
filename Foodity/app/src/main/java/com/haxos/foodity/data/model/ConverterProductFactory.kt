package com.haxos.foodity.data.model

import com.haxos.foodity.R

class ConverterProductFactory {
    companion object Factory {
        val products: List<ConverterProduct> = listOf(
            ConverterProduct(R.string.product_white_long_rice, 0.74),
            ConverterProduct(R.string.product_white_short_rice, 0.8)
        )
    }
}