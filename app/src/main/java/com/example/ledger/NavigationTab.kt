package com.example.ledger

data class NavigationTab(
    val destination : Any,
    val icon : Int,
    val text : String
)


object BottomNavDestination{
    val bottomNavDestination = listOf<NavigationTab>(
        NavigationTab(
            destination = HomeGraph,
            text = "Home",
            icon = R.drawable.home_icon
        ),
        NavigationTab(
            destination = DashBoardDestination,
            text = "Dashboard",
            icon = R.drawable.dashboard_icon
        ),
        NavigationTab(
            destination = WalletGraph,
            text = "Wallet",
            icon = R.drawable.wallet_icon
        ),

    )
}