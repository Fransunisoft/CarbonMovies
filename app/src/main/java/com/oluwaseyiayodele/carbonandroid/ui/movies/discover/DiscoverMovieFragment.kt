package com.oluwaseyiayodele.carbonandroid.ui.movies.discover

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.oluwaseyiayodele.carbonandroid.databinding.DiscoverMoviesFragmentBinding
import com.oluwaseyiayodele.carbonandroid.model.MoviePresentation
import com.oluwaseyiayodele.carbonandroid.ui.movies.MoviesFragmentDirections
import com.oluwaseyiayodele.carbonandroid.ui.movies.adapter.MoviesAdapter
import com.oluwaseyiayodele.carbonandroid.ui.utils.initRecyclerViewUsingGridLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DiscoverMovieFragment : Fragment() {

    private var _binding: DiscoverMoviesFragmentBinding? = null
    private val binding get() = _binding!!
    private val movieDiscoverViewModel: MovieDiscoverViewModel by viewModels()
    private lateinit var moviesAdapter: MoviesAdapter
    private var SPAN_COUNT = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val currentOrientation = resources.configuration.orientation
        SPAN_COUNT = if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            4
        } else {
            2
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DiscoverMoviesFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = movieDiscoverViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListAdapter()
    }

    private fun setupListAdapter() {
        moviesAdapter =
            MoviesAdapter { movie -> openMovieDetails(movie) }
        context?.let {
            binding.discoverMovieList.initRecyclerViewUsingGridLayout(it, SPAN_COUNT)
        }
        binding.discoverMovieList.adapter = moviesAdapter
    }

    private fun openMovieDetails(movie: MoviePresentation) {
        val action = MoviesFragmentDirections
            .actionMoviesFragmentToMovieDetailsFragment(movie)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
