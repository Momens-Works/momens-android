package com.momens.android.core.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import com.momens.android.R
import com.momens.android.core.common.extension.PUSH_EXTRA_BODY
import com.momens.android.core.common.extension.PUSH_EXTRA_DESTINATION
import com.momens.android.core.common.extension.PUSH_EXTRA_NOTIFICATION_TYPE
import com.momens.android.core.common.extension.PUSH_EXTRA_PROJECT_ID
import com.momens.android.core.common.extension.PUSH_EXTRA_SIGNAL_ID
import com.momens.android.core.common.extension.PUSH_EXTRA_TITLE
import com.momens.android.core.common.extension.PUSH_EXTRA_WORKSPACE_ID
import com.momens.android.core.model.fcm.PushData
import com.momens.android.presentation.main.activity.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import timber.log.Timber

@Singleton
class MomensNotificationManager @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return

        val channel = NotificationChannel(
            context.getString(R.string.push_notification_channel_id),
            context.getString(R.string.push_notification_channel_name),
            NotificationManager.IMPORTANCE_HIGH,
        ).apply {
            description = context.getString(R.string.push_notification_channel_description)
        }

        context.getSystemService<NotificationManager>()?.createNotificationChannel(channel)
    }

    fun showPushNotification(pushData: PushData) {
        val title = pushData.title
        val body = pushData.body

        if (title.isNullOrBlank() && body.isNullOrBlank()) {
            Timber.tag(TAG).w("title/body가 모두 비어있어 알림을 표시하지 않습니다: $pushData")
            return
        }

        val pendingIntent = createContentPendingIntent(pushData)

        val notification = NotificationCompat.Builder(
            context,
            context.getString(R.string.push_notification_channel_id),
        )
            .setSmallIcon(R.drawable.ic_notification)
            .setColor(context.getColor(R.color.momens_primary100))
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        runCatching {
            NotificationManagerCompat.from(context).notify(
                System.currentTimeMillis().toInt(),
                notification,
            )
        }.onFailure { throwable ->
            // POST_NOTIFICATIONS 권한이 없는 상태에서 notify를 호출하면 SecurityException이 발생합니다.
            Timber.tag(TAG).w(throwable, "알림 권한이 없어 알림을 표시하지 못했습니다.")
        }
    }

    private fun createContentPendingIntent(pushData: PushData): PendingIntent {
        val intent = Intent(context, MainActivity::class.java).apply {
            action = Intent.ACTION_VIEW
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra(PUSH_EXTRA_NOTIFICATION_TYPE, pushData.notificationType)
            putExtra(PUSH_EXTRA_DESTINATION, pushData.destination.raw)
            putExtra(PUSH_EXTRA_SIGNAL_ID, pushData.signalId)
            putExtra(PUSH_EXTRA_PROJECT_ID, pushData.projectId)
            putExtra(PUSH_EXTRA_WORKSPACE_ID, pushData.workspaceId)
            putExtra(PUSH_EXTRA_TITLE, pushData.title)
            putExtra(PUSH_EXTRA_BODY, pushData.body)
        }

        val flags = PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE

        return PendingIntent.getActivity(
            context,
            System.currentTimeMillis().toInt(),
            intent,
            flags,
        )
    }

    private companion object {
        private const val TAG = "MomensNotificationManager"
    }
}
