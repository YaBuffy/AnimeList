package com.example.animefacts.presentation.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animefacts.data.common.ApiResult
import com.example.animefacts.domain.usecase.GetRandomAnimeInfoUseCase
import com.example.animefacts.domain.usecase.SetCachedAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val getRandomAnimeInfoUseCase: GetRandomAnimeInfoUseCase,
    private val setCachedAnimeUseCase: SetCachedAnimeUseCase,
): ViewModel(){

    fun loadRandomAnimeInfo(onSuccess: (Int) -> Unit){
        viewModelScope.launch {
            val result = getRandomAnimeInfoUseCase()
            if(result is ApiResult.Success){
                setCachedAnimeUseCase(result.data)
                onSuccess(result.data.id)
        }
    }
}



}