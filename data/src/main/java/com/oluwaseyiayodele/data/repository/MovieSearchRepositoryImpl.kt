package com.oluwaseyiayodele.data.repository

import com.oluwaseyiayodele.data.contract.cache.RecentMovieSearchCache
import com.oluwaseyiayodele.data.contract.remote.MovieSearchRemote
import com.oluwaseyiayodele.data.mapper.MovieEntityMapper
import com.oluwaseyiayodele.domain.functional.Result
import com.oluwaseyiayodele.domain.model.Movie
import com.oluwaseyiayodele.domain.repository.MovieSearchRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieSearchRepositoryImpl @Inject constructor(
    private val movieSearchRemote: MovieSearchRemote,
    private val recentMovieSearchCache: RecentMovieSearchCache,
    private val movieEntityMapper: MovieEntityMapper
) : MovieSearchRepository {

    override fun getRecentMovieSearch(): Flow<Result<List<Movie>>> {
        return flow {
            when (val searchMovies = recentMovieSearchCache.getRecentSearch()) {
                is Result.Success -> {
                    emit(Result.Success(movieEntityMapper.mapToDomainList(searchMovies.data)))
                }
                is Result.Error -> {
                    emit(Result.Error(searchMovies.failure))
                }
            }
        }
    }

    override fun searchMovies(param: String): Flow<Result<List<Movie>>> {
        return flow {
            when (val searchMovies = movieSearchRemote.searchMovies(param)) {
                is Result.Success -> {
                    recentMovieSearchCache.saveMovieSearch(searchMovies.data)
                    emit(Result.Success(movieEntityMapper.mapToDomainList(searchMovies.data)))
                }
                is Result.Error -> {
                    emit(Result.Error(searchMovies.failure))
                }
            }
        }
    }
}
