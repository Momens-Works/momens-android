package com.momens.android.presentation.onboarding.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
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

@Composable
internal fun OnBoardingSignalContent(
    onSignalTitlePositioned: (Rect) -> Unit,
    onSignalCardPositioned: (Rect) -> Unit,
    onMinsuSuggestionPositioned: (Rect) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        MomensDefaultHeader(onProfileClick = {})

        Spacer(modifier = Modifier.height(12.dp))

        OnBoardingSignalTitleSection(
            onTitlePositioned = onSignalTitlePositioned,
            modifier = Modifier
                .padding(start = 12.dp, end = 20.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        OnBoardingSignalFilter(
            modifier = Modifier.padding(horizontal = 20.dp),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            OnBoardingSignalCard(
                type = SignalTagType.RISK,
                modifier = Modifier.coachmarkTarget(onSignalCardPositioned),
                suggestionModifier = Modifier.coachmarkTarget(onMinsuSuggestionPositioned),
            )

            repeat(3) { index ->
                OnBoardingSignalCard(
                    type = if (index % 2 == 0) SignalTagType.CHANGE else SignalTagType.QUESTION,
                    minsuSuggestion = "내용이 들어갈 공간입니다",
                )
            }
        }
    }
}

@Composable
private fun OnBoardingSignalTitleSection(
    onTitlePositioned: (Rect) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = "오늘 확인해야 할 시그널",
            modifier = Modifier
                .padding(start = 8.dp)
                .coachmarkTarget(onTitlePositioned),
            style = MomensTheme.typography.titleB24,
            color = MomensTheme.colors.black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            text = "프로젝트의 의사결정에 영향을 줄 수 있는 변화입니다.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            style = MomensTheme.typography.bodyM14,
            color = MomensTheme.colors.gray500,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun OnBoardingSignalFilter(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        OnBoardingFilterChip(
            text = "안읽음",
            selected = true,
        )
        OnBoardingFilterChip(
            text = "읽음",
            selected = false,
        )
    }
}

@Composable
private fun OnBoardingFilterChip(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(
                color = if (selected) MomensTheme.colors.primary100 else MomensTheme.colors.white,
                shape = RoundedCornerShape(23.dp),
            )
            .padding(horizontal = 14.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            text = text,
            style = if (selected) MomensTheme.typography.bodyB12 else MomensTheme.typography.bodyM12,
            color = if (selected) MomensTheme.colors.white else MomensTheme.colors.gray400,
        )

        if (selected) {
            Box(
                modifier = Modifier
                    .size(7.dp)
                    .clip(RoundedCornerShape(percent = 50))
                    .background(color = MomensTheme.colors.white.copy(alpha = 0.55f)),
            )
        }
    }
}

@Composable
private fun OnBoardingSignalCard(
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

private fun Modifier.coachmarkTarget(onPositioned: (Rect) -> Unit): Modifier {
    return onGloballyPositioned { coordinates ->
        onPositioned(coordinates.boundsInRoot())
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFEFF1F1)
@Composable
private fun OnBoardingSignalContentPreview() {
    MomensTheme {
        OnBoardingSignalContent(
            onSignalTitlePositioned = {},
            onSignalCardPositioned = {},
            onMinsuSuggestionPositioned = {},
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFEFF1F1)
@Composable
private fun OnBoardingSignalFilterPreview() {
    MomensTheme {
        OnBoardingSignalFilter(
            modifier = Modifier.padding(20.dp),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFEFF1F1)
@Composable
private fun OnBoardingSignalTitleSectionPreview() {
    MomensTheme {
        OnBoardingSignalTitleSection(
            onTitlePositioned = {},
            modifier = Modifier.padding(start = 12.dp, end = 20.dp),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFEFF1F1)
@Composable
private fun OnBoardingFilterChipPreview() {
    MomensTheme {
        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            OnBoardingFilterChip(
                text = "안읽음",
                selected = true,
            )
            OnBoardingFilterChip(
                text = "읽음",
                selected = false,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFEFF1F1)
@Composable
private fun OnBoardingSignalCardPreview() {
    MomensTheme {
        OnBoardingSignalCard(
            type = SignalTagType.RISK,
            modifier = Modifier.padding(20.dp),
        )
    }
}
