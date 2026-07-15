package com.momens.android.presentation.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
    text: String = "민수가 생각 중입니다.",
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.loading_animation),
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MomensTheme.colors.uiBg),
    ) {
        Spacer(modifier = Modifier.weight(265f))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp),
        ) {
            LottieAnimation(
                composition = composition,
                modifier = Modifier
                    .height(34.dp)
                    .width(120.dp),
                iterations = LottieConstants.IterateForever,
            )

            Text(
                text = text,
                style = MomensTheme.typography.titleB20,
                color = MomensTheme.colors.primary100,
            )
        }

        Spacer(modifier = Modifier.weight(340f))

    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingScreenPreview() {
    MomensTheme {
        LoadingScreen(
            modifier = Modifier.fillMaxSize(),
        )
    }
}
