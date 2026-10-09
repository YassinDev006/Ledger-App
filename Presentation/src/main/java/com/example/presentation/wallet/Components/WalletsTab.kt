package com.example.presentation.wallet.Components

import android.net.Uri
import android.text.Layout
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.offset
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WalletTab(
    walletName : String,
    amount : String,
    image : Uri?,
    onClickCard : () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable(onClick = onClickCard)
            .background(Color.White),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black,
        )
    ) {

        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.background(color = Color.Transparent).padding(vertical = (5).dp)) {


            UploadImage(
                image = image,
                isWalletScreen = true,
                modifier = Modifier.size(50.dp).offset(y = 3.dp)
            )


            Box(
                modifier = Modifier.fillMaxWidth()
            ) {


                Column(
                    verticalArrangement = Arrangement.Center
                ) {


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
}