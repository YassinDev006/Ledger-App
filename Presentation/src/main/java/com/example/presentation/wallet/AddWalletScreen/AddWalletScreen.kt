package com.example.presentation.wallet.AddWalletScreen

import AddWalletEditText
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
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
fun AddWalletScreen(
    onNavigation : () -> Unit
) {


    Scaffold(
        topBar = {
            TopAppBar(title = "Add Budget", icon = Icons.Filled.Close){
                onNavigation()
            }
        }
    ) {paddingValues ->



        val walletViewModel = hiltViewModel<WalletViewModel>()

        var budgetAmount by remember {
            mutableStateOf("")
        }

        var selectedImage by remember{
            mutableStateOf<Uri?>(null)
        }


        var walletName by remember {
            mutableStateOf("")
        }

        val isLoading by walletViewModel.isLoading.collectAsStateWithLifecycle()

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
                .padding(paddingValues)
                .fillMaxSize()
                .background(color = Color.White),
            horizontalAlignment = Alignment.Start
        ){
            HorizontalDivider(thickness = 1.dp)


            LabelText(text = "Budget Amount", modifier = Modifier.padding(start = 12.dp,top = 60.dp, bottom = 16.dp))

            AddWalletEditText(
                text = budgetAmount,
                prefix = "$",
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
                placeHolder = "0.00",
                placeHolderSize = 40,
                placeHolderPosition = TextAlign.Center

            ) {
                budgetAmount = it
            }

            LabelText(text = "Wallet Name", modifier = Modifier.padding(start = 12.dp,top = 40.dp, bottom = 16.dp))

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


            LabelText(text = "Icon", modifier = Modifier.padding(start = 12.dp,top = 30.dp))


            UploadImage(
                modifier = Modifier.size(100.dp),
                image = selectedImage,
            ) {
                selectedImage = it
            }



            SharedButton(
                text = "Save Budget",
                isLoading = isLoading
            ) {
                walletViewModel.addWallet(
                    Wallet(
                        name = walletName,
                        amount = budgetAmount.toDouble(),
                        id = 0,
                        image = selectedImage
                    )
                )
            }
        }
    }
}