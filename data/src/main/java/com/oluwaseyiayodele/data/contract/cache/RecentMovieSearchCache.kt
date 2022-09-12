package com.oluwaseyiayodele.data.contract.cache

import com.oluwaseyiayodele.data.model.MovieEntity
import com.oluwaseyiayodele.domain.functional.Result

interface RecentMovieSearchCache {
    suspend fun saveMovieSearch(movieEntities: List<MovieEntity>): Result<Unit>
    suspend fun getRecentSearch(): Result<List<MovieEntity>>
    suspend fun clearRecentSearch(): Result<Unit>
}