package com.momens.android.core.designsystem.component


import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.momens.android.R
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun FloatingComponent(
    onClick: () -> Unit
){
    FloatingActionButton(
        onClick = onClick,
        containerColor = MomensTheme.colors.primary100,
        contentColor = MomensTheme.colors.white,
        shape = CircleShape
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_plus),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FloatingComponentPreview(){
    MomensTheme{
        FloatingComponent(
            onClick = {}
        )
    }
}

