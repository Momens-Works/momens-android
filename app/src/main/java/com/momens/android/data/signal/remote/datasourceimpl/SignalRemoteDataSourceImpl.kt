package com.momens.android.data.signal.remote.datasourceimpl

import com.momens.android.data.signal.remote.datasource.SignalRemoteDataSource
import com.momens.android.data.signal.remote.dto.response.ConvertToTaskResponse
import com.momens.android.data.signal.remote.dto.response.DismissResponse
import com.momens.android.data.signal.remote.dto.response.SignalDetailResponse
import com.momens.android.data.signal.remote.dto.response.SignalListResponse
import com.momens.android.data.signal.remote.service.SignalService
import javax.inject.Inject

class SignalRemoteDataSourceImpl @Inject constructor(
    private val signalService: SignalService,
) : SignalRemoteDataSource {
    override suspend fun getSignals(projectId: String): SignalListResponse =
        signalService.getSignals(projectId = projectId)

    override suspend fun getSignalDetail(signalId: String): SignalDetailResponse =
        signalService.getSignalDetail(signalId = signalId)

    override suspend fun convertToTask(signalId: String): ConvertToTaskResponse =
        signalService.convertToTask(signalId = signalId)

    override suspend fun dismissSignal(signalId: String): DismissResponse =
        signalService.dismissSignal(signalId = signalId)
}
