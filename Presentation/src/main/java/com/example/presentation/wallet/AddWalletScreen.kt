package com.example.presentation.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.wallet.Entities.Wallet
import com.example.presentation.comonents.LabelText
import com.example.presentation.comonents.SharedButton
import com.example.presentation.comonents.TopAppBar

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

        var walletName by remember {
            mutableStateOf("")
        }

        val isLoading by walletViewModel.isLoading.collectAsStateWithLifecycle()


        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            HorizontalDivider(thickness = 1.dp)


            LabelText(text = "Budget Amount", modifier = Modifier.padding(top = 90.dp, bottom = 16.dp))

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

            LabelText(text = "Wallet Name", modifier = Modifier.padding(top = 40.dp, bottom = 16.dp))

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



            SharedButton(
                text = "Save Budget",
                isLoading = isLoading
            ) {
                walletViewModel.addWallet(
                    Wallet(
                        name = walletName,
                        amount = budgetAmount.toDouble(),
                        id = 0
                    )
                )
                onNavigation()

            }

        }
    }
}


@Composable
fun AddWalletEditText(
    text: String,
    prefix : String? = null,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    placeHolder : String,
    placeHolderSize : Int,
    placeHolderPosition : TextAlign,
    onValueChanged : (text : String) -> Unit,

) {

    OutlinedTextField(
        modifier = Modifier.padding(horizontal = 12.dp),
        value = text,
        onValueChange = onValueChanged,
        placeholder = {
            Text(
                text = placeHolder,
                fontSize = placeHolderSize.sp,
                textAlign = placeHolderPosition
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction

        ),
        textStyle = TextStyle(
            fontSize = 40.sp,
        ),
        prefix = {
            Text(
                text = prefix?:"",
                fontSize = 40.sp
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,

            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,

            focusedBorderColor = Color.DarkGray,
            unfocusedBorderColor = Color.DarkGray,

            focusedPlaceholderColor = Color.Transparent,
            unfocusedPlaceholderColor = Color.DarkGray,

            focusedPrefixColor = Color.Black,
            unfocusedPrefixColor = Color.Black


        )



    )

}

