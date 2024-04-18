package com.nameisjayant.composeprojects.features.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nameisjayant.composeprojects.R
import com.nameisjayant.composeprojects.components.AppIcon
import com.nameisjayant.composeprojects.components.SpacerHeight
import com.nameisjayant.composeprojects.components.SpacerWidth
import com.nameisjayant.composeprojects.ui.theme.HealthBlack
import com.nameisjayant.composeprojects.ui.theme.HealthBlue
import com.nameisjayant.composeprojects.ui.theme.HealthDarkGray
import com.nameisjayant.composeprojects.ui.theme.HealthGray
import com.nameisjayant.composeprojects.ui.theme.HealthLightBlue1
import com.nameisjayant.composeprojects.ui.theme.HealthLightBlue2
import com.nameisjayant.composeprojects.ui.theme.HealthLightGray
import com.nameisjayant.composeprojects.ui.theme.HealthLightRed
import com.nameisjayant.composeprojects.ui.theme.HealthLightYellow
import com.nameisjayant.composeprojects.ui.theme.HealthRed
import com.nameisjayant.composeprojects.ui.theme.HealthWhite
import com.nameisjayant.composeprojects.ui.theme.HealthWhite1
import com.nameisjayant.composeprojects.ui.theme.HealthYellow
import com.nameisjayant.composeprojects.ui.theme.interFont
import com.nameisjayant.composeprojects.ui.theme.montserratFont
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun HealthUIScreen() {
    HealthAppRow(
        header = { HealthHeaderSection() },
        center = { HealthCenterSection() }
    ) {
        FooterSection()
    }
}

@Composable
private fun HealthAppRow(
    modifier: Modifier = Modifier,
    header: (@Composable () -> Unit)? = null,
    center: (@Composable () -> Unit)? = null,
    footer: (@Composable () -> Unit)? = null,
) {

    LazyColumn(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        item {
            header?.invoke()
            center?.invoke()
            footer?.invoke()
        }
    }

}

@Composable
private fun HealthHeaderSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(
                start = 24.dp,
                top = 24.dp
            )
        ) {
            Text(
                text = stringResource(R.string.statistics), style = TextStyle(
                    color = HealthBlack,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = interFont
                )
            )
            SpacerHeight(16.dp)
            Text(
                text = stringResource(R.string.june_2022), style = TextStyle(
                    color = HealthBlack,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = montserratFont
                )
            )
        }
        SpacerHeight(16.dp)
        CalendarSection()
    }
}

