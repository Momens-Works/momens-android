package com.momens.android.core.designsystem.component.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.momens.android.R
import com.momens.android.core.designsystem.theme.MomensTheme

/**
 * 데이터 로딩 중(Loading) 및 실패(Failure) 상태에서 공통으로 보여주는 로띠 애니메이션 화면입니다.
 *
 * res/raw/loading.json 은 임시 플레이스홀더입니다. 디자이너가 제공하는 실제 로띠 파일로 교체해주세요.
 */
@Composable
fun MomensLoadingScreen(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MomensTheme.colors.uiBg),
        contentAlignment = Alignment.Center,
    ) {
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.size(120.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MomensLoadingScreenPreview() {
    MomensTheme {
        MomensLoadingScreen()
    }
}
