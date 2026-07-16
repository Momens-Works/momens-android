package com.momens.android.core.local.onboarding

interface OnboardingManager {
    suspend fun getHasSeenOnboarding(): Boolean

    suspend fun saveHasSeenOnboarding(hasSeen: Boolean)
}
