package com.example.presentation.wallet.UpdateWalletScreen

import AddWalletEditText
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.wallet.Entities.Wallet
import com.example.presentation.Components.LabelText
import com.example.presentation.Components.SharedButton
import com.example.presentation.Components.TopAppBar
import com.example.presentation.wallet.Components.UploadImage
import com.example.presentation.wallet.WalletNavigation.WalletNavigation
import com.example.presentation.wallet.WalletViewModel

@Composable
fun UpdateWalletScreen(
    wallet : Wallet,
    onNavigation : () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = "Update Wallet",
                icon = Icons.Filled.Close
            ) {
                onNavigation()
            }
        }
    ) {innerPadding ->

        val walletViewModel = hiltViewModel<WalletViewModel>()

        val isLoading by walletViewModel.isLoading.collectAsStateWithLifecycle()

        var isDeleteButtonLoading by remember {
            mutableStateOf(false)
        }

        var selectedImage by remember {
            mutableStateOf<Uri?>(wallet.image)
        }

        var walletName by remember {
            mutableStateOf(wallet.name)
        }

        LaunchedEffect(Unit) {
            walletViewModel.navigate.collect {
                when(it){
                    WalletNavigation.NavigateToWalletScreen -> onNavigation()
                    else -> {}
                }
            }
        }

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = Color.White),
            horizontalAlignment = Alignment.Start
        ) {
            HorizontalDivider(thickness = 1.dp)




            LabelText(
                text = "Wallet Name",
                modifier = Modifier.padding(start = 12.dp ,top = 90.dp, bottom = 16.dp)
            )

            AddWalletEditText(
                text = walletName,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
                placeHolder = "Enter Wallet Name",
                placeHolderSize = 20,
                placeHolderPosition = TextAlign.Left
            ) {
                walletName = it
            }

            LabelText(
                text = "Wallet Name",
                modifier = Modifier.padding(start = 12.dp ,top = 30.dp, bottom = 16.dp)
            )


            UploadImage(
                modifier = Modifier.size(100.dp),
                isWalletScreen = false,
                image = selectedImage

            ) {
                selectedImage = it
            }

            Row(
                modifier = Modifier.padding(top = 70.dp).weight(1f),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                IconButton(
                    modifier = Modifier.padding(top = 50.dp).size(50.dp).weight(1f).padding(horizontal = 12.dp),
                    onClick = {
                        isDeleteButtonLoading = true
                        walletViewModel.deleteWallet(wallet)
                    },
                    colors = IconButtonColors(
                        contentColor = Color.White,
                        containerColor = Color.Red,
                        disabledContentColor = Color.White,
                        disabledContainerColor = Color.Red
                    ),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    if (!isDeleteButtonLoading) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "",
                        )
                    }else{
                        CircularProgressIndicator(color = Color.White)
                    }
                }

                SharedButton(
                    text = "Update",
                    isLoading = isLoading,
                    modifier = Modifier.weight(3f)
                ) {
                    walletViewModel.updateWallet(
                        wallet = wallet.copy(name = walletName, image = selectedImage )
                    )
                }

            }
        }

    }
}