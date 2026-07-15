package com.momens.android.data.brief.remote.service

import com.momens.android.data.brief.remote.dto.response.BriefResponse
import com.momens.android.data.brief.remote.dto.response.BriefSignalSummaryPageResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BriefService {
    @GET("/api/mobile/projects/{projectId}/brief")
    suspend fun getBrief(
        @Path("projectId") projectId: String,
    ): BriefResponse

    @GET("/api/mobile/projects/{projectId}/brief/signal-summary")
    suspend fun getSignalSummary(
        @Path("projectId") projectId: String,
        @Query("filter") filter: String? = null,
        @Query("cursor") cursor: String? = null,
        @Query("limit") limit: Int? = null,
    ): BriefSignalSummaryPageResponse
}
