package com.johnpaulcas.watchly.ui.components

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.johnpaulcas.watchly.R
import com.johnpaulcas.watchly.base.BaseEpoxyHolder
import com.johnpaulcas.watchly.persistence.database.Track
import java.text.MessageFormat

/**
 * Created by johnpaulcas on 19/01/2021.
 */
@EpoxyModelClass(layout = R.layout.item_track)
abstract class TrackItemModel: EpoxyModelWithHolder<TrackItemModel.TrackItemViewModel>() {

    @EpoxyAttribute
    lateinit var track: Track
    @EpoxyAttribute
    lateinit var context: Context

    override fun bind(holder: TrackItemViewModel) {
        super.bind(holder)
        holder.tvTitle.text = if (track.trackName.isNotBlank()) track.trackName else  ""
        holder.tvGenre.text = track.primaryGenreName
        holder.tvPrice.text = if (track.trackPrice > 0) MessageFormat.format("$ {0}", track.trackPrice) else "Free!"
        val glideRequestOptions = RequestOptions()
//            .placeholder(R.drawable.ic_launcher_background)

        Glide.with(context)
            .setDefaultRequestOptions(glideRequestOptions)
            .load(track.artworkUrl100)
            .into(holder.ivTrack)
    }

    inner class TrackItemViewModel: BaseEpoxyHolder() {
        val ivTrack by bind<AppCompatImageView>(R.id.ivTrack)
        val tvTitle by bind<AppCompatTextView>(R.id.tvTitle)
        val tvGenre by bind<AppCompatTextView>(R.id.tvGenre)
        val tvPrice by bind<AppCompatTextView>(R.id.tvPrice)
    }
}