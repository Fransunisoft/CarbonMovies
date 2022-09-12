package com.oluwaseyiayodele.core.di

import com.oluwaseyiayodele.core.BuildConfig
import com.oluwaseyiayodele.data.contract.remote.MovieDiscoverRemote
import com.oluwaseyiayodele.data.contract.remote.MovieSearchRemote
import com.oluwaseyiayodele.remote.ApiService
import com.oluwaseyiayodele.remote.ApiServiceFactory
import com.oluwaseyiayodele.remote.contract.MovieDiscoverRemoteImpl
import com.oluwaseyiayodele.remote.contract.MovieSearchRemoteImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RemoteModule {

    @get:Binds
    val MovieDiscoverRemoteImpl.movieDiscoverRemote: MovieDiscoverRemote

    @get:Binds
    val MovieSearchRemoteImpl.movieSearchRemote: MovieSearchRemote

    companion object {
        @[Provides Singleton]
        fun provideApiService(): ApiService =
            ApiServiceFactory.createApiService(BuildConfig.DEBUG, BuildConfig.API_KEY)
    }
}
