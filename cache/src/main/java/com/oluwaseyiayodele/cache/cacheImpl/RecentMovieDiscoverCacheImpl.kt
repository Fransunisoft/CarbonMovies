package com.oluwaseyiayodele.cache.cacheImpl

import com.oluwaseyiayodele.cache.mapper.MovieCacheModelMapper
import com.oluwaseyiayodele.cache.room.MoviesDao
import com.oluwaseyiayodele.data.contract.cache.RecentMovieDiscoverCache
import com.oluwaseyiayodele.data.model.MovieEntity
import com.oluwaseyiayodele.domain.functional.Result
import javax.inject.Inject

class RecentMovieDiscoverCacheImpl @Inject constructor(
    private val movieDao: MoviesDao,
    private val movieCacheModelMapper: MovieCacheModelMapper
) : RecentMovieDiscoverCache {
    override suspend fun saveMovieDiscoveries(movieEntities: List<MovieEntity>): Result<Unit> {
        clearRecentDiscoveries()
        val movieCacheModelList = movieCacheModelMapper.mapToModelList(movieEntities)
        movieCacheModelList.forEach {
            it.isDiscovered = true
        }
        movieDao.insertMovies(movieCacheModelList)
        return Result.Success(Unit)
    }

    override suspend fun getRecentDiscoveries(): Result<List<MovieEntity>> {
        val movieCacheModelList = movieDao.fetchRecentDiscover()
        return Result.Success(movieCacheModelMapper.mapToEntityList(movieCacheModelList))
    }

    override suspend fun clearRecentDiscoveries(): Result<Unit> {
        movieDao.clearRecentDiscover()
        return Result.Success(Unit)
    }
}
