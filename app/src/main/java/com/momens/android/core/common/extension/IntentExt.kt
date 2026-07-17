package com.momens.android.core.common.extension

import android.content.Intent
import com.momens.android.core.model.fcm.PushData
import com.momens.android.core.model.fcm.PushDestination

const val PUSH_EXTRA_NOTIFICATION_TYPE = "notification_type"
const val PUSH_EXTRA_DESTINATION = "destination"
const val PUSH_EXTRA_SIGNAL_ID = "signal_id"
const val PUSH_EXTRA_PROJECT_ID = "project_id"
const val PUSH_EXTRA_WORKSPACE_ID = "workspace_id"
const val PUSH_EXTRA_TITLE = "title"
const val PUSH_EXTRA_BODY = "body"

/**
 * 알림을 탭해 앱에 진입했을 때 전달되는 Intent extra를 [PushData]로 변환합니다.
 *
 * "destination" extra가 없으면 푸시를 통해 들어온 Intent가 아니라고 보고 null을 반환합니다.
 * 값이 알 수 없는 문자열이면 [PushDestination.UNKNOWN]으로 처리되어, 알림 표시는 되었어도
 * 딥링크 라우팅은 하지 않습니다.
 *
 * title/body는 시스템이 백그라운드에서 알림을 자동 표시하고 탭한 경우 data 필드만 Intent extra로
 * 실리기 때문에 null일 수 있습니다 (라우팅에는 필요 없고, 알림 표시 시점에만 필요한 값이라 문제없음).
 *
 * 예시 - MainActivity에서 사용
 * ```
 * val pushData = intent.toPushDataOrNull()
 * ```
 */
fun Intent.toPushDataOrNull(): PushData? {
    val rawDestination = getStringExtra(PUSH_EXTRA_DESTINATION) ?: return null

    return PushData(
        notificationType = getStringExtra(PUSH_EXTRA_NOTIFICATION_TYPE),
        destination = PushDestination.fromRaw(rawDestination),
        signalId = getStringExtra(PUSH_EXTRA_SIGNAL_ID),
        projectId = getStringExtra(PUSH_EXTRA_PROJECT_ID),
        workspaceId = getStringExtra(PUSH_EXTRA_WORKSPACE_ID),
        title = getStringExtra(PUSH_EXTRA_TITLE),
        body = getStringExtra(PUSH_EXTRA_BODY),
    )
}
