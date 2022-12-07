package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MainFragment : Fragment() {

//    private val viewModel: MainViewModel by lazy {
//        ViewModelProvider(this).get(MainViewModel::class.java)
//    }
    private val viewModel: MainViewModel by sharedViewModel()
    private lateinit var binding: FragmentMainBinding
    private var progressBar: ProgressBar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var adapter = AsteroidAdapter(AsteroidListener{
                asteroid -> viewModel.onAsteroidClicked(asteroid)
        })

        binding =FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        progressBar = binding.statusLoadingWheel
        progressBar?.visibility = View.VISIBLE
        setupRecyclerViewAdapter(adapter)
        setupObservers(adapter)

        setHasOptionsMenu(true)

        return binding.root
    }



    private fun setupRecyclerViewAdapter(adapter: AsteroidAdapter) {
        binding.asteroidRecycler.adapter = adapter
    }

    private fun setupObservers(adapter: AsteroidAdapter) {
        viewModel.asteroidList.observe(viewLifecycleOwner) {
            it?.let {
                if (adapter.asterList != null) {
                    adapter.asterList = it
                    progressBar?.visibility = View.GONE
                }
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.view_week_asteroids -> viewModel.onViewWeekAsteroidsClicked()
            R.id.view_today_asteroids -> viewModel.onViewTodayAsteroidsClicked()
            R.id.view_saved_asteroids -> viewModel.onViewSavedAsteroidsClicked()
        }
        return true
    }
}
