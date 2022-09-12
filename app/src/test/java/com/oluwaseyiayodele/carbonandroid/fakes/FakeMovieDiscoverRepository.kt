package com.oluwaseyiayodele.carbonandroid.fakes

import com.oluwaseyiayodele.carbonandroid.data.DummyData
import com.oluwaseyiayodele.carbonandroid.util.ResponseType
import com.oluwaseyiayodele.domain.exception.Failure
import com.oluwaseyiayodele.domain.repository.MovieDiscoverRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import com.oluwaseyiayodele.domain.functional.Result
import com.oluwaseyiayodele.domain.model.Movie

class FakeMovieDiscoverRepository : MovieDiscoverRepository {

    private var moviesFlow: Flow<Result<List<Movie>>> = flowOf(Result.Success(DummyData.movies))

    var movieListResponseType: ResponseType = ResponseType.DATA
        set(value) {
            field = value
            moviesFlow = makeResponse(value)
        }

    private fun makeResponse(type: ResponseType): Flow<Result<List<Movie>>> {
        return when (type) {
            ResponseType.DATA -> moviesFlow
            ResponseType.EMPTY_DATA -> flowOf(Result.Success(listOf()))
            ResponseType.ERROR -> {
                flowOf(Result.Error(Failure.ServerError))
            }
        }
    }

    override fun getRecentMovieDiscover(): Flow<Result<List<Movie>>> {
        return moviesFlow
    }

    override fun discoverMovies(): Flow<Result<List<Movie>>> {
        return moviesFlow
    }
}
