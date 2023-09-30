package com.example.quintoplayerapp.Splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    private val viewState = MutableLiveData<SplashViewState>()
    val state: LiveData<SplashViewState> = viewState

    private val useCase by lazy { IsValidCacheUseCase() }

    init {
        viewModelScope.launch {
            if (useCase.isValidCache()) {
                viewState.value = SplashViewState.ShowHome
            } else {
                viewState.value = SplashViewState.ShowLogin
            }
        }
    }

}



