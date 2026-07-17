package com.momens.android.data.pushdevice.repository

interface PushDeviceRepository {

    suspend fun registerCurrentDevice(fcmRegistrationToken: String): Result<Unit>

    suspend fun registerCurrentDevice(): Result<Unit>

    suspend fun deactivateCurrentDevice(): Result<Unit>
}
