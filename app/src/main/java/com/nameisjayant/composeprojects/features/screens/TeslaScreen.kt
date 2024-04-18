package com.nameisjayant.composeprojects.features.screens

import android.view.MotionEvent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nameisjayant.composeprojects.R
import com.nameisjayant.composeprojects.components.AppIcon
import com.nameisjayant.composeprojects.components.SpacerHeight
import com.nameisjayant.composeprojects.components.SpacerWidth
import com.nameisjayant.composeprojects.ui.theme.TeslaBlack
import com.nameisjayant.composeprojects.ui.theme.TeslaBlue
import com.nameisjayant.composeprojects.ui.theme.TeslaGradientBg
import com.nameisjayant.composeprojects.ui.theme.TeslaGray
import com.nameisjayant.composeprojects.ui.theme.TeslaWhite
import com.nameisjayant.composeprojects.ui.theme.interFont
import com.nameisjayant.composeprojects.ui.theme.montserratFont
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

@Composable
fun TeslaScreen() {
    TeslaRow(header = {
        TeslaHeader()
    }, bottomSheetHeader = {
        BottomSheetHeader()
    }, bottomSheetFooter = {
        BottomSheetFooter()
    }, bottomSheetCenter = {
        BottomSheetCenter()
    })
}

@Composable
private fun TeslaRow(
    modifier: Modifier = Modifier,
    header: (@Composable () -> Unit)? = null,
    bottomSheetHeader: (@Composable () -> Unit)? = null,
    bottomSheetCenter: (@Composable () -> Unit)? = null,
    bottomSheetFooter: (@Composable () -> Unit)? = null,
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
                    0.4.dp, Brush.linearGradient(
                        listOf(
                            Color(0XFF7F8489),
                            Color(0XFF353A40),
                        )
                    ), RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp)
                )
                .background(Color.Transparent)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                item {
                    bottomSheetHeader?.invoke()
                }
                item {
                    bottomSheetCenter?.invoke()
                }
                item {
                    bottomSheetFooter?.invoke()
                    SpacerHeight(20.dp)
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.tesla), style = TextStyle(
                    color = TeslaGray, fontSize = 18.sp, fontFamily = interFont
                )
            )
            Text(
                text = stringResource(R.string.cybertruck), style = TextStyle(
                    color = TeslaWhite,
                    fontSize = 18.sp,
                    fontFamily = interFont,
                    fontWeight = FontWeight.Black
                )
            )
        }
        HeaderIconComponent(icon = R.drawable.tesla_profile_icon)
    }
}

@Composable
private fun BottomSheetHeader(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpacerHeight(16.dp)
        HorizontalDivider(
            modifier = Modifier
                .width(50.dp)
                .clip(RoundedCornerShape(20.dp)),
            thickness = 4.dp,
            color = TeslaBlack
        )
        SpacerHeight()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 20.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.a_c_is_on), style = TextStyle(
                        color = Color.White,
                        fontSize = 24.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight.Black
                    )
                )
                SpacerHeight()
                Text(
                    text = stringResource(R.string.currently_27_c_will_change_in_2min),
                    style = TextStyle(
                        color = TeslaGray,
                        fontSize = 18.sp,
                        fontFamily = montserratFont,
                    )
                )
            }
            HeaderIconComponent(
                icon = R.drawable.tesla_switch_icon,
                backgroundColor = TeslaBlue,
                borderColor = Brush.linearGradient(
                    listOf(
                        Color(0XFF016BB8),
                        Color(0XFF11A8FD)
                    )
                ),
                modifier = Modifier.size(80.dp)
            )
        }
    }
}

@Composable
private fun BottomSheetFooter(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.mode),
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = montserratFont,
                fontWeight = FontWeight.Black
            )
        )
        SpacerHeight()
        LazyRow(modifier = modifier) {
            item {
                SpacerWidth(20.dp)
            }
            items(modeModalList, key = { it.id }) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(end = 20.dp)
                ) {
                    Text(
                        text = it.title,
                        style = TextStyle(
                            color = TeslaGray,
                            fontSize = 18.sp,
                            fontFamily = montserratFont,
                        )
                    )
                    SpacerHeight()
                    HeaderIconComponent(icon = it.icon)
                }
            }
        }
    }
}

@Composable
private fun BottomSheetCenter(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Cooling()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun Cooling(
    modifier: Modifier = Modifier,
) {
    val currentProgress by remember { mutableFloatStateOf(0.8f) }

    val textMeasure = rememberTextMeasurer()
    val style = TextStyle(
        fontSize = 50.sp,
        color = Color.White,
        fontFamily = interFont,
        fontWeight = FontWeight.ExtraBold
    )
    val textToDraw = "25°C"
    val textLayoutResult = remember(textToDraw) {
        textMeasure.measure(textToDraw, style)
    }

    Canvas(modifier = modifier
        .size(300.dp)
        .pointerInteropFilter { event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    true
                }

                else -> false
            }
        }) {
        val centerX = size.width / 2
        val centerY = size.height / 2
        val radius = min(size.width, size.height) / 2.2f
        val endAngle = 360 * currentProgress
        val endX = centerX + radius * cos(Math.toRadians(endAngle.toDouble())).toFloat()
        val endY = centerY + radius * sin(Math.toRadians(endAngle.toDouble())).toFloat()

        drawCircle(
            brush = Brush.linearGradient(
                listOf(
                    Color(0XFF202428),
                    Color(0XFF131314)
                )
            ),
            min(size.width, size.height) / 1.7f, Offset(centerX, centerY)
        )

        drawCircle(
            Color(0XFF1F2124), radius, Offset(centerX, centerY), style = Stroke(
                width = 50.dp.toPx()
            )
        )


        drawArc(
            color = TeslaBlue,
            startAngle = 0f,
            sweepAngle = endAngle,
            useCenter = false,
            topLeft = Offset(centerX - radius, centerY - radius),
            size = Size(radius * 2, radius * 2),
            style = Stroke(width = 50.dp.toPx(), cap = StrokeCap.Round)
        )


        drawCircle(Color(0XFF1B1D1E), 22.dp.toPx(), Offset(endX, endY))
        drawCircle(TeslaBlue, 3.dp.toPx(), Offset(endX, endY))

        drawText(
            textMeasurer = textMeasure, text = textToDraw,
            style = style,
            topLeft = Offset(
                x = center.x - textLayoutResult.size.width / 2,
                y = center.y - textLayoutResult.size.height / 2,
            )
        )
    }
}

@Composable
private fun HeaderIconComponent(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    backgroundColor: Color = Color(0XFF24282c),
    borderColor: Brush = Brush.linearGradient(
        listOf(
            Color(0XFF2C3036), Color(0XFF31343C)
        )
    )
) {
    Card(
        shape = CircleShape, colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ), elevation = CardDefaults.cardElevation(
            8.dp
        ),
        modifier = modifier.size(60.dp)
    ) {
        Box(
            modifier = Modifier
                .border(
                    4.dp, borderColor, CircleShape
                )
                .background(
                    backgroundColor, CircleShape
                )
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            AppIcon(icon = icon)
        }
    }
}

private data class ModeModal(
    val id: Int,
    val title: String,
    @DrawableRes val icon: Int
)

private val modeModalList = listOf(
    ModeModal(
        1,
        "Auto",
        R.drawable.tesla_switch_icon
    ),
    ModeModal(
        2,
        "Dry",
        R.drawable.tesla_dry_icon
    ),
    ModeModal(
        3,
        "Cool",
        R.drawable.tesla_cool_icon
    ),
    ModeModal(
        4,
        "Program",
        R.drawable.tesla_profile_icon
    ),
)