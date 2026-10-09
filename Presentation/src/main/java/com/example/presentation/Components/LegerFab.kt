package com.example.presentation.Components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController


@Composable
fun LedgerFab(
    onClickFab : () -> Unit
) {

    SmallFloatingActionButton(
        onClick = onClickFab,
        containerColor = Color.Black,
        contentColor = Color.White,
        shape = FloatingActionButtonDefaults.largeShape,
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 16.dp),
        modifier = Modifier.padding(bottom = 80.dp).size(60.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = ""
        )
    }

}