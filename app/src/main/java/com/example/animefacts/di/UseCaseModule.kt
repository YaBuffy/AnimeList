package com.example.animefacts.di

import com.example.animefacts.domain.repository.AnimeRepository
import com.example.animefacts.domain.usecase.GetAnimeByCategoryUseCase
import com.example.animefacts.domain.usecase.GetAnimeInfoUseCase
import com.example.animefacts.domain.usecase.GetAnimeRecommendationsUseCase
import com.example.animefacts.domain.usecase.GetAnimeStatisticsUseCase
import com.example.animefacts.domain.usecase.SearchAnimePagingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetAnimeByCategoryUseCase(
        repository: AnimeRepository
    ): GetAnimeByCategoryUseCase {
        return GetAnimeByCategoryUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetAnimeInfoUseCase(
        repository: AnimeRepository
    ): GetAnimeInfoUseCase {
        return GetAnimeInfoUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetAnimeRecommendationsUseCase(
        repository: AnimeRepository
    ): GetAnimeRecommendationsUseCase {
        return GetAnimeRecommendationsUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSearchAnimeUseCase(
        repository: AnimeRepository
    ): SearchAnimePagingUseCase {
        return SearchAnimePagingUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetAnimeStatisticsUseCase(
        repository: AnimeRepository
    ): GetAnimeStatisticsUseCase {
        return GetAnimeStatisticsUseCase(repository)
    }

}