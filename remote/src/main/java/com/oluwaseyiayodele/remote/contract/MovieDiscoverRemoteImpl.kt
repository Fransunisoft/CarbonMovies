package com.oluwaseyiayodele.remote.contract


import com.oluwaseyiayodele.remote.ApiService
import com.oluwaseyiayodele.data.contract.remote.MovieDiscoverRemote
import com.oluwaseyiayodele.data.model.MovieEntity
import com.oluwaseyiayodele.domain.exception.Failure
import com.oluwaseyiayodele.domain.functional.Result
import com.oluwaseyiayodele.remote.mapper.MovieRemoteModelMapper
import javax.inject.Inject

class MovieDiscoverRemoteImpl @Inject constructor(
    private val apiService: ApiService,
    private val movieRemoteModelMapper: MovieRemoteModelMapper
) : MovieDiscoverRemote {
    override suspend fun discoverMovies(): Result<List<MovieEntity>> {
        return try {
            val res = apiService.discoverMovies()
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
