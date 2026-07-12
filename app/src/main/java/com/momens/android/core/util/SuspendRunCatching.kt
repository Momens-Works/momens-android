package com.momens.android.core.util

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive

/**
 * suspend 함수에서 예외를 Result로 감싸기 위한 유틸입니다.
 *
 * CancellationException은 Result로 감싸지 않고 다시 던져서 코루틴 취소가 정상 전파되게 합니다.
 *
 * 예시 - Repository에서 API 호출 감싸기
 * ```
 * suspend fun fetchProjects(): Result<List<ProjectResponse>> =
 *     suspendRunCatching {
 *         projectService.getProjects()
 *     }
 * ```
 */
suspend fun <R> suspendRunCatching(block: suspend () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (t: TimeoutCancellationException) {
        Result.failure(t)
    } catch (c: CancellationException) {
        throw c
    } catch (e: Throwable) {
        currentCoroutineContext().ensureActive()
        Result.failure(e)
    }
}
