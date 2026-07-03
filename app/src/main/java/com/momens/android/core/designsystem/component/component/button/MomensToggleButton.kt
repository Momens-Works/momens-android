package com.momens.android.core.designsystem.component.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensToggleButton(
    text: String,
    isSelected: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
){
    val backgroundColor = if (isSelected) MomensTheme.colors.primary100 else MomensTheme.colors.white
    val contentColor = if (isSelected) MomensTheme.colors.white else MomensTheme.colors.gray400
    val dotColor = if (isSelected) MomensTheme.colors.white else MomensTheme.colors.gray400

    Surface(
        onClick = { onCheckedChange(!isSelected) },
        modifier = modifier,
        shape = CircleShape,
        color = backgroundColor,
        contentColor = contentColor
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 14.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = text, style = MomensTheme.typography.bodyB12)

            Spacer(modifier = Modifier.width(10.dp))

            Box(
                modifier = Modifier
                    .size(7.dp)
                    .background(color = dotColor, shape = CircleShape)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFE8E8E)
@Composable
fun MomensToggleButtonPreview(){
    MomensTheme{
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            MomensToggleButton(
                text = "text",
                isSelected = true,
                onCheckedChange = {},
            )
            MomensToggleButton(
                text = "text",
                isSelected = false,
                onCheckedChange = {},
            )
        }
    }
}
