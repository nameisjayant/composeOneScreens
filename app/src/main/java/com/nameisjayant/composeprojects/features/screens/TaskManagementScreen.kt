package com.nameisjayant.composeprojects.features.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nameisjayant.composeprojects.R
import com.nameisjayant.composeprojects.components.AppIcon
import com.nameisjayant.composeprojects.components.AppIconButton
import com.nameisjayant.composeprojects.components.SpacerHeight
import com.nameisjayant.composeprojects.components.SpacerWidth
import com.nameisjayant.composeprojects.ui.theme.BackgroundColor
import com.nameisjayant.composeprojects.ui.theme.DarkGrayColor
import com.nameisjayant.composeprojects.ui.theme.GrayColor
import com.nameisjayant.composeprojects.ui.theme.LightGrayColor
import com.nameisjayant.composeprojects.ui.theme.LightPurple
import com.nameisjayant.composeprojects.ui.theme.LightRedColor
import com.nameisjayant.composeprojects.ui.theme.RedColor
import com.nameisjayant.composeprojects.ui.theme.TextColor
import com.nameisjayant.composeprojects.ui.theme.VoiletColor
import com.nameisjayant.composeprojects.ui.theme.YellowColor
import com.nameisjayant.composeprojects.ui.theme.interFont


@Composable
fun TaskManagementScreen() {
    TaskManagementRow(
        header = {
            TaskManagementHeader()
        },
        weeklyTasks = { weeklyTaskRow() },
        todayTasks = { todayTaskRow() }
    )
}

@Composable
private fun TaskManagementRow(
    modifier: Modifier = Modifier,
    header: (@Composable () -> Unit)? = null,
    weeklyTasks: (LazyListScope.() -> Unit)? = null,
    todayTasks: (LazyListScope.() -> Unit)? = null,
) {
    val state = rememberLazyListState()
    LazyColumn(
        state = state,
        modifier = modifier
            .background(BackgroundColor)
            .fillMaxSize(),
        contentPadding = PaddingValues(20.dp)
    ) {
        item {
            header?.invoke()

        }
        weeklyTasks?.invoke(this)
        todayTasks?.invoke(this)
    }
}

fun LazyListScope.weeklyTaskRow(
) {
    item {
        TaskHeaderRow(
            title = stringResource(id = R.string.my_weekly_task),
            des = stringResource(id = R.string._10_task_pending),
            modifier = Modifier.padding(vertical = 30.dp)
        )
    }
    item {
        LazyRow {
            items(5) {
                WeeklyRow()
            }
        }
    }
}

fun LazyListScope.todayTaskRow() {
    item {
        TaskHeaderRow(
            title = stringResource(id = R.string.today_s_task),
            des = stringResource(id = R.string._10_task_pending),
            modifier = Modifier.padding(vertical = 30.dp)
        )
    }
    items(5){
        TodayRow()
    }

}

