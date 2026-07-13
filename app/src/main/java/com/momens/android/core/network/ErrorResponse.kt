package com.momens.android.core.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 서버 에러 응답을 파싱하기 위한 공통 DTO입니다.
 *
 * 예시 - 서버 에러 응답
 * ```json
 * {
 *   "error": {
 *     "code": "COMMON_VALIDATION_FAILED",
 *     "message": "요청 값이 올바르지 않습니다."
 *   }
 * }
 * ```
 */
@Serializable
data class ErrorResponse(
    @SerialName("error")
    val error: ErrorBody
)

@Serializable
data class ErrorBody(
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String
)
