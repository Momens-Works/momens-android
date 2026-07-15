package com.momens.android.data.signal.remote.service

import com.momens.android.data.signal.remote.dto.response.ConvertToTaskResponse
import com.momens.android.data.signal.remote.dto.response.DismissResponse
import com.momens.android.data.signal.remote.dto.response.SignalDetailResponse
import com.momens.android.data.signal.remote.dto.response.SignalListResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SignalService {
    @GET("/api/mobile/projects/{projectId}/signals")
    suspend fun getSignals(
        @Path("projectId") projectId: String,
    ): SignalListResponse

    @GET("/api/mobile/signals/{signalId}")
    suspend fun getSignalDetail(
        @Path("signalId") signalId: String,
    ): SignalDetailResponse

    @POST("/api/mobile/signals/{signalId}/actions/convert-to-task")
    suspend fun convertToTask(
        @Path("signalId") signalId: String,
    ): ConvertToTaskResponse

    @POST("/api/mobile/signals/{signalId}/actions/dismiss")
    suspend fun dismissSignal(
        @Path("signalId") signalId: String,
    ): DismissResponse
}
