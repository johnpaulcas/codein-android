package com.johnpaulcas.watchly.ui.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.carousel
import com.google.android.material.dialog.MaterialDialogs
import com.johnpaulcas.watchly.base.BaseFragment
import com.johnpaulcas.watchly.persistence.database.Track
import com.johnpaulcas.watchly.databinding.FragmentHomeBinding
import com.johnpaulcas.watchly.ui.components.TrackItemModel_
import com.johnpaulcas.watchly.ui.components.sectionContainer
import com.johnpaulcas.watchly.ui.components.sectionTitle
import com.johnpaulcas.watchly.utils.AppConstant
import com.johnpaulcas.watchly.utils.OnItemClickListener
import com.johnpaulcas.watchly.utils.Status
import com.johnpaulcas.watchly.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by johnpaulcas on 18/01/2021.
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val TAG = HomeFragment::class.java.simpleName

    private val sections = listOf<String>(
        AppConstant.Genre.SCI_FI,
        AppConstant.Genre.ACTION_AND_ADVENTURE,
        AppConstant.Genre.COMEDY,
        AppConstant.Genre.ROMANCE,
        AppConstant.Genre.DRAMA,
        AppConstant.Genre.KINDS_AND_FAMILY,
        AppConstant.Genre.DOCUMENTARY,
        AppConstant.Genre.ANIME,
        AppConstant.Genre.WESTERN,
    )

    // bind viewModel
    private val viewModel: HomeViewModel by viewModels()
    // binding instance
    private lateinit var binding: FragmentHomeBinding

    private var tracks = mutableListOf<Track>()

    // bind view on Fragment
    override fun onBindLayoutResource(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupContainer()
        subscribeObserver()
        init()
    }

    /**
     * Render UI
     */
    private fun setupContainer() {
        binding.ervContainer.withModels {
            // loop every sections
            sections.mapIndexed { index, name ->
                // filter the tracks by category
                val sectionTracks = tracks.filter { trackModel ->
                    trackModel.primaryGenreName.equals(name, true)
                }.toMutableList()

                // show only when section (category) is not empty
                if (sectionTracks.size > 0) {
                    // render ui
                    sectionContainer {
                        id(name)
                        sectionTitle {
                            id(index)
                            title(name)
                        }
                        carousel {
                            id(name)
                            padding(Carousel.Padding.dp(0, 3, 0, 3, 2))
                            models(sectionTracks.map {
                                TrackItemModel_()
                                    .id(it.trackId)
                                    .context(requireContext())
                                    .track(it)
                                    .onItemClickListener(object : OnItemClickListener<Track> {
                                        override fun onItemClick(obj: Track) {
                                            handleClickListener(obj)
                                        }
                                    })
                            })
                        }
                    }
                }
            }
        }
    }

    // Observe request data status
    private fun subscribeObserver() {
        viewModel.response.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgressAndError()
                }
                Status.ERROR -> {
                    showError()
                }
                Status.LOADING -> {
                    showProgress()
                }
            }
        })

        // Listen for database tracks changes
        viewModel.tracks.observe(viewLifecycleOwner, Observer {response ->
            response?.let {
                if (tracks.size < 1) {
                    tracks = it.toMutableList()
                    binding.ervContainer.requestModelBuild()
                }
            }

        })
    }

    /**
     * Request tracks
     */
    private fun init() {
        viewModel.requestData()

        binding.btnRefresh.setOnClickListener {
            viewModel.requestData()
        }
    }


    private fun handleClickListener(track: Track) {
        val action = HomeFragmentDirections.actionHomeFragmentToTrackDetailFragment(track)
        findNavController().navigate(action)
    }

    private fun hideProgressAndError() {
        binding.llError.visibility = View.GONE
        binding.progress.visibility = View.GONE
        binding.ervContainer.visibility = View.VISIBLE
    }

    private fun showProgress() {
        // don't show loading when tracks is not empty
        if (tracks.size > 0) {
            binding.llError.visibility = View.GONE
            binding.progress.visibility = View.VISIBLE
            binding.ervContainer.visibility = View.GONE
        }
    }

    private fun showError() {
        if (tracks.size <= 0) {
            binding.llError.visibility = View.VISIBLE
            binding.progress.visibility = View.GONE
            binding.ervContainer.visibility = View.GONE
        } else {
            hideProgressAndError()
        }
    }

}