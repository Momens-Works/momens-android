package com.momens.android.data.brief.repository

import com.momens.android.data.brief.remote.dto.response.BriefResponse
import com.momens.android.data.brief.remote.dto.response.BriefSignalSummaryPageResponse

interface BriefRepository {
    suspend fun getBrief(projectId: String): Result<BriefResponse>

    suspend fun getSignalSummary(
        projectId: String,
        filter: String?,
        cursor: String?,
        limit: Int?,
    ): Result<BriefSignalSummaryPageResponse>
}
