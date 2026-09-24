package com.example.presentation.wallet.Components

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@Composable
fun UploadImage(
    modifier: Modifier = Modifier,
    isWalletScreen : Boolean = false,
    image : Uri?,
    selectedImage : (Uri?) -> Unit = {}
) {

    var isImageSelected by remember {
        mutableStateOf(image != null)
    }

    val context = LocalContext.current

    val activityLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        if (uri != null) {
            try {
                context.contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )

            } catch (e: SecurityException) {
                Log.e("URI_PERMISSION", "Could not persist permission", e)
            }

            selectedImage(uri)
            isImageSelected = true
        }
    }


    if (!isImageSelected && !isWalletScreen) {
        UploadImageButton(modifier = Modifier.fillMaxWidth().padding(start = 12.dp, end = 12.dp, top = 12.dp)){
            activityLauncher.launch(arrayOf("image/*"))
        }
    }
    if (isImageSelected) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = modifier.padding(start = 8.dp).size(100.dp)
        ) {

            Box {

                AsyncImage(
                    model = image,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    onError = {
                        Log.e("COIL", "Failed: $image", it.result.throwable)
                    },
                    onSuccess = {
                        Log.d("COIL", "Success: $image")
                    }
                )

                if (!isWalletScreen) {

                    IconButton(
                        modifier = Modifier.size(16.dp).offset(75.dp, y = (2).dp),
                        onClick = {
                            isImageSelected = false
                        },
                        shape = CircleShape,
                        colors = IconButtonColors(
                            contentColor = Color.Black,
                            containerColor = Color.White,
                            disabledContainerColor = Color.White,
                            disabledContentColor = Color.Black,
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "",
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun UploadImageButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier
            .height(60.dp)
            .border(width = 1.dp, color = Color.Gray, RoundedCornerShape(12.dp)),
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black
        ),
        border = null
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Upload,
                contentDescription = null
            )

            Spacer(Modifier.width(8.dp))

            Text(
                text = "Upload Image",
                fontSize = 16.sp
            )
        }
    }
}