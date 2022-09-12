package com.oluwaseyiayodele.carbonandroid.ui.movies.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oluwaseyiayodele.carbonandroid.R
import com.oluwaseyiayodele.carbonandroid.mapper.MoviePresentationMapper
import com.oluwaseyiayodele.carbonandroid.model.MoviePresentation
import com.oluwaseyiayodele.carbonandroid.model.states.MovieListView
import com.oluwaseyiayodele.domain.exception.Failure
import com.oluwaseyiayodele.domain.usecase.search.GetRecentSearch
import com.oluwaseyiayodele.domain.usecase.search.SearchMovie
import com.oluwaseyiayodele.domain.functional.Result
import com.oluwaseyiayodele.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val searchMovie: SearchMovie,
    private val getRecentSearch: GetRecentSearch,
    private val moviePresentationMapper: MoviePresentationMapper
) : ViewModel() {

    private val _movieListView = MutableLiveData<MovieListView>()
    val movieListView: LiveData<MovieListView>
        get() = _movieListView

    init {
        fetchRecentMovies()
    }

    fun fetchRecentMovies() {
        viewModelScope.launch {
            _movieListView.postValue(MovieListView(loading = true))
            getRecentSearch().collect {
                when (it) {
                    is Result.Success -> {
                        handleMoviesSuccess(it.data)
                    }
                    is Result.Error -> {
                        handleMoviesError(it.failure)
                    }
                }
            }
        }
    }

    fun searchMovies(query: String) {
        viewModelScope.launch {
            _movieListView.postValue(MovieListView(loading = true))
            if (query.isBlank()) fetchRecentMovies()
            else {
                searchMovie(query).collect {
                    when (it) {
                        is Result.Success -> {
                            handleMoviesSuccess(it.data)
                        }
                        is Result.Error -> {
                            handleMoviesError(it.failure)
                        }
                    }
                }
            }
        }
    }

    private fun handleMoviesError(failure: Failure) {
        when (failure) {
            is Failure.ServerError -> {
                _movieListView.postValue(MovieListView(errorMessage = R.string.server_error))
            }
            else -> {
                _movieListView.postValue(MovieListView(errorMessage = R.string.movies_error))
            }
        }
    }

    private fun handleMoviesSuccess(movies: List<Movie>) {
        if (movies.isEmpty()) {
            _movieListView.postValue(MovieListView(isEmpty = true))
        } else {
            val moviePresentationList: List<MoviePresentation> =
                movies.map(moviePresentationMapper::mapToPresentation)
            _movieListView.postValue(MovieListView(movies = moviePresentationList))
        }
    }
}