@Composable
private fun CalendarSection(
    modifier: Modifier = Modifier
) {
    var selected by remember { mutableIntStateOf(HealthData.getCalendarData().first().id) }

    Row(
        modifier = modifier
            .background(HealthWhite)
            .fillMaxWidth()
            .padding(vertical = 24.dp)

    ) {
        LazyRow {
            item {
                SpacerWidth(36.dp)
            }
            items(HealthData.getCalendarData(), key = { it.id }) { data ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(end = 24.dp)
                ) {
                    Text(
                        text = "${data.week}", style = TextStyle(
                            color = HealthBlue,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = montserratFont
                        )
                    )
                    SpacerHeight(16.dp)
                    ClickableText(
                        text = AnnotatedString("${data.date}"), style = TextStyle(
                            color = if (selected == data.id) HealthWhite1 else HealthGray,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = montserratFont
                        ),
                        modifier = Modifier
                            .drawBehind {
                                if (data.id == selected)
                                    drawRoundRect(
                                        color = HealthBlack,
                                        cornerRadius = CornerRadius(8.dp.toPx())
                                    )
                            }
                            .padding(10.dp),
                        onClick = {
                            selected = data.id
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun HealthCenterSection(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        SpacerHeight(40.dp)
        Text(
            text = stringResource(R.string.overview), style = TextStyle(
                color = HealthDarkGray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = interFont
            ),
            modifier = Modifier.padding(start = 24.dp)
        )
        SpacerHeight(24.dp)
        LazyRow {
            item {
                SpacerWidth(24.dp)
            }
            items(HealthData.getOverviewData(), key = { it.id }) {
                OverviewRow(data = it)
            }
        }
    }
}

@Composable
private fun OverviewRow(
    modifier: Modifier = Modifier,
    data: OverviewModal
) {
    Column(
        modifier = modifier
            .padding(end = 20.dp)
            .background(
                HealthWhite, RoundedCornerShape(24.dp)
            )
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        AppIcon(icon = data.icon)
        SpacerHeight(16.dp)
        Text(
            text = data.value, style = TextStyle(
                color = HealthBlack,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = interFont
            )
        )
        SpacerHeight()
        Text(
            text = data.title, style = TextStyle(
                color = HealthLightGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = montserratFont
            )
        )
    }
}

@Composable
private fun FooterSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(24.dp)
    ) {
        Text(
            text = stringResource(R.string.daily_progress), style = TextStyle(
                color = HealthDarkGray,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = interFont
            )
        )
        SpacerHeight(16.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DailyProgressChart(
                modifier = Modifier
                    .padding(start = 30.dp)
                    .weight(0.5f)
            )
            Column(
                modifier = Modifier
                    .padding(start = 60.dp)
                    .weight(0.5f)
            ) {
                HealthData.getDailyProgressData().forEach {
                    val annotatedString = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = HealthBlack,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = montserratFont
                            )
                        ) {
                            append(it.progress)
                        }
                        append("/")
                        withStyle(
                            style = SpanStyle(
                                color = HealthLightGray,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                fontFamily = montserratFont
                            )
                        ) {
                            append(it.total)
                        }
                    }
                    Row(
                        modifier = Modifier.padding(bottom = 20.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .background(it.progressColor, CircleShape)
                                .size(12.dp)
                        )
                        SpacerWidth()
                        Column {
                            Text(
                                text = it.title, style = TextStyle(
                                    color = HealthBlack,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = montserratFont
                                )
                            )
                            SpacerHeight(5.dp)
                            Text(
                                text = annotatedString
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DailyProgressChart(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(250.dp)) {
        val centerX = size.width / 2
        val centerY = size.height / 2
        HealthData.getDailyProgressData().forEach { data ->

            drawCircle(
                color = data.color,
                style = Stroke(
                    width = 15.dp.toPx()
                ),
                radius = 100f * data.id,
                center = Offset(centerX, centerY)
            )
            drawArc(
                color = data.progressColor,
                sweepAngle = 180f,
                startAngle = 0f,
                useCenter = false,
                style = Stroke(16.dp.toPx(), cap = StrokeCap.Round),
                topLeft = Offset(centerX - data.id * 100, centerY - data.id * 100),
                size = Size((100f * data.id) * 2, (100f * data.id) * 2)
            )
            drawCircle(
                color = Color.White,
                radius = 5.dp.toPx(),
                center = Offset(
                    centerX + 100 * data.id * cos(0f),
                    centerY + 100 * data.id * sin(0f)
                )
            )
        }
    }
}

data class CalendarModal(
    val id: Int,
    val week: Char,
    val date: Int
)

data class OverviewModal(
    val id: Int,
    val value: String,
    val title: String,
    @DrawableRes val icon: Int
)

data class DailyProgressModal(
    val id: Int,
    val title: String,
    val total: String,
    val progress: String,
    val color: Color,
    val progressColor: Color
)

object HealthData {
    fun getCalendarData(): List<CalendarModal> {
        return listOf(
            CalendarModal(1, 'M', 13),
            CalendarModal(2, 'T', 14),
            CalendarModal(3, 'W', 15),
            CalendarModal(4, 'T', 16),
            CalendarModal(5, 'F', 17),
            CalendarModal(6, 'S', 18),
            CalendarModal(7, 'S', 19),
        )
    }

    fun getOverviewData(): List<OverviewModal> {
        return listOf(
            OverviewModal(1, "3.950", "Cal Burnt", R.drawable.cal_burnt), OverviewModal(
                2, "3h 14min", "Total Time", R.drawable.time
            ),
            OverviewModal(3, "15", "Exercises", R.drawable.excercise)
        )
    }

    fun getDailyProgressData(): List<DailyProgressModal> {
        return listOf(
            DailyProgressModal(
                1, "Steps", "6000", "2015", HealthLightYellow, HealthYellow
            ),

            DailyProgressModal(
                2, "Calories", "2000", "1050", HealthLightRed, HealthRed
            ),
            DailyProgressModal(
                3, "Sleep", "8h", "6h 5min", HealthLightBlue2,
                HealthLightBlue1
            ),
        )
    }
}