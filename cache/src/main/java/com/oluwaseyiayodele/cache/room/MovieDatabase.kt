package com.oluwaseyiayodele.cache.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oluwaseyiayodele.cache.entity.MovieCacheModel

@Database(
    entities = [MovieCacheModel::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "movie_database"
    }

    abstract val moviesDao: MoviesDao
}
