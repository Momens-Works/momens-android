package com.momens.android.presentation.project.taskedit.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.common.extension.noRippleClickable
import com.momens.android.core.designsystem.component.emptyview.MomensEmptyView
import com.momens.android.core.designsystem.component.sectiontitle.MomensSectionTitle
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.project.taskedit.model.ChecklistItemState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun TaskEditCompleteSectionHeader(
    completedCount: Int,
    totalCount: Int,
    isEmpty: Boolean,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MomensSectionTitle(
                title = "완료기준",
                count = "${completedCount}/${totalCount}",
                isEmphasized = true,
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.noRippleClickable(onClick = onAddClick),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = null,
                    tint = MomensTheme.colors.black,
                    modifier = Modifier.size(16.dp),

                    )

                Text(
                    text = "기준 추가",
                    style = MomensTheme.typography.bodyM14,
                    color = MomensTheme.colors.gray800,
                )
            }
        }

        if (isEmpty) {
            MomensEmptyView(
                text = "완료기준이 등록되지 않았어요",
                iconColor = MomensTheme.colors.gray100,
                textColor = MomensTheme.colors.gray200,
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.taskEditChecklistItems(
    rules: ImmutableList<ChecklistItemState>,
    maxLength: Int,
    dragDropState: DragDropState,
    indexOffset: Int,
    onTitleChange: (String, String) -> Unit,
    onCheckedChange: (String, Boolean) -> Unit,
    onClearClick: (String) -> Unit,
) {
    itemsIndexed(
        items = rules,
        key = { _, rule -> rule.localId },
    ) { localIndex, rule ->
        val globalIndex = indexOffset + localIndex
        val bottomSpacing = if (localIndex == rules.lastIndex) 0.dp else 10.dp
        DraggableItem(
            dragDropState = dragDropState,
            index = globalIndex,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = bottomSpacing),
        ) {
            TaskEditCompletionRuleBox(
                rule = rule,
                maxLength = maxLength,
                index = globalIndex,
                dragDropState = dragDropState,
                onTitleChange = onTitleChange,
                onCheckedChange = onCheckedChange,
                onClearClick = { onClearClick(rule.localId) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskEditCompleteSectionHeaderPreview() {
    MomensTheme {
        TaskEditCompleteSectionHeader(
            completedCount = 2,
            totalCount = 4,
            isEmpty = false,
            onAddClick = {},
            modifier = Modifier.padding(10.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskEditCompleteSectionHeaderEmptyPreview() {
    MomensTheme {
        TaskEditCompleteSectionHeader(
            completedCount = 0,
            totalCount = 0,
            isEmpty = true,
            onAddClick = {},
            modifier = Modifier.padding(10.dp),
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
private fun TaskEditChecklistItemsPreview() {
    MomensTheme {
        val exampleState = rememberTextFieldState()
        val writeState = rememberTextFieldState(initialText = "어쩌구저쩌구")
        val listState = rememberLazyListState()
        val dragDropState = rememberDragDropState(lazyListState = listState, onSwap = { _, _ -> })

        LazyColumn(
            state = listState,
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            taskEditChecklistItems(
                rules = persistentListOf(
                    ChecklistItemState(id = "1", localId = "1", title = exampleState.text.toString(), completed = false),
                    ChecklistItemState(id = "2", localId = "2", title = exampleState.text.toString(), completed = true),
                    ChecklistItemState(id = "3", localId = "3", title = writeState.text.toString(), completed = false),
                    ChecklistItemState(id = "4", localId = "4", title = writeState.text.toString(), completed = true),
                ),
                maxLength = 50,
                dragDropState = dragDropState,
                indexOffset = 0,
                onTitleChange = { _, _ -> },
                onCheckedChange = { _, _ -> },
                onClearClick = {},
            )
        }
    }
}
