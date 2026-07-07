package com.momens.android.core.designsystem.component.toast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.common.extension.noRippleClickable
import com.momens.android.core.designsystem.component.type.MomensToastType
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensToast(
    title: String,
    type: MomensToastType = MomensToastType.DEFAULT,
    modifier: Modifier = Modifier,
    description: String = "",
    onActionClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MomensTheme.colors.gray700,
                shape = RoundedCornerShape(8.dp),
            )
            .padding(
                horizontal = 14.dp,
                vertical = 12.dp,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(type.iconRes),
            contentDescription = null,
            tint = MomensTheme.colors.gray400,
        )

        Spacer(modifier = Modifier.width(12.dp))

        when (type) {
            MomensToastType.DEFAULT -> {
                Text(
                    text = title,
                    style = MomensTheme.typography.bodyB14,
                    color = MomensTheme.colors.white,
                )
            }

            MomensToastType.BUTTON -> {
                MomensToastTextColumn(
                    title = title,
                    description = description,
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon( //해당 ICON부분은 추후에 갑유 버튼 merge하고 교체 예정
                    painter = painterResource(R.drawable.ic_next),
                    contentDescription = null,
                    modifier = Modifier.noRippleClickable(onClick = onActionClick),
                    tint = MomensTheme.colors.gray300,
                )
            }
        }
    }
}

@Composable
private fun MomensToastTextColumn(
    title: String,
    description: String,
) {
    Column {
        Text(
            text = title,
            style = MomensTheme.typography.bodyB14,
            color = MomensTheme.colors.white,
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = description,
            style = MomensTheme.typography.captionM11,
            color = MomensTheme.colors.gray300,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MomensToastPreview() {
    MomensTheme {
        Box(
            modifier = Modifier
                .height(720.dp)
                .width(360.dp)
                .padding(top = 50.dp)
                .padding(horizontal = 20.dp),
        ) {
            Column(
                modifier = Modifier.align(alignment = Alignment.Center),
            ) {
                MomensToast(
                    title = "토스트 메시지"
                )

                Spacer(modifier = Modifier.height(12.dp))

                MomensToast(
                    title = "우사기",
                    description = "우나 야하~!",
                    type = MomensToastType.BUTTON,
                    onActionClick = {},
                )
            }
        }
    }
}
