package com.momens.android.core.designsystem.component.tasklist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensTaskList(
    text: String,
    label: String,
    level: ImportanceLevel,
    tone: importanceTone,
    count: Int,
    modifier: Modifier = Modifier,
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MomensTheme.colors.white)
            .clip(shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(
            modifier = Modifier
        ){
            Text(
                text = text,
                modifier = Modifier.padding(bottom = 4.dp),
                color = MomensTheme.colors.gray900,
                style = MomensTheme.typography.bodyB14
            )

            TaskListRow(
                label = label,
                level = level,
                tone = tone,
                count = count
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_next),
            contentDescription = null,
            tint = MomensTheme.colors.black
        )
    }
}

@Composable
private fun TaskListRow(
    label: String,
    level: ImportanceLevel,
    tone: importanceTone,
    count: Int,
    modifier: Modifier = Modifier,
){
    Box(
        modifier = Modifier
            .background(
                color = MomensTheme.colors.primary10,
                shape = RoundedCornerShape(4.dp
                )
            )
            .padding(horizontal = 10.dp, vertical = 4.dp),

        ){
        Text(
            text = label,
            color = MomensTheme.colors.gray700,
            style = MomensTheme.typography.bodyM12
        )
    }

    MomensImportanceStatus(
        level = level, tone = tone,
        modifier = modifier.padding(end = 6.dp))

    Icon(
        painter = painterResource(id = R.drawable.ic_clip),
        contentDescription = null,
        tint = MomensTheme.colors.gray600
    )

    Text(
        text = count.toString(),
        color = MomensTheme.colors.gray600,
        style = MomensTheme.typography.bodyM12
    )
}

@Preview(showBackground = true)
@Composable
private fun MomensDividerPreview() {
    MomensTheme {
        MomensTaskList(
            text = "text",
            label = "Android",
            level = ImportantLevel.LOW,
            tone = ImportantTone.GRAY,
            count = 2
        )

    }
}
