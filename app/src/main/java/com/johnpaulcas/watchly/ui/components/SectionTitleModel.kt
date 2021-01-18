package com.johnpaulcas.watchly.ui.components

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.johnpaulcas.watchly.R
import com.johnpaulcas.watchly.base.BaseEpoxyHolder

/**
 * Created by johnpaulcas on 19/01/2021.
 */
@EpoxyModelClass(layout = R.layout.item_section_title)
abstract class SectionTitleModel: EpoxyModelWithHolder<SectionTitleModel.SectionTitleViewModel>() {

    @EpoxyAttribute
    lateinit var title: String

    override fun bind(holder: SectionTitleViewModel) {
        super.bind(holder)
        holder.tvTitle.text = title
    }

    inner class SectionTitleViewModel: BaseEpoxyHolder() {
        val tvTitle by bind<AppCompatTextView>(R.id.tvSectionTitle)
    }
}