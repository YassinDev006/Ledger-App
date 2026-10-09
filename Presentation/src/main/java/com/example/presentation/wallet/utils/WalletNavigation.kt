package com.example.presentation.wallet.utils

sealed interface WalletNavigation {
    data object NavigateToWalletScreen : WalletNavigation
}