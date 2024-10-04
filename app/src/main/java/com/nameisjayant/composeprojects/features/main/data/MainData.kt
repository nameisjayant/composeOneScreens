package com.nameisjayant.composeprojects.features.main.data

import com.nameisjayant.composeprojects.navigation.ChatScreenRoute
import com.nameisjayant.composeprojects.navigation.ChattingScreenRoute
import com.nameisjayant.composeprojects.navigation.ElectricCarScreenRoute
import com.nameisjayant.composeprojects.navigation.HealthAppScreenRoute
import com.nameisjayant.composeprojects.navigation.InstagramHomeScreenRoute
import com.nameisjayant.composeprojects.navigation.InstagramProfileScreenRoute
import com.nameisjayant.composeprojects.navigation.NFTMobileScreenRoute
import com.nameisjayant.composeprojects.navigation.ScoreboardScreenRoute
import com.nameisjayant.composeprojects.navigation.ShoesScreenRoute
import com.nameisjayant.composeprojects.navigation.TaskManagementScreenRoute
import com.nameisjayant.composeprojects.navigation.TeslaScreenRoute
import com.nameisjayant.composeprojects.navigation.TimerScreenRoute
import com.nameisjayant.composeprojects.navigation.TwitterProfileScreenRoute

data class MainData(
    val title: String,
    val route: Any
)

object MainDataObject {

    fun getMainDataList() = listOf(
        MainData(
            title = "Chat Screen",
            route = ChatScreenRoute
        ),
        MainData(
            title = "Chatting Screen",
            route = ChattingScreenRoute
        ),
        MainData(
            title = "Electric car Screen",
            route = ElectricCarScreenRoute
        ),
        MainData(
            title = "Health App Screen",
            route = HealthAppScreenRoute
        ),
        MainData(
            title = "Instagram Home Screen",
            route = InstagramHomeScreenRoute
        ),
        MainData(
            title = "Instagram Profile Screen",
            route = InstagramProfileScreenRoute
        ),
        MainData(
            title = "NFT Mobile Screen",
            route = NFTMobileScreenRoute
        ),
        MainData(
            title = "Scoreboard Screen",
            route = ScoreboardScreenRoute
        ),
        MainData(
            title = "Shoes Screen",
            route = ShoesScreenRoute
        ),
        MainData(
            title = "Task Management Screen",
            route = TaskManagementScreenRoute
        ),
        MainData(
            title = "Tesla Screen",
            route = TeslaScreenRoute
        ),
        MainData(
            title = "Timer Screen",
            route = TimerScreenRoute
        ),
        MainData(
            title = "Twitter Profile Screen",
            route = TwitterProfileScreenRoute
        )
    )
}
