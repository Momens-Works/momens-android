package com.momens.android.data.brief.remote.datasourceimpl

import com.momens.android.data.brief.remote.datasource.BriefRemoteDataSource
import com.momens.android.data.brief.remote.dto.response.BriefResponse
import com.momens.android.data.brief.remote.dto.response.BriefSignalSummaryPageResponse
import com.momens.android.data.brief.remote.service.BriefService
import javax.inject.Inject

class BriefRemoteDataSourceImpl @Inject constructor(
    private val briefService: BriefService,
) : BriefRemoteDataSource {
    override suspend fun getBrief(projectId: String): BriefResponse {
        return briefService.getBrief(projectId = projectId)
    }

    override suspend fun getSignalSummary(
        projectId: String,
        filter: String?,
        cursor: String?,
        limit: Int?,
    ): BriefSignalSummaryPageResponse {
        return briefService.getSignalSummary(
            projectId = projectId,
            filter = filter,
            cursor = cursor,
            limit = limit,
        )
    }
}
