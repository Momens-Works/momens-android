package com.momens.android.data.pushdevice.remote.service

import com.momens.android.data.pushdevice.remote.dto.request.PushDeviceRegisterRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Path

interface PushDeviceService {
    @PUT("/api/me/push-devices/{firebaseInstallationId}")
    suspend fun registerPushDevice(
        @Path("firebaseInstallationId") firebaseInstallationId: String,
        @Body request: PushDeviceRegisterRequest,
    )

    @DELETE("/api/me/push-devices/{firebaseInstallationId}")
    suspend fun deactivatePushDevice(
        @Path("firebaseInstallationId") firebaseInstallationId: String,
    )
}
