package com.momens.android.core.designsystem.component

import android.R.attr.text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toolingGraphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MomensButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MomensTheme.colors.gray100,
    contentColor: Color = MomensTheme.colors.gray700
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        contentPadding = ButtonDefaults.TextButtonContentPadding
    ){
      Text(text = text, style = MomensTheme.typography.bodyM12)
    }
}

@Preview(showBackground = true)
@Composable
fun MomensButtonPreview(){
    MomensTheme{
        MomensButton(
            text = "버튼",
            onClick = {}
        )
    }
}

@Composable
fun MomensChipButton(
    label: String,
    count: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MomensTheme.colors.white,
    contentColor: Color = MomensTheme.colors.gray800
) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        shape = CircleShape,
        color = backgroundColor,
        contentColor = contentColor
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "$label $count",
                style = MomensTheme.typography.captionM10)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MomensChipButtonPreview(){
    MomensTheme{
        MomensChipButton(
            label = "All",
            count = 2,
            onClick = {}
        )
    }
}

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

@Preview(showBackground = true)
@Composable
fun MomensChipTogglePreview(){
    MomensTheme{
        MomensToggleButton(
            text = "text",
            isSelected = true,
            onCheckedChange = {},
        )
    }
}
