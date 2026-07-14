package com.momens.android.presentation.project.taskedit.model

import kotlinx.serialization.Serializable

@Serializable
enum class TaskRole(val label: String) {
    PM("PM"),
    DESIGN("Design"),
    BACKEND("Backend"),
    FRONTEND("Frontend"),
}
