package com.nameisjayant.composeprojects.features.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nameisjayant.composeprojects.R
import com.nameisjayant.composeprojects.components.AppIcon
import com.nameisjayant.composeprojects.components.SpacerHeight
import com.nameisjayant.composeprojects.ui.theme.ElectricDarkGrayColor
import com.nameisjayant.composeprojects.ui.theme.ElectricDarkGreen
import com.nameisjayant.composeprojects.ui.theme.ElectricGradientBackground
import com.nameisjayant.composeprojects.ui.theme.ElectricLightGreen
import com.nameisjayant.composeprojects.ui.theme.montserratFont

@Composable
fun ElectricCarScreen() {
    ElectricCarRow(
        header = {
            ElectricHeaderRow()
        },
        charge = {
            ChargingRow()
        },
        selection = {
            SelectionRow()
        }
    )
}

@Composable
private fun ElectricCarRow(
    modifier: Modifier = Modifier,
    header: (@Composable () -> Unit)? = null,
    charge: (@Composable () -> Unit)? = null,
    selection: (@Composable () -> Unit)? = null,
    status: (@Composable () -> Unit)? = null,
) {
    LazyColumn(
        modifier = modifier
            .background(ElectricGradientBackground)
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            header?.invoke()
            charge?.invoke()
            selection?.invoke()
            status?.invoke()
        }
    }
}

@Composable
private fun SelectionRow() {
    val list = listOf("Car Status", "Engine Status", "Climate")
    var selected by remember { mutableIntStateOf(0) }
    Row(
        modifier = Modifier.padding(top = 30.dp)
    ) {
        list.forEachIndexed { index, s ->
            SelectionComponent(
                selected = index == selected,
                title = s,
                index = index,
                onValueChange = {
                    selected = it
                })
        }
    }
}

@Composable
private fun SelectionComponent(
    modifier: Modifier = Modifier,
    selected: Boolean,
    title: String,
    index: Int,
    onValueChange: (Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(end = 16.dp)
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null,
                onClick = { onValueChange(index) }
            )
    ) {
        Text(
            text = title, style = LocalTextStyle.current.copy(
                color = if (selected) Color.White else Color(0XFF858a8a),
                fontFamily = montserratFont,
                fontWeight = FontWeight.SemiBold
            )
        )
        AnimatedVisibility(visible = selected) {
            HorizontalDivider(
                thickness = 3.dp,
                modifier = Modifier.width(57.dp).clip(CircleShape),
                color = ElectricLightGreen
            )
        }

    }
}

@Composable
private fun ChargingRow(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(top = 24.dp)
            .border(1.dp, ElectricLightGreen, CircleShape)
            .size(255.dp)
            .padding(12.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .border(
                    12.dp, Brush.verticalGradient(
                        listOf(
                            ElectricLightGreen,
                            Color(0XFF00da99)
                        )
                    ), CircleShape
                )
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            ElectricLightGreen.copy(alpha = 0.33f),
                            ElectricDarkGreen.copy(alpha = 0.33f)
                        )
                    ), CircleShape
                )
                .size(230.dp)
                .padding(60.dp)
        ) {
            AppIcon(icon = R.drawable.charge, modifier = Modifier.size(17.dp))
            SpacerHeight()
            Text(
                text = stringResource(R.string._360_km), style = TextStyle(
                    color = Color.White,
                    fontFamily = montserratFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 48.sp
                )
            )
            Text(
                text = stringResource(R.string.km), style = TextStyle(
                    color = ElectricDarkGrayColor,
                    fontFamily = montserratFont,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            )
        }
    }
    SpacerHeight(24.dp)
    Text(
        text = stringResource(R.string.total_charge), style = TextStyle(
            color = Color(0XFF079c86),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
    )
}

@Composable
private fun ElectricHeaderRow(
    modifier: Modifier = Modifier
) {
    Row {
        Row(modifier = Modifier.weight(1f)) {
            Box(
                modifier = modifier
                    .background(Color.Transparent, CircleShape)
                    .border(
                        2.dp,
                        ElectricLightGreen, CircleShape
                    )
                    .padding(5.dp)
            ) {
                AppIcon(icon = R.drawable.girl, modifier = Modifier.size(62.dp))
            }
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically),
            ) {
                Text(
                    text = stringResource(R.string.tesla_roadster),
                    style = LocalTextStyle.current.copy(
                        color = ElectricDarkGrayColor,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    text = stringResource(R.string.olivia_smith),
                    style = LocalTextStyle.current.copy(
                        color = Color.White,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight.Black,
                        fontSize = 24.sp
                    )
                )
            }
        }
        Box(
            modifier = Modifier
                .background(color = Color.Transparent, CircleShape)
                .border(
                    5.dp, Color(0XFF162e2b),
                    CircleShape
                )
                .align(Alignment.CenterVertically)
        ) {
            Box(
                modifier = Modifier
                    .border(
                        8.dp, Color(0XFF2c4844),
                        CircleShape
                    )
                    .background(color = Color(0XFF1b2826), CircleShape)
                    .padding(18.dp),
                contentAlignment = Alignment.Center
            ) {
                AppIcon(
                    icon = R.drawable.settings, modifier = Modifier
                        .size(22.dp)
                )
            }
        }
    }
}