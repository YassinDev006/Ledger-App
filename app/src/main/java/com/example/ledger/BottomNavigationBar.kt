package com.example.ledger

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState


val bottomNavTabs = BottomNavDestination.bottomNavDestination

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    Column {
        HorizontalDivider(
            thickness = 0.5.dp,
            color = Color.Black
        )


        NavigationBar(
            modifier = Modifier
                .shadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    clip = false,
                    ambientColor = Color.Black.copy(alpha = 0.25f),
                    spotColor = Color.Black.copy(alpha = 0.25f)
                ),
            containerColor = Color.White,
            tonalElevation = 19.dp

        ) {

            val entry by navController.currentBackStackEntryAsState()
            val currentDestination = entry?.destination

            var isSelected by remember {
                mutableStateOf(false)
            }


            bottomNavTabs.forEach { tab ->

                isSelected = currentDestination?.hierarchy?.any {
                    it.hasRoute(tab.destination::class)
                } == true

                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        navController.navigate(tab.destination)
                    },
                    icon = {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(tab.icon),
                                contentDescription = "",
                                tint = if (isSelected) Color.White else Color.Gray,
                                modifier = Modifier.background(
                                    color = if (isSelected) Color.Black else Color.Transparent,
                                    shape = RoundedCornerShape(8.dp)
                                )
                            )
                            Text(
                                text = tab.text,
                                color = if (isSelected) Color.White else Color.Gray,
                                fontSize = 12.sp
                            )
                        }
                    },
                    colors = NavigationBarItemColors(
                        selectedIconColor = Color.White,
                        selectedTextColor = Color.White,
                        selectedIndicatorColor = Color.Black,
                        unselectedIconColor = Color.Transparent,
                        unselectedTextColor = Color.Gray,
                        disabledIconColor = Color.Gray,
                        disabledTextColor = Color.Gray

                    )

                )

            }

        }
    }
}

