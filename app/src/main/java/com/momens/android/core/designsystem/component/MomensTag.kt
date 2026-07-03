package com.momens.android.core.designsystem.component


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme
import androidx.compose.ui.graphics.Color

@Composable
fun MomensTag(
    type: TagType,
    modifier: Modifier = Modifier,
){
    val style = type.toStyle()

    Box(
        modifier = modifier
            .background(style.background, RoundedCornerShape(4.dp))
            .padding(horizontal = 12.dp, vertical = 1.dp)
    ){
        Text(
            text = style.label,
            color = MomensTheme.colors.white,
            style = MomensTheme.typography.captionB10,
        )
    }
}

enum class TagType{
    RISK,
    DECISION,
    CHANGE,
    QUESTION,
}

private data class TagStyle(
    val label: String,
    val background: Color,
)

@Composable
private fun TagType.toStyle() = when (this) {
    TagType.RISK -> TagStyle("Risk", MomensTheme.colors.pointRed)
    TagType.DECISION -> TagStyle("Decision", MomensTheme.colors.pointPurple)
    TagType.CHANGE -> TagStyle("Change", MomensTheme.colors.pointYellow)
    TagType.QUESTION -> TagStyle("Question", MomensTheme.colors.pointMint)
}

@Preview(showBackground = true)
@Composable
private fun MomensTagPreview() {
    MomensTheme {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            MomensTag(type = TagType.RISK)
            MomensTag(type = TagType.DECISION)
            MomensTag(type = TagType.CHANGE)
            MomensTag(type = TagType.QUESTION)
        }
    }
}
