package com.johnpaulcas.watchly.utils

fun Double.format(digits: Int) = "%.${digits}f".format(this)
