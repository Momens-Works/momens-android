package com.momens.android.core.model.fcm

data class PushData(
    val notificationType: String?,
    val destination: PushDestination,
    val signalId: String?,
    val projectId: String?,
    val workspaceId: String?,
    val title: String?,
    val body: String?,
)
