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
