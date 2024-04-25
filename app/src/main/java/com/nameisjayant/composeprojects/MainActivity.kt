package com.nameisjayant.composeprojects

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nameisjayant.composeprojects.features.screens.InstagramProfileScreen
import com.nameisjayant.composeprojects.ui.theme.ComposeProjectsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeProjectsTheme {
                InstagramProfileScreen()
            }
        }
    }
}
