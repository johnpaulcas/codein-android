package com.johnpaulcas.watchly.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Created by johnpaulcas on 19/01/2021.
 */
abstract class BaseFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return onBindLayoutResource(inflater, container)
    }

    abstract fun onBindLayoutResource(inflater: LayoutInflater, container: ViewGroup?): View


}