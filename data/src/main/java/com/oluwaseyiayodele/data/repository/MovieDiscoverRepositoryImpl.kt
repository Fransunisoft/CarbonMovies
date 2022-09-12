package com.oluwaseyiayodele.data.repository

import com.oluwaseyiayodele.data.contract.cache.RecentMovieDiscoverCache
import com.oluwaseyiayodele.data.contract.remote.MovieDiscoverRemote
import com.oluwaseyiayodele.data.mapper.MovieEntityMapper
import com.oluwaseyiayodele.domain.model.Movie
import com.oluwaseyiayodele.domain.repository.MovieDiscoverRepository
import com.oluwaseyiayodele.domain.functional.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDiscoverRepositoryImpl @Inject constructor(
    private val movieDiscoverRemote: MovieDiscoverRemote,
    private val recentMovieDiscoverCache: RecentMovieDiscoverCache,
    private val movieEntityMapper: MovieEntityMapper
) : MovieDiscoverRepository {

    override fun getRecentMovieDiscover(): Flow<Result<List<Movie>>> {
        return flow {
            when (val discoveredMovies = recentMovieDiscoverCache.getRecentDiscoveries()) {
                is Result.Success -> {
                    emit(Result.Success(movieEntityMapper.mapToDomainList(discoveredMovies.data)))
                }
                is Result.Error -> {
                    emit(Result.Error(discoveredMovies.failure))
                }
            }
        }
    }

    override fun discoverMovies(): Flow<Result<List<Movie>>> {
        return flow {
            when (val discoveredMovies = movieDiscoverRemote.discoverMovies()) {
                is Result.Success -> {
                    recentMovieDiscoverCache.saveMovieDiscoveries(discoveredMovies.data)
                    emit(Result.Success(movieEntityMapper.mapToDomainList(discoveredMovies.data)))
                }
                is Result.Error -> {
                    emit(Result.Error(discoveredMovies.failure))
                }
            }
        }
    }
}
