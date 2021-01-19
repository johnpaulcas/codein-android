package com.johnpaulcas.watchly.ui.fragments

import android.os.Build
import android.os.Bundle
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.johnpaulcas.watchly.R
import com.johnpaulcas.watchly.base.BaseFragment
import com.johnpaulcas.watchly.databinding.FragmentTrackDetailBinding
import com.johnpaulcas.watchly.persistence.database.Track
import com.johnpaulcas.watchly.utils.format
import com.johnpaulcas.watchly.viewmodels.TrackDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by johnpaulcas on 18/01/2021.
 */
@AndroidEntryPoint
class TrackDetailFragment: BaseFragment() {

    private var loadOnce = false

    val viewModel: TrackDetailViewModel by viewModels()
    lateinit var binding: FragmentTrackDetailBinding

    override fun onBindLayoutResource(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentTrackDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        viewModel.track.observe(viewLifecycleOwner, Observer { track ->
            if (track != null) {
                populateView(track)
                viewModel.cachedTrackData(track)
            } else {
                if (!loadOnce) {
                    viewModel.loadLastKnowCacheData()
                    loadOnce = true
                }
            }
        })

    }

    private fun populateView(track: Track) {
        binding.tvTitle.text = track.trackName
        binding.tvGenre.text = track.primaryGenreName
        binding.tvPrice.text = "$${track.trackPrice.format(2)}"
        binding.tvDescription.text = track.longDescription

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.tvDescription.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }

        Glide.with(requireContext())
            .load(track.artworkUrl100)
            .placeholder(R.drawable.ic_no_image)
            .encodeQuality(100)
            .centerCrop()
            .into(binding.ivTrackDetail)
    }

}