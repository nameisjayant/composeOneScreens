package com.nameisjayant.composeprojects

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nameisjayant.composeprojects.features.screens.ChatScreen
import com.nameisjayant.composeprojects.features.screens.ElectricCarScreen
import com.nameisjayant.composeprojects.features.screens.InstagramHomeScreen
import com.nameisjayant.composeprojects.features.screens.NFTMobileScreen
import com.nameisjayant.composeprojects.features.screens.TaskManagementScreen
import com.nameisjayant.composeprojects.features.screens.TimerScreen
import com.nameisjayant.composeprojects.features.screens.TwitterProfileScreen
import com.nameisjayant.composeprojects.ui.theme.ComposeProjectsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeProjectsTheme {
               TwitterProfileScreen()
            }
        }
    }
}
