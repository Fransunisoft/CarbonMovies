package com.oluwaseyiayodele.domain.usecase.discover

import com.oluwaseyiayodele.domain.model.Movie
import com.oluwaseyiayodele.domain.functional.Result
import com.oluwaseyiayodele.domain.repository.MovieDiscoverRepository
import com.oluwaseyiayodele.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecentDiscoveries @Inject constructor(
    private val movieDiscoverRepository: MovieDiscoverRepository
) : FlowUseCase<Unit, List<Movie>> {
    override fun invoke(params: Unit?): Flow<Result<List<Movie>>> {
        return movieDiscoverRepository.getRecentMovieDiscover()
    }
}