@Composable
fun WeeklyRow(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(end = 10.dp)
            .background(Color.White, RoundedCornerShape(20.dp))
            .width(230.dp)
            .padding(16.dp)
    ) {
        Row(modifier = modifier.fillMaxWidth()) {
            TasksTagsRow(
                title = stringResource(R.string.ui_ux_design),
                color = LightPurple,
                textColor = GrayColor,
            )
            SpacerWidth(16.dp)
            TasksTagsRow(
                title = stringResource(R.string.high),
                color = LightRedColor,
                textColor = RedColor,
            )
        }
        SpacerHeight(24.dp)
        Text(
            text = stringResource(R.string.create_a_personal_portfolio),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold,
                color = TextColor,
                fontFamily = interFont,
            )
        )
        SpacerHeight(16.dp)
        AssignIdsRow()
        SpacerHeight(16.dp)
        Row {
            AppIcon(icon = R.drawable.calendar)
            SpacerWidth()
            Text(
                text = stringResource(R.string.mon_15_feb_2024),
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Light,
                    color = GrayColor,
                    fontFamily = interFont,
                ),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun AssignIdsRow(
    modifier: Modifier = Modifier,
    size:Dp = 36.dp,
    offset:Int = 15

) {
    Row (modifier = modifier, verticalAlignment = Alignment.CenterVertically){
        repeat(3) {
            AppIcon(
                icon = R.drawable.person, modifier = Modifier
                    .size(size)
                    .offset(x = -(it * offset).dp, y = 0.dp)
            )
        }
        Text(
            text = stringResource(R.string._3),
            style = MaterialTheme.typography.labelMedium.copy(
                color = Color.White,
                fontFamily = interFont,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier
                .offset(
                    x = -(2.5 * offset).dp, y = 0.dp
                )
                .drawBehind {
                    drawCircle(
                        color = YellowColor,
                        radius = 42f,
                    )
                }
        )
    }
}


@Composable
fun TodayRow(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .background(Color.White, RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(R.string.design_3_app_screens),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = TextColor,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = interFont,
                        textDecoration = TextDecoration.LineThrough
                    )
                )
                Text(
                    text = stringResource(R.string.learning_platform_app),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Light,
                        color = GrayColor,
                        fontFamily = interFont,
                    ),
                )
            }
            AppIcon(
                imageVector = Icons.Default.Check,
                tint = Color.White,
                modifier = Modifier
                    .size(22.dp)
                    .drawBehind {
                        drawCircle(
                            color = VoiletColor,
                            radius = 40f
                        )
                    })
        }
        SpacerHeight(16.dp)
        HorizontalDivider(color = DarkGrayColor, thickness = 1.dp)
        SpacerHeight(16.dp)
        Row(verticalAlignment = Alignment.CenterVertically) {
           Row(modifier = Modifier.weight(1f)) {
               AppIcon(icon = R.drawable.calendar)
               SpacerWidth(24.dp)
               Text(
                   text = stringResource(R.string.mon_15_feb_2024),
                   style = MaterialTheme.typography.labelMedium.copy(
                       fontWeight = FontWeight.Light,
                       color = GrayColor,
                       fontFamily = interFont,
                   ),
                   modifier = Modifier.align(Alignment.CenterVertically)
               )
           }
            AssignIdsRow(size = 28.dp, offset = 10)
        }
    }
}

@Composable
fun TasksTagsRow(
    modifier: Modifier = Modifier,
    title: String,
    color: Color,
    textColor: Color
) {
    Text(text = title, style = MaterialTheme.typography.labelMedium.copy(
        color = textColor,
        fontFamily = interFont,
        fontWeight = FontWeight.Medium
    ), modifier = modifier
        .drawBehind {
            drawRoundRect(
                color = color,
                cornerRadius = CornerRadius(50f)
            )
        }
        .padding(horizontal = 16.dp, vertical = 5.dp))
}

@Composable
fun TaskHeaderRow(
    modifier: Modifier = Modifier,
    title: String,
    des: String
) {
    Row(modifier = modifier) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = TextColor,
                    fontFamily = interFont,
                )
            )
            Text(
                text = des,
                style = MaterialTheme.typography.labelMedium.copy(
                    color = GrayColor,
                    fontWeight = FontWeight.Light,
                    fontFamily = interFont,
                )
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            AppIconButton(icon = R.drawable.filter) {

            }
            SpacerWidth()
            VerticalDivider(
                thickness = 1.dp, modifier = Modifier.height(
                    37.dp
                ), color = LightGrayColor
            )
            SpacerWidth()
            AppIconButton(icon = R.drawable.plus) {

            }
        }

    }
}

@Composable
private fun TaskManagementHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(top = 10.dp)
    ) {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            AppIcon(icon = R.drawable.person, modifier = Modifier.size(45.dp))
            SpacerWidth(16.dp)
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = stringResource(R.string.good_evening),
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = GrayColor,
                        fontWeight = FontWeight.Light,
                        fontFamily = interFont,
                    )
                )
                Text(
                    text = stringResource(R.string.jayant_kumar),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = TextColor,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = interFont
                    )
                )
            }
        }
        Row(
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            AppIconButton(icon = R.drawable.search, modifier = Modifier.size(24.dp)) {

            }
            SpacerWidth(24.dp)
            Box {
                AppIconButton(icon = R.drawable.notification, modifier = Modifier.size(24.dp)) {

                }
                Spacer(
                    modifier = Modifier
                        .padding(2.5.dp)
                        .background(YellowColor, CircleShape)
                        .size(7.dp)
                        .align(Alignment.TopEnd)
                )
            }
        }
    }
}
