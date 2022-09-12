package com.oluwaseyiayodele.data.contract.remote

import com.oluwaseyiayodele.data.model.MovieEntity
import com.oluwaseyiayodele.domain.functional.Result


interface MovieDiscoverRemote {
    suspend fun discoverMovies(): Result<List<MovieEntity>>
}