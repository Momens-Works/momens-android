package com.momens.android.presentation.main.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.momens.android.R
import com.momens.android.core.common.extension.toPushDataOrNull
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.core.model.fcm.PushData
import com.momens.android.presentation.main.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * 알림을 탭해 들어왔을 때 채워지는 푸시 데이터입니다.
     * MainScreen 쪽에서 소비(라우팅)한 뒤 null로 되돌려 재구성 시 중복 라우팅되지 않도록 합니다.
     */
    private var pendingPushData by mutableStateOf<PushData?>(null)

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Momens)
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                scrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                scrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            )
        )

        pendingPushData = intent.toPushDataOrNull()

        setContent {
            MomensTheme {
                MainScreen(
                    pendingPushData = pendingPushData,
                    onPushDataConsumed = { pendingPushData = null },
                )
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)

        pendingPushData = intent.toPushDataOrNull()
    }
}
