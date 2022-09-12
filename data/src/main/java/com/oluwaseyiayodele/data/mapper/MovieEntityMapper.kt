package com.oluwaseyiayodele.data.mapper

import com.oluwaseyiayodele.data.mapper.base.EntityMapper
import com.oluwaseyiayodele.data.model.MovieEntity
import com.oluwaseyiayodele.domain.model.Movie
import javax.inject.Inject

class MovieEntityMapper @Inject constructor() : EntityMapper<MovieEntity, Movie> {
    override fun mapToDomain(entity: MovieEntity): Movie {
        return Movie(
            id = entity.id,
            adult = entity.adult,
            posterPath = entity.posterPath,
            backdropPath = entity.backdropPath,
            releaseDate = entity.releaseDate,
            title = entity.title,
            popularity = entity.popularity,
            originalTitle = entity.originalTitle,
            overview = entity.overview,
            voteAverage = entity.voteAverage,
            voteCount = entity.voteCount
        )
    }

    override fun mapToEntity(domain: Movie): MovieEntity {
        return MovieEntity(
            id = domain.id,
            adult = domain.adult,
            posterPath = domain.posterPath,
            backdropPath = domain.backdropPath,
            releaseDate = domain.releaseDate,
            title = domain.title,
            popularity = domain.popularity,
            originalTitle = domain.originalTitle,
            overview = domain.overview,
            voteAverage = domain.voteAverage,
            voteCount = domain.voteCount
        )
    }
}
