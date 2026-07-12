package com.momens.android.presentation.signal.component.detail.accodion

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.signal.model.SignalAccordionItem
import com.momens.android.presentation.signal.model.SignalAccordionType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun SignalAccordion(
    type: SignalAccordionType,
    time: String,
    items: ImmutableList<SignalAccordionItem>,
    modifier: Modifier = Modifier,
    initiallyExpanded: Boolean = false,
) {
    var expanded by rememberSaveable {
        mutableStateOf(initiallyExpanded)
    }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        ) {
        SignalAccordionHeader(
            title = type.text,
            time = time,
            expanded = expanded,
            iconResId = type.icon,
            onClick = {
                expanded = !expanded
            },
        )

        AnimatedVisibility(expanded) {

            SignalAccordionContent(
                items = items,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignalAccordionPreview() {
    MomensTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            SignalAccordion(
                type = SignalAccordionType.FIGMA,
                time = "30분 전",
                items = persistentListOf(
                    SignalAccordionItem("text", "text"),
                    SignalAccordionItem("text", "text"),
                    SignalAccordionItem("text", "text"),
                ),
            )

            SignalAccordion(
                time = "12분 전",
                type = SignalAccordionType.SLACK,
                initiallyExpanded = true,
                items = persistentListOf(
                    SignalAccordionItem("text", "text"),
                    SignalAccordionItem("text", "text"),
                    SignalAccordionItem("text", "text"),
                ),
            )
        }
    }
}
