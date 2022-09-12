package com.oluwaseyiayodele.domain.repository

import com.oluwaseyiayodele.domain.model.Movie
import com.oluwaseyiayodele.domain.functional.Result
import kotlinx.coroutines.flow.Flow

interface MovieDiscoverRepository {
    fun getRecentMovieDiscover(): Flow<Result<List<Movie>>>
    fun discoverMovies(): Flow<Result<List<Movie>>>
}
