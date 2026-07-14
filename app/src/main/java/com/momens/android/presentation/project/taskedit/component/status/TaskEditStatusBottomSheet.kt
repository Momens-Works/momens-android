package com.momens.android.presentation.project.taskedit.component.status

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.component.bottomsheet.MomensBottomSheet
import com.momens.android.core.designsystem.component.type.MomensStatusEditType
import com.momens.android.core.designsystem.theme.MomensTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEditStatusBottomSheet(
    status: MomensStatusEditType,
    onStatusChange: (MomensStatusEditType) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    MomensBottomSheet(
        onDismiss = onDismiss,
        modifier = modifier,
    ) {
        TaskEditStatusSection(
            status = status,
            onStatusChange = onStatusChange,
            modifier = Modifier
                .padding(top = 17.dp, bottom = 16.dp)
                .padding(horizontal = 20.dp),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun TaskEditStatusBottomSheetPreview() {
    MomensTheme {
        var status by remember { mutableStateOf(MomensStatusEditType.TODO) }

        Box(modifier = Modifier.fillMaxSize()) {
            TaskEditStatusBottomSheet(
                status = status,
                onStatusChange = { status = it },
                onDismiss = {},
            )
        }
    }
}
