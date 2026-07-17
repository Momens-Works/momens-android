package com.momens.android.data.pushdevice.remote.datasource

interface PushDeviceRemoteDataSource {

    suspend fun registerPushDevice(
        firebaseInstallationId: String,
        fcmRegistrationToken: String,
    )

    suspend fun deactivatePushDevice(
        firebaseInstallationId: String,
    )
}
