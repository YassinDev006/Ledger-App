package com.example.presentation.comonents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title : String,
    icon : ImageVector? = null,
    onClickIcon : () -> Unit
){
    CenterAlignedTopAppBar(

        title = {
            Text(
                text = title,
                fontSize = 30.sp
            )
        },
        navigationIcon = {

            if(icon != null) {

                IconButton(
                    onClick = onClickIcon
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "",
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        },
        colors = TopAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
            scrolledContainerColor = Color.Transparent,
            navigationIconContentColor = Color.Black,
            actionIconContentColor = Color.Transparent,
        )


    )

}