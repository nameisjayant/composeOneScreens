package com.nameisjayant.composeprojects.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nameisjayant.composeprojects.features.main.MainScreen


val LocalNavigator = staticCompositionLocalOf<NavHostController> {
    error("error in creating navHostController object")
}

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navHostController = rememberNavController()
    CompositionLocalProvider(
        LocalNavigator provides navHostController
    ) {
        NavHost(navController = navHostController, startDestination = MainRoute) {
            composable<MainRoute> {
                MainScreen()
            }
        }
    }
}