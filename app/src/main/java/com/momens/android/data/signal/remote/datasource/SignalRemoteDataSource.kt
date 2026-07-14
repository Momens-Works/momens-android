package com.momens.android.data.signal.remote.datasource

import com.momens.android.data.signal.remote.dto.response.ConvertToTaskResponse
import com.momens.android.data.signal.remote.dto.response.DismissResponse
import com.momens.android.data.signal.remote.dto.response.SignalDetailResponse
import com.momens.android.data.signal.remote.dto.response.SignalListResponse

interface SignalRemoteDataSource {
    suspend fun getSignals(projectId: String): SignalListResponse

    suspend fun getSignalDetail(signalId: String): SignalDetailResponse

    suspend fun convertToTask(signalId: String): ConvertToTaskResponse

    suspend fun dismissSignal(signalId: String): DismissResponse
}
