package com.momens.android.core.designsystem.component.button
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier.size(50.dp),
        containerColor = MomensTheme.colors.primary100,
        contentColor = MomensTheme.colors.white,
        shape = CircleShape
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = R.drawable.ic_plus),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MomensFloatingActionButtonPreview(){
    MomensTheme{
        MomensFloatingActionButton(
            onClick = {}
        )
    }
}

