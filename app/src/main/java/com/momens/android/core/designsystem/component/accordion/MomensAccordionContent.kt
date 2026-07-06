package com.momens.android.core.designsystem.component.accordion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.component.type.MomensAccordionItem
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
internal fun MomensAccordionContent(
    items: List<MomensAccordionItem>
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MomensTheme.colors.gray100,
                RoundedCornerShape(
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            )
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {

        items.forEachIndexed { index, item ->

            MomensAccordionRow(item)

            if (index != items.lastIndex) {
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}

@Composable
private fun MomensAccordionRow(
    item: MomensAccordionItem
) {

    Row {

        Text(
            text = item.title,
            style = MomensTheme.typography.bodyB12,
            color = MomensTheme.colors.black
        )

        Spacer(Modifier.width(20.dp))

        Text(
            text = item.value,
            style = MomensTheme.typography.bodyM12,
            color = MomensTheme.colors.gray500
        )
    }
}
