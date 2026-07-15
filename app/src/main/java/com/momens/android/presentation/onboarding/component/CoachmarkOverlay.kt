package com.momens.android.presentation.onboarding.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.coerceAtMost
import androidx.compose.ui.unit.dp
import com.momens.android.core.common.extension.noRippleClickable
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.onboarding.OnBoardingCoachmarkStep

private val CoachBubbleArrowCenterX = 48.dp
private val HighlightHorizontalPadding = 4.dp
private val HighlightVerticalPadding = 4.dp
private val CoachBubbleFallbackHeight = 142.dp

@Composable
internal fun BoxScope.CoachmarkOverlay(
    step: OnBoardingCoachmarkStep,
    targetBounds: Rect?,
    onNextClick: () -> Unit,
) {
    val targetHighlightBounds = targetBounds ?: return
    val density = LocalDensity.current
    val highlightBounds = targetHighlightBounds.expand(
        horizontalPadding = HighlightHorizontalPadding,
        verticalPadding = HighlightVerticalPadding,
        density = density,
    )

    CoachmarkDimOverlay(highlightBounds = highlightBounds)

    HighlightFrame(
        modifier = Modifier
            .fillMaxWidth()
            .highlightBounds(bounds = highlightBounds),
    )

    when (step) {
        OnBoardingCoachmarkStep.SignalTitle -> {
            CoachBubble(
                title = "오늘 확인해야 할 시그널",
                description = buildAnnotatedString {
                    append("Momens는 ")
                    withStyle(SpanStyle(color = MomensTheme.colors.primary100)) {
                        append("프로젝트의 의사결정에 영향")
                    }
                    append("을\n줄 수 있는 변화를 실시간으로 포착하여 알려드려요")
                },
                page = "1/3",
                buttonText = "다음",
                targetBounds = highlightBounds,
                onNextClick = onNextClick,
            )
        }

        OnBoardingCoachmarkStep.SignalCard -> {
            CoachBubble(
                title = "시그널카드",
                description = buildAnnotatedString {
                    append("각 카드에는 유형(Risk/Decision/Question/Change), 제목,\n영향 범위가 표시돼요. 탭하면 상세내용을 확인할 수 있어요.")
                },
                page = "2/3",
                buttonText = "다음",
                targetBounds = highlightBounds,
                onNextClick = onNextClick,
            )
        }

        OnBoardingCoachmarkStep.MinsuSuggestion -> {
            CoachBubble(
                title = "민수의 제안",
                description = buildAnnotatedString {
                    append("시그널을 분석하여 민수가 제안하는 최적의 대응 방안과\n관련 액션을 확인할 수 있어요.")
                },
                page = "3/3",
                buttonText = "완료",
                targetBounds = highlightBounds,
                onNextClick = onNextClick,
            )
        }
    }
}

@Composable
private fun Modifier.highlightBounds(
    bounds: Rect,
): Modifier {
    val density = LocalDensity.current

    return with(density) {
        offset(
            x = bounds.left.toDp(),
            y = bounds.top.toDp(),
        )
            .size(
                width = bounds.width.toDp(),
                height = bounds.height.toDp(),
            )
    }
}

private fun Rect.expand(
    horizontalPadding: Dp,
    verticalPadding: Dp,
    density: Density,
): Rect = with(density) {
    Rect(
        left = left - horizontalPadding.toPx(),
        top = top - verticalPadding.toPx(),
        right = right + horizontalPadding.toPx(),
        bottom = bottom + verticalPadding.toPx(),
    )
}

@Composable
private fun BoxScope.CoachmarkDimOverlay(
    highlightBounds: Rect,
) {
    val dimColor = MomensTheme.colors.black.copy(alpha = 0.48f)

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.Offscreen
            },
    ) {
        drawRect(color = dimColor)
        drawRoundRect(
            color = Color.Transparent,
            topLeft = Offset(
                x = highlightBounds.left,
                y = highlightBounds.top,
            ),
            size = Size(
                width = highlightBounds.width,
                height = highlightBounds.height,
            ),
            cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx()),
            blendMode = BlendMode.Clear,
        )
    }
}

@Composable
private fun BoxScope.HighlightFrame(
    modifier: Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
    )
}

