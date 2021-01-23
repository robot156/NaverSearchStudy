package com.poly.test.naversearchstudy.utils

import java.text.DecimalFormat

object ExtensionFunc {

    fun Int?.formedPrice(): String {
        val decimalFormat = DecimalFormat("#,###")
        return decimalFormat.format(this)
    }

}