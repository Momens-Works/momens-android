package com.momens.android.core.designsystem.component.dropdown

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.common.extension.noRippleClickable
import com.momens.android.core.designsystem.component.type.MomensSignalItem
import com.momens.android.core.designsystem.component.type.MomensSignalType
import com.momens.android.core.designsystem.theme.MomensTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

private const val MAX_VISIBLE_COUNT = 3

@Composable
fun MomensDropdown(
    items: ImmutableList<MomensSignalItem>,
    modifier: Modifier = Modifier,
) {
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    val defaultItems = items.take(MAX_VISIBLE_COUNT)
    val expandableItems = items.drop(MAX_VISIBLE_COUNT)
    val expandable = expandableItems.isNotEmpty()

    val rotation by animateFloatAsState(
        targetValue = if (expanded) 270f else 90f,
        label = "",
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = MomensTheme.colors.gray100,
                shape = RoundedCornerShape(8.dp),
            )
            .background(MomensTheme.colors.white),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp),
        ) {
            defaultItems.forEachIndexed { index, item ->
                MomensDropdownRow(
                    item = item,
                )

                if (index != defaultItems.lastIndex) {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            AnimatedVisibility(
                visible = expanded && expandable,
                enter = expandVertically(expandFrom = Alignment.Top) + fadeIn(),
                exit = shrinkVertically(shrinkTowards = Alignment.Top) + fadeOut(),
            ) {
                Column(
                    modifier = Modifier.padding(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    expandableItems.forEach {
                        MomensDropdownRow(
                            item = it,
                        )
                    }
                }
            }
        }

        if (expandable) {
            HorizontalDivider(
                color = MomensTheme.colors.gray100,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .noRippleClickable {
                        expanded = !expanded
                    }
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = if (expanded) "접기" else "더보기",
                )

                Spacer(
                    modifier = Modifier.width(4.dp),
                )

                Icon(
                    painter = painterResource(R.drawable.ic_next),
                    contentDescription = null,
                    modifier = Modifier.rotate(rotation),
                    tint = MomensTheme.colors.gray100
                )
            }
        }
    }
}

@Composable
private fun MomensDropdownRow(
    item: MomensSignalItem,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .background(
                    color = item.type.color(),
                    shape = CircleShape,
                ),
        )

        Spacer(
            modifier = Modifier.width(8.dp),
        )

        Text(
            text = item.text,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun MomensSignalType.color() =
    when (this) {
        MomensSignalType.RISK -> MomensTheme.colors.pointRed
        MomensSignalType.QUESTION -> MomensTheme.colors.pointMint
        MomensSignalType.DECISION -> MomensTheme.colors.pointPurple
        MomensSignalType.UNKNOWN -> MomensTheme.colors.gray400 // 협의 필요. UNKNOWN도 처리할지? 하면 무슨색?
    }

@Preview(showBackground = true)
@Composable
private fun MomensDropdownPreview() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        MomensDropdown(
            items = persistentListOf(
                MomensSignalItem(
                    MomensSignalType.DECISION,
                    "소셜 로그인은 MVP 범위에서 제외",
                ),
                MomensSignalItem(
                    MomensSignalType.DECISION,
                    "회원가입 MVP 범위 1차 확정",
                ),
                MomensSignalItem(
                    MomensSignalType.RISK,
                    "Android13+ 권한 요청 플로우 이탈 가능성",
                ),
            ),
        )

        MomensDropdown(
            items = persistentListOf(
                MomensSignalItem(
                    MomensSignalType.DECISION,
                    "소셜 로그인은 MVP 범위에서 제외",
                ),
                MomensSignalItem(
                    MomensSignalType.DECISION,
                    "회원가입 MVP 범위 1차 확정",
                ),
                MomensSignalItem(
                    MomensSignalType.RISK,
                    "Android13+ 권한 요청 플로우 이탈 가능성",
                ),
                MomensSignalItem(
                    MomensSignalType.QUESTION,
                    "푸시 알림 정책 논의 필요",
                ),
                MomensSignalItem(
                    MomensSignalType.QUESTION,
                    "로그인 유지 기간 결정 필요",
                ),
            ),
        )
    }
}
