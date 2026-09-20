package com.example.ledger

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.presentation.Home.HomeScreen
import com.example.presentation.wallet.AddWalletScreen
import com.example.presentation.wallet.WalletScreen
import kotlinx.serialization.Serializable


@Serializable
object SplashDestination

@Serializable
object DashBoardDestination

@Serializable
object  WalletGraph

@Serializable
object WalletDestination

@Serializable
object AddWalletDestination

@Serializable
object HomeGraph

@Serializable
object HomeDestination

@Serializable
object AddTransactionDestination

fun NavGraphBuilder.walletGraph(navController : NavHostController){
    navigation<WalletGraph>(startDestination = WalletDestination ){

        composable<WalletDestination> {
            WalletScreen(onClickItem = {}){
                navController.navigate(AddWalletDestination)

            }
        }
        composable<AddWalletDestination> {
            AddWalletScreen{
                navController.navigate(WalletDestination){
                    popUpTo(AddWalletDestination){
                        inclusive = true
                    }
                }
            }
        }

    }
}
fun NavGraphBuilder.homeGraph(){

    navigation<HomeGraph>(startDestination = HomeDestination){
        composable<HomeDestination> {
            HomeScreen()
        }
        composable<AddTransactionDestination> {  }
    }

}
