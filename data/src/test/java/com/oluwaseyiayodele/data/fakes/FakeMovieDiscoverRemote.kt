package com.oluwaseyiayodele.data.fakes

import com.oluwaseyiayodele.data.model.DummyData
import com.oluwaseyiayodele.data.util.ResponseType
import com.oluwaseyiayodele.data.contract.remote.MovieDiscoverRemote
import com.oluwaseyiayodele.data.model.MovieEntity
import com.oluwaseyiayodele.domain.exception.Failure
import com.oluwaseyiayodele.domain.functional.Result

class FakeMovieDiscoverRemote : MovieDiscoverRemote {

    private var moviesResult: Result<List<MovieEntity>> =
        Result.Success(listOf(DummyData.movieEntity))
    var movieResponseType: ResponseType = ResponseType.DATA
        set(value) {
            field = value
            moviesResult = makeResponse(value)
        }

    private fun makeResponse(type: ResponseType): Result<List<MovieEntity>> {
        return when (type) {
            ResponseType.DATA -> moviesResult
            ResponseType.EMPTY_DATA -> Result.Success(emptyList())
            ResponseType.ERROR -> Result.Error(Failure.ServerError)
        }
    }

    override suspend fun discoverMovies(): Result<List<MovieEntity>> {
        return moviesResult
    }
}
