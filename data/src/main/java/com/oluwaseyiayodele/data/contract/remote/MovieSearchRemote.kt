package com.oluwaseyiayodele.data.contract.remote

import com.oluwaseyiayodele.data.model.MovieEntity
import com.oluwaseyiayodele.domain.functional.Result

interface MovieSearchRemote {
    suspend fun searchMovies(movieName: String): Result<List<MovieEntity>>
}