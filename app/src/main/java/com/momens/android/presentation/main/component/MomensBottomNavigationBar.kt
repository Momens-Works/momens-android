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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.common.extension.noRippleClickable
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.main.type.MomensBottomNavigationBarType

@Composable
fun MomensBottomNavigationBar(
    selectedType: MomensBottomNavigationBarType,
    onTypeClick: (MomensBottomNavigationBarType) -> Unit,
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
        MomensBottomNavigationBarType.entries.forEach { type ->
            MomensBottomNavigationBarItem(
                type = type,
                selected = type == selectedType,
                onClick = { onTypeClick(type) },
            )
        }
    }
}

@Composable
private fun MomensBottomNavigationBarItem(
    type: MomensBottomNavigationBarType,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val contentColor = if (selected) MomensTheme.colors.primary100 else MomensTheme.colors.gray300
    val textStyle = if (selected) MomensTheme.typography.captionB11 else MomensTheme.typography.captionM11
    val backgroundColor = if(selected) MomensTheme.colors.white else Color.Transparent
    Column(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(40.dp)
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
            painter = painterResource(type.iconRes),
            contentDescription = type.label,
            tint = contentColor,
            modifier = Modifier.size(24.dp),
        )

        Text(
            text = type.label,
            color = contentColor,
            style = textStyle,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MomensBottomNavigationBarPreview() {
    MomensTheme {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            MomensBottomNavigationBar(
                selectedType = MomensBottomNavigationBarType.SIGNAL,
                onTypeClick = {},
            )

            MomensBottomNavigationBar(
                selectedType = MomensBottomNavigationBarType.BRIEF,
                onTypeClick = {},
            )

            MomensBottomNavigationBar(
                selectedType = MomensBottomNavigationBarType.TASK,
                onTypeClick = {},
            )
        }
    }
}
