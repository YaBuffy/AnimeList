package com.example.animefacts.di

import com.example.animefacts.domain.repository.AnimeRepository
import com.example.animefacts.domain.repository.BookmarkRepository
import com.example.animefacts.domain.usecase.bookmark.DeleteBookmarkByIdUseCase
import com.example.animefacts.domain.usecase.bookmark.GetBookmarkByIdUseCase
import com.example.animefacts.domain.usecase.bookmark.GetBookmarksByStatusUseCase
import com.example.animefacts.domain.usecase.bookmark.GetFavoritesUseCase
import com.example.animefacts.domain.usecase.bookmark.SaveBookmarkUseCase
import com.example.animefacts.domain.usecase.home.GetAnimeByCategoryUseCase
import com.example.animefacts.domain.usecase.detail.GetAnimeInfoUseCase
import com.example.animefacts.domain.usecase.detail.GetAnimeRecommendationsUseCase
import com.example.animefacts.domain.usecase.detail.GetAnimeStatisticsUseCase
import com.example.animefacts.domain.usecase.discover.GetCachedAnimeUseCase
import com.example.animefacts.domain.usecase.discover.GetRandomAnimeInfoUseCase
import com.example.animefacts.domain.usecase.discover.GetRecommendationsUseCase
import com.example.animefacts.domain.usecase.discover.GetSchedulesUseCase
import com.example.animefacts.domain.usecase.discover.GetTopReviewUseCase
import com.example.animefacts.domain.usecase.search.SearchAnimePagingUseCase
import com.example.animefacts.domain.usecase.discover.SetCachedAnimeUseCase
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

    @Singleton
    @Provides
    fun provideSaveBookmarkUseCase(
        repository: BookmarkRepository
    ): SaveBookmarkUseCase {
        return SaveBookmarkUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetFavoritesUseCase(
        repository: BookmarkRepository
    ): GetFavoritesUseCase {
        return GetFavoritesUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetBookmarksByStatusUseCase(
        repository: BookmarkRepository
    ): GetBookmarksByStatusUseCase {
        return GetBookmarksByStatusUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetBookmarkByIdUseCase(
        repository: BookmarkRepository
    ): GetBookmarkByIdUseCase {
        return GetBookmarkByIdUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideDeleteBookmarkByIdUseCase(
        repository: BookmarkRepository
    ): DeleteBookmarkByIdUseCase {
        return DeleteBookmarkByIdUseCase(repository)
    }
}