package com.example.animefacts.di

import com.example.animefacts.data.remote.repository.AnimeRepositoryImpl
import com.example.animefacts.domain.repository.AnimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAnimeRepository(
        impl: AnimeRepositoryImpl
    ): AnimeRepository = impl
}