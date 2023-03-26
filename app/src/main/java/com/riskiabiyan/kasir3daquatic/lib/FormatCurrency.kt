package com.riskiabiyan.kasir3daquatic.lib

import java.text.NumberFormat
import java.util.Locale

class FormatCurrency(
    val number: Int?
) {
    fun formatRupiah(): String{
        val locale:Locale = Locale("in", "ID")
        val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(locale)
        return formatRupiah.format(number)
    }
}