package com.johnpaulcas.watchly.ui

import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.johnpaulcas.watchly.R
import com.johnpaulcas.watchly.base.BaseActivity
import com.johnpaulcas.watchly.databinding.ActivityMainBinding
import com.johnpaulcas.watchly.ui.fragments.HomeFragmentDirections
import com.johnpaulcas.watchly.utils.AppConstant
import com.johnpaulcas.watchly.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private var loadOnce = true

    override fun onBindLayoutResource(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun init() {
        // nav controller setup
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (!loadOnce) {
                viewModel.saveLastKnownScreen(destination.label.toString())
            }
            loadOnce = false
        }

        setupActionBarWithNavController(navController)

        subscribeObserver()
        requestData()
    }

    private fun subscribeObserver() {
        viewModel.lastVisitedDate.observe(this, Observer { lastDateVisited ->
            if (!lastDateVisited.isNullOrBlank()) {
                binding.tvLastDateVisited.visibility = View.VISIBLE
                binding.tvLastDateVisited.text = "Last visit: $lastDateVisited"
            } else {
                binding.tvLastDateVisited.visibility = View.GONE
            }
        })

        viewModel.lastKnownScreen.observe(this, Observer { lastKnownScreen ->
            lastKnownScreen?.let {
                if (it.equals(AppConstant.Screen.TRACK_DETAILS, true)) {
                    val action = HomeFragmentDirections.actionHomeFragmentToTrackDetailFragment(null)
                    navController.navigate(action)
                }
            }
        })
    }

    private fun requestData() {
        viewModel.getLastVisitedDate()
        viewModel.loadLastKnownScreen()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}