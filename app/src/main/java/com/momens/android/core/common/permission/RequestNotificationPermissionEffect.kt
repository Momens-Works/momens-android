package com.momens.android.core.common.permission

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import timber.log.Timber

@Composable
fun RequestNotificationPermissionEffect() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
    ) { isGranted ->
        Timber.tag(TAG).d("알림 권한 요청 결과: $isGranted")
    }

    LaunchedEffect(Unit) {
        if (!context.hasNotificationPermission()) {
            launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}

private const val TAG = "NotificationPermission"
