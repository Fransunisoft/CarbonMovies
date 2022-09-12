package com.oluwaseyiayodele.domain.usecase.search

import com.oluwaseyiayodele.domain.model.Movie
import com.oluwaseyiayodele.domain.functional.Result
import com.oluwaseyiayodele.domain.repository.MovieSearchRepository
import com.oluwaseyiayodele.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMovie @Inject constructor(
    private val movieSearchRepository: MovieSearchRepository
) : FlowUseCase<String, List<Movie>> {
    override fun invoke(params: String?): Flow<Result<List<Movie>>> {
        return movieSearchRepository.searchMovies(params!!)
    }
}
