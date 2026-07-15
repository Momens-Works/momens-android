package com.momens.android.data.signal.repositoryimpl

import com.momens.android.core.util.suspendRunCatching
import com.momens.android.data.signal.model.ConvertToTaskModel
import com.momens.android.data.signal.model.DismissSignalModel
import com.momens.android.data.signal.model.SignalDetailModel
import com.momens.android.data.signal.model.SignalListModel
import com.momens.android.data.signal.model.toModel
import com.momens.android.data.signal.remote.datasource.SignalRemoteDataSource
import com.momens.android.data.signal.repository.SignalRepository
import javax.inject.Inject

class SignalRepositoryImpl @Inject constructor(
    private val signalRemoteDataSource: SignalRemoteDataSource,
) : SignalRepository {
    override suspend fun getSignals(projectId: String): Result<SignalListModel> = suspendRunCatching {
        signalRemoteDataSource.getSignals(projectId = projectId).toModel()
    }

    override suspend fun getSignalDetail(signalId: String): Result<SignalDetailModel> = suspendRunCatching {
        signalRemoteDataSource.getSignalDetail(signalId = signalId).toModel()
    }

    override suspend fun convertToTask(signalId: String): Result<ConvertToTaskModel> = suspendRunCatching {
        signalRemoteDataSource.convertToTask(signalId = signalId).toModel()
    }

    override suspend fun dismissSignal(signalId: String): Result<DismissSignalModel> = suspendRunCatching {
        signalRemoteDataSource.dismissSignal(signalId = signalId).toModel()
    }
}
