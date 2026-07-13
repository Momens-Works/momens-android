package com.momens.android.presentation.project.taskdetail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.component.questionbox.MomensQuestionBox
import com.momens.android.core.designsystem.component.sectiontitle.MomensSectionTitle
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.project.taskdetail.model.TaskDetailQuestionModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun TaskDetailQuestionSection(
    questions: ImmutableList<TaskDetailQuestionModel>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        MomensSectionTitle(
            title = "열린질문",
            count = questions.size.toString(),
            isEmphasized = true,
        )

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            questions.forEachIndexed { index, question ->
                key(question.id) {
                    MomensQuestionBox(text = question.body)

                    if (index != questions.lastIndex) {
                        HorizontalDivider(
                            color = MomensTheme.colors.gray100,
                            thickness = 1.dp,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskDetailQuestionSectionPreview() {
    MomensTheme {
        TaskDetailQuestionSection(
            modifier = Modifier.padding(16.dp),
            questions = persistentListOf(
                TaskDetailQuestionModel(
                    id = "1",
                    body = "약한 비밀번호 기준을 사용자에게 얼마나 구체적으로 알려줘야할 지 결정이 필요해보임",
                ),
                TaskDetailQuestionModel(
                    id = "2",
                    body = "약한 비밀번호 기준을 사용자에게 얼마나 구체적으로 알려줘야할 지 결정이 필요해보임",
                ),
            ),
        )
    }
}
