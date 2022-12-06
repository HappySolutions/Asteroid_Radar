package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.viewmodel.dsl.viewModel
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MainFragment : Fragment() {

//    private val viewModel: MainViewModel by lazy {
//        ViewModelProvider(this).get(MainViewModel::class.java)
//    }
    private val viewModel: MainViewModel by sharedViewModel()
    private val adapter = AsteroidAdapter()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding =FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViewAdapter()
        setupObservers()
    }

    private fun setupRecyclerViewAdapter() {
        binding.asteroidRecycler.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.asteroidList.observe(viewLifecycleOwner){
            it?.let {
                if (adapter.asterList != null) {
                adapter.asterList = it
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
