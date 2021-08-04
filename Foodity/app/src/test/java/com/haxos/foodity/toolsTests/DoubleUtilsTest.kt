package com.haxos.foodity.toolsTests

import com.haxos.foodity.utils.round
import com.haxos.foodity.utils.toPrettyString
import org.junit.Test

class DoubleUtilsTest {

    @Test
    fun roundToOneDecimal() {
        val double = 2.135
        assert(double.round(1) == 2.1)
    }

    @Test
    fun roundToTwoDecimals() {
        val double = 9.1456
        assert(double.round(2) == 9.15)
    }

    @Test
    fun roundToThreeDecimals() {
        val double = 1.99934
        assert (double.round(3) == 1.999)
    }

    @Test
    fun doubleToStringCutDecimal() {
        val double = 2.0
        assert(double.toPrettyString() == "2")
    }

    @Test
    fun doubleToStringCropDecimalZeros() {
        val double = 14.30
        assert(double.toPrettyString() == "14.3")
    }

    @Test
    fun doubleToStringCropLongDecimal() {
        val double = 3.333333333333
        assert(double.toPrettyString() == "3.333")
    }

    @Test
    fun doubleToStringKeepDecimal() {
        val double = 1.92
        assert (double.toPrettyString() == "1.92")
    }
}