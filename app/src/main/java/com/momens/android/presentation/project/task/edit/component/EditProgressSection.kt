package com.momens.android.presentation.project.task.edit.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.common.extension.noRippleClickable
import com.momens.android.core.designsystem.component.type.MomensStatusEditType
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun EditProgressSection(
    status: MomensStatusEditType,
    onStatusClick: (MomensStatusEditType) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = "진행사항 수정",
            modifier = Modifier.padding(bottom = 20.dp),
            color = MomensTheme.colors.black,
            style = MomensTheme.typography.bodyB12, // 나오면 변경하기
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ){
            MomensStatusEditType.entries.forEach { type ->
                MomensStatusEdit(
                    type = type,
                    modifier = Modifier.noRippleClickable(onClick = {onStatusClick(type)}),
                    isSelected = status == type,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EditProgressSectionPreview(){
    MomensTheme{
        Box(
            modifier = Modifier.padding(10.dp)
        ){
            EditProgressSection(
                status = MomensStatusEditType.TODO,
                onStatusClick = {}
            )
        }
    }
}