@Composable
private fun BoxScope.CoachBubble(
    title: String,
    description: AnnotatedString,
    page: String,
    buttonText: String,
    targetBounds: Rect,
    onNextClick: () -> Unit,
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
    ) {
        val density = LocalDensity.current
        val bubbleWidth = maxWidth.coerceAtMost(320.dp)
        var measuredBubbleHeightPx by remember { mutableIntStateOf(0) }
        val bubbleHeight = if (measuredBubbleHeightPx > 0) {
            with(density) { measuredBubbleHeightPx.toDp() }
        } else {
            CoachBubbleFallbackHeight
        }
        val screenWidth = maxWidth
        val screenHeight = maxHeight
        val horizontalMargin = 20.dp
        val verticalGap = 15.dp

        val targetTop = with(density) { targetBounds.top.toDp() }
        val targetBottom = with(density) { targetBounds.bottom.toDp() }
        val targetCenterX = with(density) { targetBounds.center.x.toDp() }

        val minBubbleX = horizontalMargin
        val maxBubbleX = (screenWidth - bubbleWidth - horizontalMargin).coerceAtLeast(horizontalMargin)
        val preferredBubbleX = targetCenterX - bubbleWidth / 2
        val bubbleX = preferredBubbleX.coerceIn(minBubbleX, maxBubbleX)
        val bottomBubbleY = targetBottom + verticalGap
        val topBubbleY = targetTop - bubbleHeight - verticalGap
        val showBelowTarget = bottomBubbleY + bubbleHeight <= screenHeight - horizontalMargin
        val bubbleY = if (showBelowTarget) {
            bottomBubbleY
        } else {
            topBubbleY.coerceAtLeast(horizontalMargin)
        }
        val arrowX = if (showBelowTarget) {
            CoachBubbleArrowCenterX
        } else {
            (targetCenterX - bubbleX).coerceIn(20.dp, bubbleWidth - 20.dp)
        }

        Box(
            modifier = Modifier
                .offset(
                    x = bubbleX,
                    y = bubbleY,
                )
                .width(bubbleWidth)
                .onSizeChanged { size ->
                    measuredBubbleHeightPx = size.height
                },
        ) {
            BubbleBackground(
                arrowX = arrowX,
                arrowOnTop = showBelowTarget,
                modifier = Modifier.matchParentSize(),
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        top = if (showBelowTarget) 28.dp else 16.dp,
                        end = 20.dp,
                        bottom = if (showBelowTarget) 16.dp else 28.dp,
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = title,
                        style = MomensTheme.typography.bodyB16,
                        color = MomensTheme.colors.black,
                    )
                    Text(
                        text = description,
                        style = MomensTheme.typography.bodyM12,
                        color = MomensTheme.colors.gray600,
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = page,
                        style = MomensTheme.typography.bodyM12,
                        color = MomensTheme.colors.gray300,
                    )
                    CoachmarkButton(
                        text = buttonText,
                        onClick = onNextClick,
                    )
                }
            }
        }
    }
}

@Composable
private fun BubbleBackground(
    arrowX: Dp,
    arrowOnTop: Boolean,
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier) {
        val arrowCenterX = arrowX.toPx()
        val arrowHeight = 12.dp.toPx()
        val arrowHalfWidth = 10.dp.toPx()
        val corner = 6.dp.toPx()

        if (arrowOnTop) {
            drawPath(
                path = Path().apply {
                    moveTo(arrowCenterX - arrowHalfWidth, arrowHeight)
                    lineTo(arrowCenterX, 0f)
                    lineTo(arrowCenterX + arrowHalfWidth, arrowHeight)
                    close()
                },
                color = Color.White,
                style = Fill,
            )
        } else {
            drawPath(
                path = Path().apply {
                    moveTo(arrowCenterX - arrowHalfWidth, size.height - arrowHeight)
                    lineTo(arrowCenterX, size.height)
                    lineTo(arrowCenterX + arrowHalfWidth, size.height - arrowHeight)
                    close()
                },
                color = Color.White,
                style = Fill,
            )
        }

        drawRoundRect(
            color = Color.White,
            topLeft = Offset(
                x = 0f,
                y = if (arrowOnTop) arrowHeight else 0f,
            ),
            size = Size(
                width = size.width,
                height = size.height - arrowHeight,
            ),
            cornerRadius = CornerRadius(corner, corner),
        )
    }
}

@Composable
private fun CoachmarkButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(
                color = MomensTheme.colors.primary50,
                shape = RoundedCornerShape(4.dp),
            )
            .noRippleClickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = MomensTheme.typography.bodyB12,
            color = MomensTheme.colors.white,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFEFF1F1)
@Composable
private fun CoachmarkOverlayPreview() {
    MomensTheme {
        val density = LocalDensity.current
        val targetBounds = with(density) {
            Rect(
                left = 20.dp.toPx(),
                top = 120.dp.toPx(),
                right = 340.dp.toPx(),
                bottom = 220.dp.toPx(),
            )
        }

        Box(
            modifier = Modifier
                .size(width = 360.dp, height = 720.dp)
                .background(color = MomensTheme.colors.uiBg),
        ) {
            Box(
                modifier = Modifier
                    .offset(x = 20.dp, y = 120.dp)
                    .size(width = 320.dp, height = 100.dp)
                    .background(
                        color = MomensTheme.colors.white,
                        shape = RoundedCornerShape(8.dp),
                    ),
            )

            CoachmarkOverlay(
                step = OnBoardingCoachmarkStep.SignalTitle,
                targetBounds = targetBounds,
                onNextClick = {},
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFEFF1F1)
@Composable
private fun CoachBubblePreview() {
    MomensTheme {
        val density = LocalDensity.current
        val targetBounds = with(density) {
            Rect(
                left = 20.dp.toPx(),
                top = 120.dp.toPx(),
                right = 340.dp.toPx(),
                bottom = 220.dp.toPx(),
            )
        }

        Box(
            modifier = Modifier
                .size(width = 360.dp, height = 420.dp)
                .background(color = MomensTheme.colors.uiBg),
        ) {
            CoachBubble(
                title = "오늘 확인해야 할 시그널",
                description = buildAnnotatedString {
                    append("Momens는 ")
                    withStyle(SpanStyle(color = MomensTheme.colors.primary100)) {
                        append("프로젝트의 의사결정에 영향")
                    }
                    append("을\n줄 수 있는 변화를 실시간으로 포착하여 알려드려요")
                },
                page = "1/3",
                buttonText = "다음",
                targetBounds = targetBounds,
                onNextClick = {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BubbleBackgroundPreview() {
    MomensTheme {
        Box(
            modifier = Modifier.size(width = 320.dp, height = 142.dp),
        ) {
            BubbleBackground(
                arrowX = 48.dp,
                arrowOnTop = true,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CoachmarkButtonPreview() {
    MomensTheme {
        CoachmarkButton(
            text = "다음",
            onClick = {},
            modifier = Modifier.padding(20.dp),
        )
    }
}
