package com.momens.android.core.network

/**
 * OkHttp 로그 메시지가 JSON object인지 확인합니다.
 *
 * 예시
 * ```
 * if (message.isJsonObject()) {
 *     Timber.d(JSONObject(message).toString(4))
 * }
 * ```
 */
fun String?.isJsonObject(): Boolean = this?.trim()?.startsWith("{") == true && this.trim().endsWith("}")

/**
 * OkHttp 로그 메시지가 JSON array인지 확인합니다.
 *
 * 예시
 * ```
 * if (message.isJsonArray()) {
 *     Timber.d(JSONArray(message).toString(4))
 * }
 * ```
 */
fun String?.isJsonArray(): Boolean = this?.trim()?.startsWith("[") == true && this.trim().endsWith("]")
