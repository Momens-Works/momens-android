package com.momens.android.data.signal.repository

import com.momens.android.data.signal.model.ConvertToTaskModel
import com.momens.android.data.signal.model.DismissSignalModel
import com.momens.android.data.signal.model.SignalDetailModel
import com.momens.android.data.signal.model.SignalListModel

interface SignalRepository {
    suspend fun getSignals(projectId: String): Result<SignalListModel>

    suspend fun getSignalDetail(signalId: String): Result<SignalDetailModel>

    suspend fun convertToTask(signalId: String): Result<ConvertToTaskModel>

    suspend fun dismissSignal(signalId: String): Result<DismissSignalModel>
}
