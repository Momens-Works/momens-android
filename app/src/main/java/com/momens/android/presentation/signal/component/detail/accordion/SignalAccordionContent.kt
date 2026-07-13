package com.momens.android.presentation.signal.component.detail.accordion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.signal.model.SignalAccordionItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun SignalAccordionContent(
    items: ImmutableList<SignalAccordionItem>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MomensTheme.colors.gray100,
                shape = RoundedCornerShape(
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp,
                ),
            )
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items.forEach { item ->
            SignalAccordionRow(item)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignalAccordionContentPreview() {
    MomensTheme {
        SignalAccordionContent(
            items = persistentListOf(
                SignalAccordionItem(title = "대상", value = "권한 요청 화면"),
                SignalAccordionItem(title = "변화", value = "권한 요청 단계 이탈률이 오른 것으로 보임"),
                SignalAccordionItem(title = "영향", value = "회원가입 완료율이 떨어질 수 있음"),
            ),
        )
    }
}
