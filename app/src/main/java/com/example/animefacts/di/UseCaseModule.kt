package com.example.animefacts.di

import com.example.animefacts.domain.repository.AnimeRepository
import com.example.animefacts.domain.usecase.GetAnimeByCategoryUseCase
import com.example.animefacts.domain.usecase.GetAnimeInfoUseCase
import com.example.animefacts.domain.usecase.GetAnimeRecommendationsUseCase
import com.example.animefacts.domain.usecase.GetAnimeStatisticsUseCase
import com.example.animefacts.domain.usecase.GetCachedAnimeUseCase
import com.example.animefacts.domain.usecase.GetRandomAnimeInfoUseCase
import com.example.animefacts.domain.usecase.GetRecommendationsUseCase
import com.example.animefacts.domain.usecase.GetSchedulesUseCase
import com.example.animefacts.domain.usecase.GetTopReviewUseCase
import com.example.animefacts.domain.usecase.SearchAnimePagingUseCase
import com.example.animefacts.domain.usecase.SetCachedAnimeUseCase
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

    @Singleton
    @Provides
    fun provideGetRandomAnimeInfoUseCase(
        repository: AnimeRepository
    ): GetRandomAnimeInfoUseCase {
        return GetRandomAnimeInfoUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetCachedAnimeUseCase(
        repository: AnimeRepository
    ): GetCachedAnimeUseCase {
        return GetCachedAnimeUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSetCachedAnimeUseCase(
        repository: AnimeRepository
    ): SetCachedAnimeUseCase {
        return SetCachedAnimeUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetRecommendationsUseCase(
        repository: AnimeRepository
    ): GetRecommendationsUseCase {
        return GetRecommendationsUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetSchedulesUseCase(
        repository: AnimeRepository
    ): GetSchedulesUseCase {
        return GetSchedulesUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetTopReviewUseCase(
        repository: AnimeRepository
    ): GetTopReviewUseCase {
        return GetTopReviewUseCase(repository)
    }
}