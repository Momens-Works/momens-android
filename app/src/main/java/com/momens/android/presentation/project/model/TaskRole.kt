package com.momens.android.presentation.project.model

import kotlinx.serialization.Serializable

@Serializable
enum class TaskRole(val label: String) {
    PM("PM"),
    DESIGN("Design"),
    FRONTEND("Frontend"),
    BACKEND("Backend"),
}
