package com.momens.android.core.network.google.di

import com.momens.android.core.network.google.GoogleSignInLauncher
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface GoogleSignInEntryPoint {
    fun googleSignInLauncher(): GoogleSignInLauncher
}
