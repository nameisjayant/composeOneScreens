package com.nameisjayant.composeprojects.features.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nameisjayant.composeprojects.R
import com.nameisjayant.composeprojects.components.AppIcon
import com.nameisjayant.composeprojects.components.SpacerHeight
import com.nameisjayant.composeprojects.components.SpacerWidth
import com.nameisjayant.composeprojects.ui.theme.ElectricBackgroundColor
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
        },
        status = {
            StatusRow()
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
private fun StatusRow(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.height(IntrinsicSize.Max)
    ) {
        BatterySection(modifier = Modifier.weight(0.5f))
        SpacerWidth(16.dp)
        Column(
            modifier = Modifier
                .weight(0.5f)
        ) {
            TyrePressure(modifier = Modifier.weight(0.5f))
            SpacerHeight(20.dp)
            DistanceRow(modifier = Modifier.weight(0.5f))
        }
    }
}

@Composable
private fun DistanceRow(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(ElectricBackgroundColor, RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.total_distance),
            style = MaterialTheme.typography.labelLarge.copy(
                color = Color.White,
                fontFamily = montserratFont,
                fontWeight = FontWeight.SemiBold
            )
        )
        SpacerHeight(16.dp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            AppIcon(icon = R.drawable.distance)
            SpacerWidth(16.dp)
            Text(
                text = stringResource(R.string._20_000_km),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White,
                    fontFamily = montserratFont,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
        SpacerHeight(16.dp)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.check_milage),
                style = MaterialTheme.typography.labelMedium.copy(
                    color = Color(0XFF01F2CF),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = montserratFont,
                ),
            )
            AppIcon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                modifier = Modifier.size(12.dp), tint = Color(0XFF01F2CF)
            )
        }
    }
}

@Composable
private fun TyrePressure(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(ElectricBackgroundColor, RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.tyre_pressure),
            style = MaterialTheme.typography.labelLarge.copy(
                color = Color.White,
                fontFamily = montserratFont,
                fontWeight = FontWeight.SemiBold
            )
        )
        SpacerHeight(20.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppIcon(icon = R.drawable.up, modifier = Modifier.size(10.dp))
                SpacerHeight()
                Text(
                    text = stringResource(R.string.tyre_3),
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = Color.White,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                SpacerHeight()
                AppIcon(icon = R.drawable.down, modifier = Modifier.size(10.dp))
            }
            Column {
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = stringResource(R.string._36),
                        style = TextStyle(
                            color = Color.White,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight.Bold,
                            fontSize = 36.sp
                        )
                    )
                    SpacerWidth()
                    Text(
                        text = stringResource(R.string.psi),
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = ElectricDarkGrayColor,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight.SemiBold,
                            baselineShift = BaselineShift.Superscript
                        )

                    )
                }
                SpacerHeight(5.dp)
                Text(
                    text = stringResource(R.string.optimum),
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = ElectricLightGreen,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
    }
}

@Composable
private fun BatterySection(
    modifier: Modifier = Modifier
) {
    var selected by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .background(ElectricBackgroundColor, RoundedCornerShape(16.dp))
            .width(IntrinsicSize.Max)
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.battery),
            style = MaterialTheme.typography.labelLarge.copy(
                color = Color.White,
                fontFamily = montserratFont,
                fontWeight = FontWeight.SemiBold
            )
        )
        SpacerHeight(20.dp)
        Box(
            modifier = Modifier
                .background(Color.Transparent, RoundedCornerShape(16.dp))
                .border(
                    1.dp,
                    Color.White, RoundedCornerShape(16.dp)
                )
                .height(160.dp)
                .width(111.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color(0XFF01F2CF),
                                Color(0XFF03DA9A),
                                Color(0XFF03B3DA)
                            )
                        ),
                        RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
                    .align(Alignment.BottomCenter),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string._80), style = TextStyle(
                        color = Color.White,
                        fontSize = 47.57.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = stringResource(R.string._percetange), style = TextStyle(
                        color = Color.White,
                        fontSize = 17.84.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
        SpacerHeight(16.dp)
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.saving_mode),
                style = MaterialTheme.typography.labelMedium.copy(
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = montserratFont,
                ),
                modifier = Modifier.weight(1f)
            )
            CheckBoxComponent(selected = selected, onValueChange = {
                selected = it
            })
        }
        SpacerHeight(16.dp)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.find_stations),
                style = MaterialTheme.typography.labelMedium.copy(
                    color = Color(0XFF01F2CF),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = montserratFont,
                ),
            )
            AppIcon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                modifier = Modifier.size(12.dp), tint = Color(0XFF01F2CF)
            )
        }
    }
}

@Composable
private fun CheckBoxComponent(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onValueChange: (Boolean) -> Unit
) {
    Box(
        modifier = modifier
            .background(
                if (selected) Brush.horizontalGradient(
                    listOf(
                        Color(0XFF01F2CF),
                        Color(0XFF03DA9A),
                        Color(0XFF03B3DA)
                    )
                ) else Brush.horizontalGradient(
                    listOf(
                        Color(0XFF01F2CF).copy(alpha = 0.33f),
                        Color(0XFF03DA9A).copy(alpha = 0.33f)
                    )
                ),
                CircleShape
            )
            .width(35.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { onValueChange(!selected) },
            ), contentAlignment = if (selected) Alignment.TopEnd else Alignment.TopStart
    ) {
        Spacer(
            modifier = Modifier
                .size(16.dp)
                .background(
                    brush = Brush.radialGradient(
                        listOf(
                            Color(0XFF182724),
                            Color(0XFF2B534B)
                        )
                    ),
                    CircleShape
                )
        )
    }
}

@Composable
private fun SelectionRow() {
    val list = listOf("Car Status", "Engine Status", "Climate")
    var selected by remember { mutableIntStateOf(0) }
    Row(
        modifier = Modifier
            .padding(top = 30.dp)
            .height(60.dp)
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
                modifier = Modifier
                    .width(57.dp)
                    .clip(CircleShape),
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
            color = Color(0XFF00FFDA),
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
                    .background(
                        brush = Brush.linearGradient(
                            listOf(Color(0XFF2D4945), Color(0XFF2C3546))
                        ), CircleShape
                    )
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