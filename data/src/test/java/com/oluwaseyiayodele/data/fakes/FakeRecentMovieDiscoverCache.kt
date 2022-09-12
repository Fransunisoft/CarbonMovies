package com.oluwaseyiayodele.data.fakes

import com.oluwaseyiayodele.data.contract.cache.RecentMovieDiscoverCache
import com.oluwaseyiayodele.data.model.MovieEntity
import com.oluwaseyiayodele.domain.functional.Result

class FakeRecentMovieDiscoverCache : RecentMovieDiscoverCache {

    private val cache = LinkedHashMap<Long, MovieEntity>()

    override suspend fun saveMovieDiscoveries(movieEntities: List<MovieEntity>): Result<Unit> {
        clearRecentDiscoveries()
        movieEntities.forEach {
            cache[it.id] = it
        }
        return Result.Success(Unit)
    }

    override suspend fun getRecentDiscoveries(): Result<List<MovieEntity>> {
        return Result.Success(cache.values.toList())
    }

    override suspend fun clearRecentDiscoveries(): Result<Unit> {
        cache.clear()
        return Result.Success(Unit)
    }
}
