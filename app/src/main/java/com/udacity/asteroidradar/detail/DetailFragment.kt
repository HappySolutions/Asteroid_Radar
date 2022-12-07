package com.udacity.asteroidradar.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentDetailBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class DetailFragment : Fragment() {
    private val viewModel: DetailViewModel by sharedViewModel()
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val asteroid = DetailFragmentArgs.fromBundle(requireArguments()).selectedAsteroid

        binding.asteroid = asteroid
        listObserver()

        return binding.root
    }

    private fun listObserver() {
        viewModel.displayExplanationDialog.observe(viewLifecycleOwner) { displayExplanationDialog ->
            if (displayExplanationDialog) {
                displayAstronomicalUnitExplanationDialog()
                viewModel.closeExplanationDialog()
            }
        }
    }

    private fun displayAstronomicalUnitExplanationDialog() {
        val builder = AlertDialog.Builder(requireActivity())
            .setMessage(getString(R.string.astronomica_unit_explanation))
            .setPositiveButton(android.R.string.ok, null)
        builder.create().show()
    }
}
