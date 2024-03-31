package com.nameisjayant.composeprojects.features.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nameisjayant.composeprojects.R
import com.nameisjayant.composeprojects.components.AppIcon
import com.nameisjayant.composeprojects.ui.theme.TeslaGradientBg

@Composable
fun TeslaScreen() {
    TeslaRow(
        header = {
            TeslaHeader()
        }
    )
}

@Composable
private fun TeslaRow(
    modifier: Modifier = Modifier,
    header: (@Composable () -> Unit)? = null,
    bottomSheetHeader: (LazyListScope.() -> Unit)? = null,
    bottomSheetCenter: (LazyListScope.() -> Unit)? = null,
    bottomSheetFooter: (LazyListScope.() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .background(
                TeslaGradientBg
            )
            .fillMaxSize()
    ) {
        header?.invoke()
        Box(
            modifier = Modifier
                .border(
                    1.dp,
                    Brush.linearGradient(
                        listOf(
                            Color(0XFF7F8489),
                            Color(0XFF353A40),
                        )
                    ),
                    RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp)
                )
                .background(Color.Transparent)
                .fillMaxSize()
        ) {
            LazyColumn {
                item {
                    bottomSheetHeader?.invoke(this@LazyColumn)
                    bottomSheetCenter?.invoke(this@LazyColumn)
                    bottomSheetFooter?.invoke(this@LazyColumn)
                }
            }
        }
    }
}

@Composable
private fun TeslaHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        HeaderIconComponent(icon = R.drawable.tesla_menu_icon)
        HeaderIconComponent(icon = R.drawable.tesla_profile_icon)
    }
}

@Composable
private fun HeaderIconComponent(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int
) {
    Card(
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(
            8.dp
        )
    ) {
        Box(
            modifier = modifier
                .border(
                    4.dp, Brush.linearGradient(
                        listOf(
                            Color(0XFF2C3036),
                            Color(0XFF31343C)
                        )
                    ), CircleShape
                )
                .background(
                    Color(0XFF2F353A), CircleShape
                )
                .size(60.dp),
            contentAlignment = Alignment.Center
        ) {
            AppIcon(icon = icon)
        }
    }
}