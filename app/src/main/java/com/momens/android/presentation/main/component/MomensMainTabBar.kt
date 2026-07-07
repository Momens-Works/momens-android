package com.momens.android.presentation.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.common.extension.noRippleClickable
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.main.type.MainTab
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun MomensMainTabBar(
    tabs: ImmutableList<MainTab>,
    selectedTab: MainTab,
    onTabClick: (MainTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(
                 color = MomensTheme.colors.navGray,
                shape = RoundedCornerShape(40.dp),
            )
            .padding(6.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        tabs.forEach { tab ->
            MomensMainTabBarItem(
                tab = tab,
                selected = tab == selectedTab,
                onClick = { onTabClick(tab) },
            )
        }
    }
}

@Composable
private fun MomensMainTabBarItem(
    tab: MainTab,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val contentColor = if (selected) MomensTheme.colors.primary100 else MomensTheme.colors.gray300
    val textStyle = if (selected) MomensTheme.typography.captionB11 else MomensTheme.typography.captionM11
    val backgroundColor = if (selected) MomensTheme.colors.white else Color.Transparent
    Column(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(40.dp),
            )
            .noRippleClickable(onClick = onClick)
            .padding(
                horizontal = 20.dp,
                vertical = if (selected) 4.dp else 2.dp,
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

@Preview(showBackground = true)
@Composable
private fun MomensMainTabBarPreview() {
    val tabs = MainTab.entries.toImmutableList()

    MomensTheme {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            MomensMainTabBar(
                tabs = tabs,
                selectedTab = MainTab.SIGNAL,
                onTabClick = {},
            )

            MomensMainTabBar(
                tabs = tabs,
                selectedTab = MainTab.BRIEF,
                onTabClick = {},
            )

            MomensMainTabBar(
                tabs = tabs,
                selectedTab = MainTab.TASK,
                onTabClick = {},
            )
        }
    }
}
