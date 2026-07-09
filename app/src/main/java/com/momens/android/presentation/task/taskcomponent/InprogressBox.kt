package com.momens.android.presentation.project.task.taskcomponent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.designsystem.component.sectiontitle.MomensSectionTitle
import com.momens.android.core.designsystem.component.tasklist.MomensTaskListItem
import com.momens.android.core.designsystem.component.type.ImportantLevel
import com.momens.android.core.designsystem.component.type.ImportantTone
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun InprogressBox() {
    val count = "2"
    val text = "text"
    val label = "Android"
    val level = ImportantLevel.LOW
    val tone = ImportantTone.CLEAR

    Column() {
        MomensSectionTitle(
            title = "진행중",
            count = count,
            isEmphasized = true,
            iconRes = R.drawable.ic_inprogress,
        )

        Spacer(modifier = Modifier.height(12.dp))

        MomensTaskListItem(
            text = text,
            label = label,
            level = level,
            tone = tone,
            count = count,
            onClick = {},
        )

        Spacer(modifier = Modifier.height(8.dp))

        MomensTaskListItem(
            text = text,
            label = label,
            level = level,
            tone = tone,
            count = count,
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InprogressBoxPreview() {
    MomensTheme {
        Box(
            modifier = Modifier
                .padding(
                    horizontal = 20.dp,
                    vertical = 10.dp,
                ),
        ) {
            InprogressBox()
        }
    }
}
