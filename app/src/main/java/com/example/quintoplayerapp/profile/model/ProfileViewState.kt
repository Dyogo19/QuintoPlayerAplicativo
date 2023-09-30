package com.example.quintoplayerapp.profile.model

sealed class ProfileViewState {
    object ShowEmailErrorMessage : ProfileViewState()
    object ShowPasswordErrorMessage : ProfileViewState()
    object ShowNameErrorMessage : ProfileViewState()
    object ShowNumberErrorMessage : ProfileViewState()
    object ShowErrorMessage : ProfileViewState()
    object ShowHomeScreen : ProfileViewState()
    object ShowLoading : ProfileViewState()

    object ShowSuccesCreate : ProfileViewState()



}