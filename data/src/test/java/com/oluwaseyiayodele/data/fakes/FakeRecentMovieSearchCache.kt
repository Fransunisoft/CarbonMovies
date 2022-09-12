package com.oluwaseyiayodele.data.fakes

import com.oluwaseyiayodele.data.contract.cache.RecentMovieSearchCache
import com.oluwaseyiayodele.data.model.MovieEntity
import com.oluwaseyiayodele.domain.functional.Result

class FakeRecentMovieSearchCache : RecentMovieSearchCache {

    private val cache = LinkedHashMap<Long, MovieEntity>()
    override suspend fun saveMovieSearch(movieEntities: List<MovieEntity>): Result<Unit> {
        clearRecentSearch()
        movieEntities.forEach {
            cache[it.id] = it
        }
        return Result.Success(Unit)
    }

    override suspend fun getRecentSearch(): Result<List<MovieEntity>> {
        return Result.Success(cache.values.toList())
    }

    override suspend fun clearRecentSearch(): Result<Unit> {
        cache.clear()
        return Result.Success(Unit)
    }
}
