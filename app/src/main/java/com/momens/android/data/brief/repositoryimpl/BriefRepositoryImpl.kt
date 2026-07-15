package com.momens.android.data.brief.repositoryimpl

import com.momens.android.core.util.suspendRunCatching
import com.momens.android.data.brief.model.BriefModel
import com.momens.android.data.brief.model.BriefSignalSummaryPageModel
import com.momens.android.data.brief.model.toModel
import com.momens.android.data.brief.remote.datasource.BriefRemoteDataSource
import com.momens.android.data.brief.repository.BriefRepository
import javax.inject.Inject

class BriefRepositoryImpl @Inject constructor(
    private val briefRemoteDataSource: BriefRemoteDataSource,
) : BriefRepository {
    override suspend fun getBrief(projectId: String): Result<BriefModel> {
        return suspendRunCatching {
            briefRemoteDataSource.getBrief(projectId = projectId).toModel()
        }
    }

    override suspend fun getSignalSummary(
        projectId: String,
        filter: String?,
        cursor: String?,
        limit: Int?,
    ): Result<BriefSignalSummaryPageModel> {
        return suspendRunCatching {
            briefRemoteDataSource.getSignalSummary(
                projectId = projectId,
                filter = filter,
                cursor = cursor,
                limit = limit,
            ).toModel()
        }
    }
}
