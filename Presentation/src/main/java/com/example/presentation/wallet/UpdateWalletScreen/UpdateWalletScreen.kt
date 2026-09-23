//package com.example.presentation.wallet.UpdateWalletScreen
//
//import AddWalletEditText
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Close
//import androidx.compose.material3.HorizontalDivider
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import com.example.presentation.Components.LabelText
//import com.example.presentation.Components.TopAppBar
//
//@Composable
//fun UpdateWallet(
//    onNavigation : () -> Unit
//) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = "Update Wallet",
//                icon = Icons.Filled.Close
//            ) {
//                onNavigation()
//            }
//        }
//    ) {innerPadding ->
//
//        Column(
//            modifier = Modifier
//                .padding(innerPadding)
//                .fillMaxSize()
//                .background(color = Color.White),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            HorizontalDivider(thickness = 1.dp)
//
//
//            var walletName by remember {
//                mutableStateOf("")
//            }
//
//            LabelText(
//                text = "Budget Amount",
//                modifier = Modifier.padding(top = 90.dp, bottom = 16.dp)
//            )
//
//            AddWalletEditText(
//                text = walletName,
//                keyboardType = KeyboardType.Text,
//                imeAction = ImeAction.Done,
//                placeHolder = "Enter Wallet Name",
//                placeHolderSize = 20,
//                placeHolderPosition = TextAlign.Left
//            ) {
//                walletName = it
//            }
//
//
//
//
//        }
//    }
//}