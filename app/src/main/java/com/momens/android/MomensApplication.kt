package com.momens.android

import android.app.Application
import com.google.firebase.messaging.FirebaseMessaging
import com.momens.android.core.notification.MomensNotificationManager
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class MomensApplication : Application() {

    @Inject
    lateinit var notificationManager: MomensNotificationManager

    override fun onCreate() {
        super.onCreate()

        initTimber()
        notificationManager.createNotificationChannel()
        logFcmTokenForDebug()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun logFcmTokenForDebug() {
        if (!BuildConfig.DEBUG) return

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Timber.tag(FCM_DEBUG_TAG).d("현재 FCM 토큰: ${task.result}")
            } else {
                Timber.tag(FCM_DEBUG_TAG).e(task.exception, "FCM 토큰 조회 실패")
            }
        }
    }

    private companion object {
        private const val FCM_DEBUG_TAG = "FcmTokenDebug"
    }
}
