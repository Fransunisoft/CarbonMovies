package com.oluwaseyiayodele.carbonandroid.model.states

import com.oluwaseyiayodele.carbonandroid.model.MoviePresentation

data class MovieListView(
    val loading: Boolean = false,
    val errorMessage: Int? = null,
    val isEmpty: Boolean = false,
    val movies: List<MoviePresentation>? = null
)
