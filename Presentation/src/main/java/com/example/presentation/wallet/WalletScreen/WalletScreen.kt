package com.example.presentation.wallet.WalletScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.domain.Entities.Wallet
import com.example.presentation.Components.LabelText
import com.example.presentation.Components.LedgerFab
import com.example.presentation.Components.LedgerTopAppBar
import com.example.presentation.wallet.Components.WalletTab
import com.example.presentation.wallet.WalletViewModel

@Composable
fun WalletScreen(
    onClickItem : (Wallet) -> Unit,
    onNavigation : () -> Unit
) {

    val walletViewModel = hiltViewModel<WalletViewModel>()

    val wallets by walletViewModel.wallets.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        walletViewModel.getWallet()
    }

    Scaffold(
        topBar = {
            LedgerTopAppBar(
                title = "Wallet"
            ) {

            }
        },
        floatingActionButtonPosition = FabPosition.End ,
        floatingActionButton = {
            LedgerFab {
                onNavigation()
            }
        }
    ){ innerPadding ->



        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = Color.White)
        ) {

            HorizontalDivider(thickness = 1.dp)

            Text(
                text = "Budgets",
                color = Color.Black,
                fontSize = 40.sp,
                modifier = Modifier.padding(top = 24.dp).offset(x = 24.dp)

            )

            LabelText(
                    modifier = Modifier.padding(top = 12.dp).offset(x = 24.dp),
                    text = "mange your spending\nLimits"
            )


            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(top = 20.dp, bottom = 100.dp),


            ) {
                items(wallets){ item ->
                    WalletTab(
                        walletName = item.name,
                        amount = item.amount,
                        image = item.image
                    ) {
                        onClickItem(item)
                    }
                }
            }
        }
    }
}