package com.momens.android.presentation.main.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.common.extension.noRippleClickable
import com.momens.android.core.designsystem.effect.MomensNavBlurState
import com.momens.android.core.designsystem.effect.momensNavBlur
import com.momens.android.core.designsystem.effect.momensNavBlurSource
import com.momens.android.core.designsystem.effect.rememberMomensNavBlurState
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.main.type.MainTab
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Immutable
data class MomensMainTabBarItemUiModel<T>(
    val key: T,
    val label: String,
    @param:DrawableRes val iconRes: Int,
    val isSelected: Boolean,
)

@Composable
fun <T> MomensMainTabBar(
    tabs: ImmutableList<MomensMainTabBarItemUiModel<T>>,
    onTabClick: (T) -> Unit,
    modifier: Modifier = Modifier,
    navBlurState: MomensNavBlurState? = null,
) {
    val navShape = RoundedCornerShape(40.dp)
    val navBackgroundColor = if (navBlurState == null) MomensTheme.colors.navGray else Color.Transparent

    Box(
        modifier = modifier
            .clip(navShape),
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .momensNavBlur(state = navBlurState)
                .background(
                    color = navBackgroundColor,
                    shape = navShape,
                ),
        )

        Row(
            modifier = Modifier
                .padding(
                    horizontal = 8.dp,
                    vertical = 6.dp,
                ),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            tabs.forEach { tab ->
                MomensMainTabBarItem(
                    tab = tab,
                    onClick = { onTabClick(tab.key) },
                )
            }
        }
    }
}


@Composable
private fun MomensMainTabBarItem(
    tab: MomensMainTabBarItemUiModel<*>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val contentColor = if (tab.isSelected) MomensTheme.colors.primary100 else MomensTheme.colors.gray300
    val textStyle = if (tab.isSelected) MomensTheme.typography.captionB11 else MomensTheme.typography.captionM11
    val backgroundColor = if (tab.isSelected) MomensTheme.colors.white else Color.Transparent

    Column(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(40.dp),
            )
            .noRippleClickable(onClick = onClick)
            .padding(
                horizontal = 20.dp,
                vertical = if (tab.isSelected) 4.dp else 2.dp,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(tab.iconRes),
            contentDescription = tab.label,
            tint = contentColor,
            modifier = Modifier.size(24.dp),
        )

        Text(
            text = tab.label,
            color = contentColor,
            style = textStyle,
        )
    }
}

@Composable
private fun MomensMainTabBarPreviewItem(
    tabs: ImmutableList<MomensMainTabBarItemUiModel<MainTab>>,
    selectedTab: MainTab,
    navBlurState: MomensNavBlurState,
) {
    Box(
        modifier = Modifier.size(
            width = 340.dp,
            height = 88.dp,
        ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .matchParentSize()
                .momensNavBlurSource(state = navBlurState)
                .background(MomensTheme.colors.uiBg)
                .padding(
                    horizontal = 16.dp,
                    vertical = 10.dp,
                ),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            repeat(4) {
                Text(
                    text = "시그널 브리프 태스크 회의록 업데이트 프로젝트 멤버",
                    color = MomensTheme.colors.gray800,
                    style = MomensTheme.typography.bodyM14,
                )
            }
        }

        MomensMainTabBar(
            tabs = tabs.mapSelected(selectedTab = selectedTab),
            onTabClick = { },
            navBlurState = navBlurState,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MomensMainTabBarPreview() {
    val tabs = MainTab.entries
        .map { tab ->
            MomensMainTabBarItemUiModel(
                key = tab,
                label = tab.label,
                iconRes = tab.iconRes,
                isSelected = false,
            )
        }
        .toImmutableList()
    val signalNavBlurState = rememberMomensNavBlurState()
    val briefNavBlurState = rememberMomensNavBlurState()
    val taskNavBlurState = rememberMomensNavBlurState()

    MomensTheme {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            MomensMainTabBarPreviewItem(
                tabs = tabs,
                selectedTab = MainTab.SIGNAL,
                navBlurState = signalNavBlurState,
            )

            MomensMainTabBarPreviewItem(
                tabs = tabs,
                selectedTab = MainTab.BRIEF,
                navBlurState = briefNavBlurState,
            )

            MomensMainTabBarPreviewItem(
                tabs = tabs,
                selectedTab = MainTab.TASK,
                navBlurState = taskNavBlurState,
            )
        }
    }
}

private fun ImmutableList<MomensMainTabBarItemUiModel<MainTab>>.mapSelected(
    selectedTab: MainTab,
): ImmutableList<MomensMainTabBarItemUiModel<MainTab>> = map { tab ->
    tab.copy(isSelected = tab.key == selectedTab)
}.toImmutableList()
