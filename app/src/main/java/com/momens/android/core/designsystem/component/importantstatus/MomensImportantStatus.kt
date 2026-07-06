package com.momens.android.core.designsystem.component.importantstatus

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensImportantStatus(
    level: ImportanceLevel,
    tone: ImportanceTone,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(4.dp))
            .background(
                color = tone.background(),
            )
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(level.iconFor(tone)),
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 2.dp)
                .padding(end = 4.dp),
        )

        Text(
            text = level.text,
            color = tone.textColor(),
            style = tone.textStyle(),
        )
    }
}

enum class ImportanceLevel(
    val text: String,
    @DrawableRes val grayIcon: Int,
    @DrawableRes val blueIcon: Int,
) {
    LOW(
        text = "낮음",
        grayIcon = R.drawable.ic_importance_low_gray,
        blueIcon = R.drawable.ic_importance_low_blue,
    ),
    MEDIUM(
        text = "중간",
        grayIcon = R.drawable.ic_importance_medium_gray,
        blueIcon = R.drawable.ic_importance_medium_blue,
    ),
    HIGH(
        text = "높음",
        grayIcon = R.drawable.ic_importance_high_gray,
        blueIcon = R.drawable.ic_importance_high_blue,
    ),
}

enum class ImportanceTone(
    val textStyle: @Composable () -> TextStyle,
    val textColor: @Composable () -> Color,
    val background: @Composable () -> Color,
) {
    GRAY(
        textStyle = { MomensTheme.typography.bodyM12 },
        textColor = { MomensTheme.colors.gray700 },
        background = { MomensTheme.colors.gray100 },
    ),
    BLUE(
        textStyle = { MomensTheme.typography.bodyB12 },
        textColor = { MomensTheme.colors.primary10 },
        background = { MomensTheme.colors.primary50 },
    ),
}

@DrawableRes
fun ImportanceLevel.iconFor(tone: ImportanceTone): Int = when (tone) {
    ImportanceTone.GRAY -> grayIcon
    ImportanceTone.BLUE -> blueIcon
}

@Preview(showBackground = true)
@Composable
private fun MomensImportantStatusPreview() {
    MomensTheme {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            MomensImportantStatus(level = ImportanceLevel.LOW, tone = ImportanceTone.GRAY)
            MomensImportantStatus(level = ImportanceLevel.MEDIUM, tone = ImportanceTone.GRAY)
            MomensImportantStatus(level = ImportanceLevel.HIGH, tone = ImportanceTone.GRAY)
            MomensImportantStatus(level = ImportanceLevel.LOW, tone = ImportanceTone.BLUE)
            MomensImportantStatus(level = ImportanceLevel.MEDIUM, tone = ImportanceTone.BLUE)
            MomensImportantStatus(level = ImportanceLevel.HIGH, tone = ImportanceTone.BLUE)
        }
    }
}


