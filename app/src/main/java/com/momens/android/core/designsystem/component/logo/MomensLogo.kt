package com.momens.android.core.designsystem.component.logo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensLogo(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        Image(
            painter = painterResource(R.drawable.ic_login_logo),
            contentDescription = null,
        )

        Text(
            text = "프로젝트 컨텍스트 워크스페이스",
            color = MomensTheme.colors.white,
            style = MomensTheme.typography.bodyB12,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    MomensTheme {
        Column(modifier = Modifier.background(color = MomensTheme.colors.primary100)) {
            MomensLogo()
        }
    }
}
