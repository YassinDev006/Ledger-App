package com.example.presentation.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SharedButton(
    modifier: Modifier = Modifier,
    text : String,
    icon : ImageVector? = null,
    isLoading : Boolean,
    onClickButton: () -> Unit
) {

    Button(
        modifier = modifier.padding(top = 50.dp).fillMaxWidth().padding(horizontal = 12.dp).height(50.dp),
        onClick = onClickButton,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Color.Black
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    tint = Color.White
                )
            }

            if (isLoading){
                CircularProgressIndicator(color = Color.White)
            }else {
                Text(
                    text = text,
                    fontSize = 20.sp
                )
            }

        }
    }

}