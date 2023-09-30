package com.example.quintoplayerapp.Splash

sealed class SplashViewState {

    object ShowHome : SplashViewState()
    object ShowLogin : SplashViewState()
}