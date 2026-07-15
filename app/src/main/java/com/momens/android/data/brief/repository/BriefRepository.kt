package com.momens.android.data.brief.repository

import com.momens.android.data.brief.model.BriefModel
import com.momens.android.data.brief.model.BriefSignalSummaryPageModel

interface BriefRepository {
    suspend fun getBrief(projectId: String): Result<BriefModel>

    suspend fun getSignalSummary(
        projectId: String,
        filter: String?,
        cursor: String?,
        limit: Int?,
    ): Result<BriefSignalSummaryPageModel>
}
