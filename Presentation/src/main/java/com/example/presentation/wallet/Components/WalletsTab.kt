package com.example.presentation.wallet.Components

import android.text.Layout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WalletTab(
    walletName : String,
    amount : Double,
    onClickCard : () -> Unit
) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clickable(onClick = onClickCard),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(width = 1.dp, color = Color.Gray),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black,
            )
        ) {

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column() {


                    Text(
                        text = walletName,
                        color = Color.Black,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(start = 12.dp)
                    )

                    Text(
                        text = "$${amount}",
                        color = Color.Gray,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 12.dp)
                    )

                }

                Icon(
                    imageVector = Icons.Filled.ArrowForwardIos,
                    contentDescription = "",
                    modifier = Modifier.padding(end = 12.dp).align(Alignment.CenterEnd)
                )

            }

        }
    }