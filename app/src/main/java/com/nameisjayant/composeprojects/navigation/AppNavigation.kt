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
import com.nameisjayant.composeprojects.features.screens.ChatScreen
import com.nameisjayant.composeprojects.features.screens.ChattingScreen
import com.nameisjayant.composeprojects.features.screens.ElectricCarScreen
import com.nameisjayant.composeprojects.features.screens.HealthUIScreen
import com.nameisjayant.composeprojects.features.screens.InstagramHomeScreen
import com.nameisjayant.composeprojects.features.screens.InstagramProfileScreen
import com.nameisjayant.composeprojects.features.screens.NFTMobileScreen
import com.nameisjayant.composeprojects.features.screens.ScoreboardScreen
import com.nameisjayant.composeprojects.features.screens.ShoesScreen
import com.nameisjayant.composeprojects.features.screens.TaskManagementScreen
import com.nameisjayant.composeprojects.features.screens.TeslaScreen
import com.nameisjayant.composeprojects.features.screens.TimerScreen
import com.nameisjayant.composeprojects.features.screens.TwitterProfileScreen


val LocalNavigator = staticCompositionLocalOf<NavHostController> {
    error("error in creating navHostController object")
}

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navHostController = rememberNavController()
    CompositionLocalProvider(
        LocalNavigator provides navHostController
    ) {
        NavHost(
            modifier = modifier,
            navController = navHostController,
            startDestination = MainRoute
        ) {
            composable<MainRoute> {
                MainScreen()
            }
            composable<ChattingScreenRoute> {
                ChattingScreen()
            }
            composable<ChatScreenRoute> {
                ChatScreen()
            }
            composable<ElectricCarScreenRoute> {
                ElectricCarScreen()
            }
            composable<HealthAppScreenRoute> {
                HealthUIScreen()
            }
            composable<InstagramHomeScreenRoute> {
                InstagramHomeScreen()
            }
            composable<InstagramProfileScreenRoute> {
                InstagramProfileScreen()
            }
            composable<NFTMobileScreenRoute> {
                NFTMobileScreen()
            }
            composable<ScoreboardScreenRoute> {
                ScoreboardScreen()
            }
            composable<ShoesScreenRoute> {
                ShoesScreen()
            }
            composable<TaskManagementScreenRoute> {
                TaskManagementScreen()
            }
            composable<TeslaScreenRoute> {
                TeslaScreen()
            }
            composable<TimerScreenRoute> {
                TimerScreen()
            }
            composable<TwitterProfileScreenRoute> {
                TwitterProfileScreen()
            }
        }
    }
}