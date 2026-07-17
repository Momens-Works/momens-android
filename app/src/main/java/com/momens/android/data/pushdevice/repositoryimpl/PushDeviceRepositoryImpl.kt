package com.momens.android.data.pushdevice.repositoryimpl

import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.messaging.FirebaseMessaging
import com.momens.android.core.util.suspendRunCatching
import com.momens.android.data.pushdevice.remote.datasource.PushDeviceRemoteDataSource
import com.momens.android.data.pushdevice.repository.PushDeviceRepository
import javax.inject.Inject
import kotlinx.coroutines.tasks.await

class PushDeviceRepositoryImpl @Inject constructor(
    private val pushDeviceRemoteDataSource: PushDeviceRemoteDataSource,
) : PushDeviceRepository {

    override suspend fun registerCurrentDevice(fcmRegistrationToken: String): Result<Unit> = suspendRunCatching {
        val firebaseInstallationId = FirebaseInstallations.getInstance().id.await()

        pushDeviceRemoteDataSource.registerPushDevice(
            firebaseInstallationId = firebaseInstallationId,
            fcmRegistrationToken = fcmRegistrationToken,
        )
    }

    override suspend fun registerCurrentDevice(): Result<Unit> = suspendRunCatching {
        val fcmRegistrationToken = FirebaseMessaging.getInstance().token.await()

        registerCurrentDevice(fcmRegistrationToken = fcmRegistrationToken).getOrThrow()
    }

    override suspend fun deactivateCurrentDevice(): Result<Unit> = suspendRunCatching {
        val firebaseInstallationId = FirebaseInstallations.getInstance().id.await()

        pushDeviceRemoteDataSource.deactivatePushDevice(firebaseInstallationId)
    }
}
