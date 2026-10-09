package com.example.presentation.Home

import android.annotation.SuppressLint
import android.view.SurfaceControl
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.Components.LedgerFab
import com.example.presentation.Components.LedgerTopAppBar
import com.example.presentation.wallet.WalletViewModel
import java.nio.file.WatchEvent

@Composable
fun HomeScreen(
    onNavigation : () -> Unit
) {
    Scaffold(
        topBar = {
            LedgerTopAppBar(
                title = "Home",
            )
        },
        floatingActionButton = {
            LedgerFab {
                onNavigation()
            }
        },
        floatingActionButtonPosition = FabPosition.End

        ){ innerPadding ->


        val walletViewModel = hiltViewModel<WalletViewModel>()

        val totalBudget by walletViewModel.totalBudget.collectAsStateWithLifecycle()


        LaunchedEffect(Unit) {
            walletViewModel.getTotalBudget()
        }

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = Color.White)
        ) {

            HorizontalDivider(thickness = 1.dp)
            
            BudgetCard(totalBudget)

        }

    }
}


@Composable
fun TransactionsList() {

    LazyColumn(

    ) { }

}


@Composable
fun BudgetCard(
    amount : String
) {
    Card(
        modifier = Modifier
            .padding(top = 32.dp)
            .size(width = 354.dp, height = 128.dp)
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black,
        ),
        shape = RoundedCornerShape(8.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "TOTAL BALANCE",
                color = Color.Gray,
                fontSize = 16.sp
            )

            Text(
                text = "$amount L.E",
                color = Color.White,
                fontSize = 40.sp
            )
        }

    }
}