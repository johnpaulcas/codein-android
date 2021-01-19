package com.johnpaulcas.watchly.utils

/**
 * Created by johnpaulcas on 20/01/2021.
 */
interface OnItemClickListener<in T> {
    fun onItemClick(obj: T)
}