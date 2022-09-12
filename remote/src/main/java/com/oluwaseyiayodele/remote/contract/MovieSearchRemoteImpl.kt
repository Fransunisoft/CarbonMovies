package com.oluwaseyiayodele.remote.contract

import com.oluwaseyiayodele.remote.ApiService
import com.oluwaseyiayodele.data.contract.remote.MovieSearchRemote
import com.oluwaseyiayodele.data.model.MovieEntity
import com.oluwaseyiayodele.domain.exception.Failure
import com.oluwaseyiayodele.domain.functional.Result
import com.oluwaseyiayodele.remote.mapper.MovieRemoteModelMapper

import javax.inject.Inject

class MovieSearchRemoteImpl @Inject constructor(
    private val apiService: ApiService,
    private val movieRemoteModelMapper: MovieRemoteModelMapper
) : MovieSearchRemote {
    override suspend fun searchMovies(movieName: String): Result<List<MovieEntity>> {
        return try {
            val res = apiService.searchMovies(movieName)
            when (res.isSuccessful) {
                true -> {
                    res.body()?.let {
                        Result.Success(movieRemoteModelMapper.mapFromModelList(it.results))
                    } ?: Result.Success(emptyList())
                }
                false -> {
                    Result.Error(Failure.ServerError)
                }
            }
        } catch (e: Throwable) {
            Result.Error(Failure.ServerError)
        }
    }
}
