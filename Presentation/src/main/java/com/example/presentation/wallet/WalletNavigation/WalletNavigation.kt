package com.example.presentation.wallet.WalletNavigation

sealed interface WalletNavigation {
    data object NavigateToWalletScreen : WalletNavigation
}