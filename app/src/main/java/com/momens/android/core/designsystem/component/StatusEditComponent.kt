package com.momens.android.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensStatusEditComponent(
    status: String,
    isActive: Boolean
){
    val backgroundColor = if (isActive) MomensTheme.colors.primary10 else MomensTheme.colors.white
    val textColor = if (isActive) MomensTheme.colors.primary100 else MomensTheme.colors.primary100

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_backlog),
            tint = MomensTheme.colors.primary100,
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(3.5.dp))

        Text(
            text = status,
            color = textColor,
            style = MomensTheme.typography.captionM11
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MomensStatusEditComponentPreview(){
    MomensTheme() {
        MomensStatusEditComponent(
            status = "백로그",
            isActive = true
        )
    }
}
