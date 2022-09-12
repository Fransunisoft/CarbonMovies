package com.oluwaseyiayodele.cache.di

import android.content.Context
import androidx.room.Room
import com.oluwaseyiayodele.cache.cacheImpl.RecentMovieDiscoverCacheImpl
import com.oluwaseyiayodele.cache.cacheImpl.RecentMovieSearchCacheImpl
import com.oluwaseyiayodele.cache.room.MovieDatabase
import com.oluwaseyiayodele.cache.room.MoviesDao
import com.oluwaseyiayodele.data.contract.cache.RecentMovieDiscoverCache
import com.oluwaseyiayodele.data.contract.cache.RecentMovieSearchCache
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CacheModule {

    @get:Binds
    val RecentMovieSearchCacheImpl.recentMovieSearchCache: RecentMovieSearchCache

    @get:Binds
    val RecentMovieDiscoverCacheImpl.recentMovieDiscoverCache: RecentMovieDiscoverCache

    companion object {
        @[Provides Singleton]
        fun provideDataBase(@ApplicationContext context: Context): MovieDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                MovieDatabase.DB_NAME
            ).fallbackToDestructiveMigration().build()
        }

        @[Provides Singleton]
        fun provideMoviesDao(movieDatabase: MovieDatabase): MoviesDao = movieDatabase.moviesDao
    }
}
