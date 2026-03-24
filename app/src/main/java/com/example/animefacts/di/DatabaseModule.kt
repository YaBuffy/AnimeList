package com.example.animefacts.di

import android.content.Context
import androidx.room.Room
import com.example.animefacts.data.local.AnimeDatabase
import com.example.animefacts.data.local.Dao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AnimeDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = AnimeDatabase::class.java,
            name = "anime_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAnimeDao(db: AnimeDatabase): Dao{
        return db.animeDao()
    }


}