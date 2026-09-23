package com.example.presentation.Components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun LabelText(
    text : String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = Color.DarkGray,
        fontSize = 18.sp,
        modifier = modifier
    )

}