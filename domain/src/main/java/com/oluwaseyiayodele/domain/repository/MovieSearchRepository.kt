package com.oluwaseyiayodele.domain.repository

import com.oluwaseyiayodele.domain.model.Movie
import com.oluwaseyiayodele.domain.functional.Result
import kotlinx.coroutines.flow.Flow

interface MovieSearchRepository {
    fun getRecentMovieSearch(): Flow<Result<List<Movie>>>
    fun searchMovies(param: String): Flow<Result<List<Movie>>>
}
