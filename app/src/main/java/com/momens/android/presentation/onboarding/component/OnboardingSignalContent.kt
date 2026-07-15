package com.momens.android.presentation.onboarding.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.designsystem.component.header.MomensDefaultHeader
import com.momens.android.core.designsystem.component.tag.MomensSignalTag
import com.momens.android.core.designsystem.component.textbox.MomensTextBox
import com.momens.android.core.designsystem.component.type.SignalTagType
import com.momens.android.core.designsystem.effect.momensUiShadow
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.onboarding.extension.coachmarkTarget

@Composable
fun OnboardingSignalContent(
    onSignalTitlePositioned: (Rect) -> Unit,
    onSignalCardPositioned: (Rect) -> Unit,
    onMinsuSuggestionPositioned: (Rect) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
    ) {
        MomensDefaultHeader(onProfileClick = {})

        Spacer(modifier = Modifier.height(12.dp))

        OnboardingSignalTitleSection(
            onTitlePositioned = onSignalTitlePositioned,
            modifier = Modifier
                .padding(start = 12.dp, end = 20.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            OnboardingSignalCard(
                type = SignalTagType.RISK,
                modifier = Modifier.coachmarkTarget(onSignalCardPositioned),
                suggestionModifier = Modifier.coachmarkTarget(onMinsuSuggestionPositioned),
            )

            repeat(3) { index ->
                OnboardingSignalCard(
                    type = if (index % 2 == 0) SignalTagType.CHANGE else SignalTagType.QUESTION,
                    minsuSuggestion = "content",
                )
            }
        }
    }
}

@Composable
private fun OnboardingSignalTitleSection(
    onTitlePositioned: (Rect) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 14.dp, end = 14.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = "오늘 확인해야 할 시그널",
            modifier = Modifier
                .fillMaxWidth()
                .coachmarkTarget(
                    onTitlePositioned,
                    startPadding = 6.dp,
                ),
            style = MomensTheme.typography.titleB24,
            color = MomensTheme.colors.black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            text = "프로젝트의 의사결정에 영향을 줄 수 있는 변화입니다.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 4.dp),
            style = MomensTheme.typography.bodyM14,
            color = MomensTheme.colors.gray500,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun OnboardingSignalCard(
    type: SignalTagType,
    modifier: Modifier = Modifier,
    suggestionModifier: Modifier = Modifier,
    minsuSuggestion: String = "내용이 들어갈 공간입니다",
) {
    val shape = RoundedCornerShape(8.dp)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .momensUiShadow(shape = shape)
            .background(
                color = MomensTheme.colors.white,
                shape = shape,
            )
            .padding(all = 16.dp),
    ) {
        MomensSignalTag(type = type)

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Android 13+ 권한 요청 플로우에서 이탈 가능성 발견",
            style = MomensTheme.typography.bodyB16,
            color = MomensTheme.colors.black,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = "MVP 완료율과 온보딩 품질에 영향을 줄 수 있습니다.",
            style = MomensTheme.typography.bodyM12,
            color = MomensTheme.colors.gray500,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(16.dp))

        MomensTextBox(
            text = minsuSuggestion,
            iconResId = R.drawable.ic_minsu,
            modifier = suggestionModifier,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFEFF1F1)
@Composable
private fun OnboardingSignalContentPreview() {
    MomensTheme {
        OnboardingSignalContent(
            onSignalTitlePositioned = {},
            onSignalCardPositioned = {},
            onMinsuSuggestionPositioned = {},
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFEFF1F1)
@Composable
private fun OnboardingSignalCardPreview() {
    MomensTheme {
        OnboardingSignalCard(
            type = SignalTagType.RISK,
            modifier = Modifier.padding(20.dp),
        )
    }
}
