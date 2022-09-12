package com.oluwaseyiayodele.domain.usecase.search

import com.oluwaseyiayodele.domain.model.Movie
import com.oluwaseyiayodele.domain.functional.Result
import com.oluwaseyiayodele.domain.repository.MovieSearchRepository
import com.oluwaseyiayodele.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecentSearch @Inject constructor(
    private val movieSearchRepository: MovieSearchRepository
) : FlowUseCase<Unit, List<Movie>> {
    override fun invoke(params: Unit?): Flow<Result<List<Movie>>> {
        return movieSearchRepository.getRecentMovieSearch()
    }
}
