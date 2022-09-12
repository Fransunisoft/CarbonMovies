package com.oluwaseyiayodele.core.di

import com.oluwaseyiayodele.data.repository.MovieSearchRepositoryImpl
import com.oluwaseyiayodele.data.repository.MovieDiscoverRepositoryImpl
import com.oluwaseyiayodele.domain.repository.MovieDiscoverRepository
import com.oluwaseyiayodele.domain.repository.MovieSearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @get:Binds
    val MovieDiscoverRepositoryImpl.movieDiscoverRepository: MovieDiscoverRepository

    @get:Binds
    val MovieSearchRepositoryImpl.movieSearchRepository: MovieSearchRepository
}
