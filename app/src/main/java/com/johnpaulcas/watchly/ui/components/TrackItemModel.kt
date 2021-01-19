package com.johnpaulcas.watchly.ui.components

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.johnpaulcas.watchly.R
import com.johnpaulcas.watchly.base.BaseEpoxyHolder
import com.johnpaulcas.watchly.persistence.database.Track
import com.johnpaulcas.watchly.utils.OnItemClickListener
import com.johnpaulcas.watchly.utils.format

/**
 * Created by johnpaulcas on 19/01/2021.
 */
@EpoxyModelClass(layout = R.layout.item_track)
abstract class TrackItemModel: EpoxyModelWithHolder<TrackItemModel.TrackItemViewModel>() {

    @EpoxyAttribute
    lateinit var track: Track
    @EpoxyAttribute
    lateinit var context: Context
    @JvmField
    @EpoxyAttribute
    var onItemClickListener: OnItemClickListener<Track>? = null

    override fun bind(holder: TrackItemViewModel) {
        super.bind(holder)
        holder.tvTitle.text = track.trackName
        holder.tvGenre.text = track.primaryGenreName
        holder.tvPrice.text = track.trackPrice.format(2)

        Glide.with(context)
            .load(track.artworkUrl100)
            .placeholder(R.drawable.ic_no_image)
            .encodeQuality(100)
            .centerCrop()
            .into(holder.ivTrack)

        holder.itemContainer.setOnClickListener {
            onItemClickListener?.onItemClick(track)
        }

    }

    inner class TrackItemViewModel: BaseEpoxyHolder() {
        val ivTrack by bind<AppCompatImageView>(R.id.ivTrack)
        val tvTitle by bind<AppCompatTextView>(R.id.tvTitle)
        val tvGenre by bind<AppCompatTextView>(R.id.tvGenre)
        val tvPrice by bind<AppCompatTextView>(R.id.tvPrice)
        val itemContainer by bind<MaterialCardView>(R.id.itemContainer)
    }
}