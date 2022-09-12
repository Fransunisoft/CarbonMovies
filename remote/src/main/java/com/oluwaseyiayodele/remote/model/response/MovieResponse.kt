package com.oluwaseyiayodele.remote.model.response

import com.oluwaseyiayodele.remote.model.MovieRemoteModel

data class MovieResponse(
    val page: Int,
    val results: List<MovieRemoteModel>
)