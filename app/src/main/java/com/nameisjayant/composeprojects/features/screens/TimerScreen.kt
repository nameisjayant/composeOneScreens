package com.nameisjayant.composeprojects.features.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nameisjayant.composeprojects.R
import com.nameisjayant.composeprojects.components.AppIconButton
import com.nameisjayant.composeprojects.components.SpacerHeight
import com.nameisjayant.composeprojects.components.SpacerWidth
import com.nameisjayant.composeprojects.ui.theme.TimerDarkColor
import com.nameisjayant.composeprojects.ui.theme.TimerDarkPurple
import com.nameisjayant.composeprojects.ui.theme.TimerGrayColor
import com.nameisjayant.composeprojects.ui.theme.TimerLightDarkBlue
import com.nameisjayant.composeprojects.ui.theme.TimerPinkColor
import com.nameisjayant.composeprojects.ui.theme.TimerLinearGradient
import com.nameisjayant.composeprojects.ui.theme.montserratFont


@Composable
fun TimerScreen() {
    TimerRow(
        header = { TimerHeader() },
        body = { TimerBody() }
    ) {
        TimerFooter()
    }
}

@Composable
private fun TimerRow(
    modifier: Modifier = Modifier,
    header: (@Composable () -> Unit)? = null,
    body: (@Composable () -> Unit)? = null,
    footer: (@Composable () -> Unit)? = null
) {
    Column(
        modifier = modifier
            .background(TimerDarkColor)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        header?.invoke()
        LazyColumn(
            modifier = Modifier.weight(1f)
        ){
            item {
                body?.invoke()
            }
        }

        footer?.invoke()
    }
}

@Composable
private fun TimerHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(
                top = 20.dp,
                end = 30.dp
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppIconButton(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            tint = Color.White
        ) {}
        Text(
            text = stringResource(R.string.rasion_project), style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = montserratFont,
                color = Color.White
            )
        )
        Text(
            text = stringResource(R.string.work), style = TextStyle(
                fontSize = 12.sp,
                fontFamily = montserratFont,
                color = TimerPinkColor
            ),
            modifier = Modifier
                .drawBehind {
                    drawRoundRect(
                        color = TimerPinkColor,
                        cornerRadius = CornerRadius(16f),
                        style = Stroke(
                            width = 3f
                        )
                    )
                }
                .padding(6.dp)
        )
    }
}

@Composable
private fun TimerBody(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        SpacerHeight(80.dp)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(
                modifier = Modifier
                    .border(2.dp, TimerLinearGradient, CircleShape)
                    .size(16.dp)
            )
            SpacerWidth()
            Text(
                text = stringResource(R.string.development), style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = montserratFont,
                    color = TimerGrayColor
                )
            )
        }
        SpacerHeight(80.dp)
       DrawCircularProgress()
    }
}

@Composable
private fun DrawCircularProgress(
    modifier: Modifier = Modifier
) {
    val textMeasure = rememberTextMeasurer()
    val style = TextStyle(
        fontSize = 40.sp,
        color = Color.White,
        fontFamily = montserratFont,
        fontWeight = FontWeight.ExtraBold
    )
    val textToDraw = stringResource(R.string._32_10)
    val textLayoutResult = remember(textToDraw) {
        textMeasure.measure(textToDraw, style)
    }
    Canvas(modifier = modifier.size(220.dp)){

        drawCircle(
            color = TimerDarkPurple,
            style = Stroke(
                width = 16.dp.toPx()
            )
        )
        drawArc(
            brush = TimerLinearGradient,
            sweepAngle = 220f,
            startAngle = -90f,
            useCenter = false,
            style = Stroke(16.dp.toPx(), cap = StrokeCap.Round)
        )
        drawText(textMeasurer = textMeasure, text = textToDraw,
            style = style,
            topLeft = Offset(
                x = center.x - textLayoutResult.size.width / 2,
                y = center.y - textLayoutResult.size.height / 2,
            )
        )
    }
}

@Composable
private fun TimerFooter(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.padding(bottom = 80.dp)) {
        ActionsIcons(icon = R.drawable.pause, title = stringResource(R.string.pause))
        SpacerWidth(60.dp)
        ActionsIcons(icon = R.drawable.stop, title = stringResource(R.string.stop))
    }
}

@Composable
private fun ActionsIcons(
    modifier: Modifier = Modifier,
    @DrawableRes icon:Int,
    title:String
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        AppIconButton(
            icon = icon, modifier = Modifier
                .drawBehind {
                    drawCircle(
                        color = TimerLightDarkBlue,
                    )
                }
                .padding(8.dp)
        ) {}
        SpacerHeight()
        Text(
            text = title, style = TextStyle(
                fontSize = 14.sp,
                fontFamily = montserratFont,
                color = TimerGrayColor
            )
        )
    }
}