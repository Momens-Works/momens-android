package com.momens.android.core.designsystem.component.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme

enum class MomensChipButtonType {
    PURPLE,
    RED,
    BLACK,
    WHITE,
    YELLOW,
    MINT,
}

@Composable
private fun MomensChipButtonType.getColors() = when (this){
    MomensChipButtonType.PURPLE -> Pair(MomensTheme.colors.pointPurple, MomensTheme.colors.white)
    MomensChipButtonType.RED -> Pair(MomensTheme.colors.pointRed, MomensTheme.colors.white)
    MomensChipButtonType.BLACK -> Pair(MomensTheme.colors.gray800, MomensTheme.colors.white)
    MomensChipButtonType.WHITE -> Pair(MomensTheme.colors.white, MomensTheme.colors.gray800)
    MomensChipButtonType.YELLOW -> Pair(MomensTheme.colors.pointYellow, MomensTheme.colors.white)
    MomensChipButtonType.MINT -> Pair(MomensTheme.colors.pointMint, MomensTheme.colors.white)
}

@Composable
fun MomensChipButton(
    label: String,
    count: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    type: MomensChipButtonType = MomensChipButtonType.WHITE
) {
    val(backgroundColor, contentColor) =type.getColors()

    Surface(
        onClick = onClick,
        modifier = modifier,
        shape = CircleShape,
        color = backgroundColor,
        contentColor = contentColor
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(text = label,
                style = MomensTheme.typography.captionM10)
            if (count != null) {
                Text(
                    text = count.toString(),
                    style =MomensTheme.typography.captionM10
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF666E74)
@Composable
fun MomensChipButtonPreview(){
    MomensTheme{
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            MomensChipButton(
                label = "All",
                count = 2,
                type = MomensChipButtonType.WHITE,
                onClick = {}
            )
            MomensChipButton(
                label = "All",
                count = 2,
                type = MomensChipButtonType.BLACK,
                onClick = {}
            )
            MomensChipButton(
                label = "Decision",
                count = 2,
                type = MomensChipButtonType.PURPLE,
                onClick = {}
            )
            MomensChipButton(
                label = "Risk",
                count = 2,
                type = MomensChipButtonType.RED,
                onClick = {}
            )
            MomensChipButton(
                label = "Change",
                count = 2,
                type = MomensChipButtonType.YELLOW,
                onClick = {}
            )
            MomensChipButton(
                label = "Question",
                count = 2,
                type = MomensChipButtonType.MINT,
                onClick = {}
            )
        }
    }
}
