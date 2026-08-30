package com.example.ledger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ledger.ui.theme.LedgerTheme
import com.example.presentation.DashBoard.DashBoardScreen
import com.example.presentation.splash.SplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LedgerTheme {
                val navController = rememberNavController()
                val entry by navController.currentBackStackEntryAsState()

                val showNavBar = entry?.destination?.hierarchy?.any{
                    it.hasRoute(SplashDestination::class)
                } == false

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (showNavBar) {
                            BottomNavigationBar(navController)
                        }
                    }
                    ) {paddingValues ->

                    Ledger(modifier = Modifier.padding(paddingValues),navController)

                }
            }
        }
    }
}


@Composable
fun Ledger(modifier: Modifier = Modifier,navController: NavHostController) {


    NavHost(
        navController = navController,
        startDestination = SplashDestination

    ){
        composable<SplashDestination>{
            SplashScreen{
                navController.navigate(HomeDestination){
                    popUpTo(SplashDestination){
                        inclusive = true
                    }
                }
            }
        }
        composable<DashBoardDestination> {
            DashBoardScreen()
        }

        homeGraph()

        walletGraph()

    }


}
