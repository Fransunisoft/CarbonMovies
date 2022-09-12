package com.oluwaseyiayodele.data.contract.cache

import com.oluwaseyiayodele.data.model.MovieEntity
import com.oluwaseyiayodele.domain.functional.Result

interface RecentMovieDiscoverCache {
    suspend fun saveMovieDiscoveries(movieEntities: List<MovieEntity>): Result<Unit>
    suspend fun getRecentDiscoveries(): Result<List<MovieEntity>>
    suspend fun clearRecentDiscoveries(): Result<Unit>
}