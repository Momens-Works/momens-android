package com.momens.android.presentation.signin.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.momens.android.core.designsystem.component.button.MomensCtaButton
import com.momens.android.core.designsystem.component.type.MomensCtaType
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun SignInBottomContent(
    isGoogleLoginEnabled: Boolean,
    onGoogleLoginClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        MomensCtaButton(
            onClick = onGoogleLoginClick,
            type = MomensCtaType.LOGIN,
            enabled = isGoogleLoginEnabled,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_google),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
                Text(
                    text = "Continue with Google",
                    color = MomensTheme.colors.gray700,
                    style = MomensTheme.typography.bodyB16,
                )
            }
        }
        Text(
            text = "계속하면 서비스 약관과 개인정보 처리방침에 동의하게 됩니다.",
            color = MomensTheme.colors.gray200,
            style = MomensTheme.typography.captionM10,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInBottomContentPreview() {
    MomensTheme {
        SignInBottomContent(
            isGoogleLoginEnabled = true,
            onGoogleLoginClick = {},
        )
    }
}
