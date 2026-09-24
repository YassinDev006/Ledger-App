package com.example.ledger

import androidx.core.net.toUri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.presentation.Home.HomeScreen
import com.example.presentation.wallet.AddWalletScreen.AddWalletScreen
import com.example.presentation.wallet.UpdateWalletScreen.UpdateWalletScreen
import com.example.presentation.wallet.WalletScreen.WalletScreen
import kotlinx.serialization.Serializable
import com.example.domain.wallet.Entities.Wallet



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
data class UpdateWalletDestination(
    val id : Int,
    val name : String,
    val amount : Double,
    val image : String?
)
@Serializable
object HomeGraph

@Serializable
object HomeDestination

@Serializable
object AddTransactionDestination

fun NavGraphBuilder.walletGraph(navController : NavHostController){
    navigation<WalletGraph>(startDestination = WalletDestination ){

        composable<WalletDestination> {
            WalletScreen(
                onClickItem ={ wallet ->
                    navController.navigate(UpdateWalletDestination(
                        id = wallet.id,
                        name = wallet.name,
                        amount = wallet.amount,
                        image = wallet.image.toString()
                    ))
                } ,
                onNavigation = {
                    navController.navigate(AddWalletDestination)
                }
            )
        }
        composable<AddWalletDestination>{
            AddWalletScreen{
                navController.navigate(WalletDestination){
                    popUpTo(AddWalletDestination){
                        inclusive = true
                    }
                }
            }
        }
        composable<UpdateWalletDestination> {
            val wallet = it.toRoute<UpdateWalletDestination>()
            UpdateWalletScreen(
                wallet = Wallet(
                    name = wallet.name,
                    id = wallet.id,
                    image = wallet.image?.toUri(),
                    amount = wallet.amount
                )
            ) {
                navController.navigate(WalletDestination){
                    popUpTo<UpdateWalletDestination>{
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
        composable<AddTransactionDestination> {

        }
    }

}
