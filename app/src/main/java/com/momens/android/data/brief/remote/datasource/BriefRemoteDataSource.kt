package com.momens.android.data.brief.remote.datasource

import com.momens.android.data.brief.remote.dto.response.BriefResponse
import com.momens.android.data.brief.remote.dto.response.BriefSignalSummaryPageResponse

interface BriefRemoteDataSource {
    suspend fun getBrief(projectId: String): BriefResponse

    suspend fun getSignalSummary(
        projectId: String,
        filter: String?,
        cursor: String?,
        limit: Int?,
    ): BriefSignalSummaryPageResponse
}
