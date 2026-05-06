package com.example.animefacts.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animefacts.domain.model.Bookmark
import com.example.animefacts.domain.model.ViewingStatus
import com.example.animefacts.domain.usecase.bookmark.GetBookmarksByStatusUseCase
import com.example.animefacts.domain.usecase.bookmark.GetFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getBookmarksByStatusUseCase: GetBookmarksByStatusUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase
): ViewModel() {
    // В BookmarkViewModel.kt
    private val pageCache = mutableMapOf<Int, Flow<List<Bookmark>>>()

    fun getBookmarksForPage(page: Int): Flow<List<Bookmark>> {
        return pageCache.getOrPut(page) {
            if (page == 0) {
                getFavoritesUseCase()
            } else {
                val status = ViewingStatus.entries[page]
                getBookmarksByStatusUseCase(status)
            }
                .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
        }
    }

}