package com.momens.android.data.signin.model

data class GoogleUserProfile(
    val idToken: String,
    val email: String?,
    val displayName: String?,
    val profileImageUrl: String?,
)
