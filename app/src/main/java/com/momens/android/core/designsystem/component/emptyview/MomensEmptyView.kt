package com.momens.android.core.designsystem.component.emptyview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensEmptyView(
    text: String,
    modifier: Modifier = Modifier,
    iconColor: Color = MomensTheme.colors.gray200,
    textColor: Color = MomensTheme.colors.gray300,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Icon(
            painter = painterResource(id = R.drawable.ic_minsu),
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(120.dp),
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = text,
            style = MomensTheme.typography.bodyM16,
            color = textColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MomensEmptyViewPreview() {
    MomensTheme {
        MomensEmptyView(
            text = "시그널을 다 확인했어요.",
        )
    }
}
