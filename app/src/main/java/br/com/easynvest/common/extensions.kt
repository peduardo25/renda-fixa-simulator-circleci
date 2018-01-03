package br.com.easynvest.common

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private val formRegex = Regex("[^0-9]*")
private var apiDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
private var input = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
private var output = SimpleDateFormat("yyyy-MM-dd", Locale.US)
private val locale = Locale("pt", "BR")

fun String.unmask(): String = formRegex.replace(this, "")

fun String.convertDate(): String {
    val parsed = input.parse(this)
    return output.format(parsed)
}

fun String.localDateFormat(): String {
    val parsed = apiDateFormat.parse(this)
    return input.format(parsed)
}

fun Double.toBrCurrency(): String {
    return NumberFormat.getCurrencyInstance(locale).format((this / 100)).toString()
}