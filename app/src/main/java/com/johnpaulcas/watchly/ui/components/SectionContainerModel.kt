package com.johnpaulcas.watchly.ui.components

import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.johnpaulcas.watchly.R
import com.johnpaulcas.watchly.base.BaseEpoxyHolder

/**
 * Created by johnpaulcas on 19/01/2021.
 */
@EpoxyModelClass(layout = R.layout.container_section)
abstract class SectionContainerModel: EpoxyModelWithHolder<SectionContainerModel.SectionContainerViewHolder>() {

    inner class SectionContainerViewHolder: BaseEpoxyHolder() {

    }
}