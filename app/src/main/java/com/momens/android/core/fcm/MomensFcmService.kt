package com.momens.android.core.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.momens.android.core.local.TokenManager
import com.momens.android.core.model.fcm.PushData
import com.momens.android.core.model.fcm.PushDestination
import com.momens.android.core.notification.MomensNotificationManager
import com.momens.android.data.pushdevice.repository.PushDeviceRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MomensFcmService : FirebaseMessagingService() {

    @Inject
    lateinit var notificationManager: MomensNotificationManager

    @Inject
    lateinit var pushDeviceRepository: PushDeviceRepository

    @Inject
    lateinit var tokenManager: TokenManager

    private val serviceScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Timber.tag(TAG).d("새 FCM 토큰 발급: $token")

        serviceScope.launch {
            // 로그인 전에 토큰이 갱신될 수 있어, 인증 토큰이 있을 때만 서버에 동기화합니다.
            // 로그인 직후에는 SignInRepository에서 별도로 등록을 호출합니다.
            if (tokenManager.getAccessToken().isNullOrBlank()) {
                Timber.tag(TAG).d("로그인 상태가 아니어서 FCM 토큰 동기화를 건너뜁니다.")
                return@launch
            }

            pushDeviceRepository.registerCurrentDevice(fcmRegistrationToken = token)
                .onFailure { Timber.tag(TAG).w(it, "FCM 토큰 서버 동기화 실패") }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val data = message.data
        Timber.tag(TAG).d("푸시 수신 (data): $data, notification: ${message.notification}")

        val pushData = PushData(
            notificationType = data[KEY_NOTIFICATION_TYPE],
            destination = PushDestination.fromRaw(data[KEY_DESTINATION]),
            signalId = data[KEY_SIGNAL_ID],
            projectId = data[KEY_PROJECT_ID],
            workspaceId = data[KEY_WORKSPACE_ID],
            title = message.notification?.title ?: data[KEY_TITLE],
            body = message.notification?.body ?: data[KEY_BODY],
        )

        notificationManager.showPushNotification(pushData)
    }

    private companion object {
        private const val TAG = "MomensFcmService"
        private const val KEY_NOTIFICATION_TYPE = "notification_type"
        private const val KEY_DESTINATION = "destination"
        private const val KEY_SIGNAL_ID = "signal_id"
        private const val KEY_PROJECT_ID = "project_id"
        private const val KEY_WORKSPACE_ID = "workspace_id"
        private const val KEY_TITLE = "title"
        private const val KEY_BODY = "body"
    }
}
