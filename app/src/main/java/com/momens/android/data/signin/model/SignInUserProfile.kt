package com.momens.android.data.signin.model

data class SignInUserProfile(
    val idToken: String,
    val email: String?,
    val displayName: String?,
    val profileImageUrl: String?,
)
