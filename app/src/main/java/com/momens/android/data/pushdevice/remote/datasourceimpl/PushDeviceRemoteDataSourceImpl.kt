package com.momens.android.data.pushdevice.remote.datasourceimpl

import com.momens.android.data.pushdevice.remote.datasource.PushDeviceRemoteDataSource
import com.momens.android.data.pushdevice.remote.dto.request.PushDeviceRegisterRequest
import com.momens.android.data.pushdevice.remote.service.PushDeviceService
import javax.inject.Inject

class PushDeviceRemoteDataSourceImpl @Inject constructor(
    private val pushDeviceService: PushDeviceService,
) : PushDeviceRemoteDataSource {

    override suspend fun registerPushDevice(
        firebaseInstallationId: String,
        fcmRegistrationToken: String,
    ) {
        pushDeviceService.registerPushDevice(
            firebaseInstallationId = firebaseInstallationId,
            request = PushDeviceRegisterRequest(fcmRegistrationToken = fcmRegistrationToken),
        )
    }

    override suspend fun deactivatePushDevice(
        firebaseInstallationId: String,
    ) {
        pushDeviceService.deactivatePushDevice(firebaseInstallationId)
    }
}
