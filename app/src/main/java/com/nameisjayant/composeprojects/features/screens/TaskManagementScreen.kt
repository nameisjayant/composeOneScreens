package com.nameisjayant.composeprojects.features.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nameisjayant.composeprojects.R
import com.nameisjayant.composeprojects.components.AppIcon
import com.nameisjayant.composeprojects.components.AppIconButton
import com.nameisjayant.composeprojects.components.SpacerWidth
import com.nameisjayant.composeprojects.ui.theme.GrayColor
import com.nameisjayant.composeprojects.ui.theme.TextColor
import com.nameisjayant.composeprojects.ui.theme.YellowColor


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
        modifier = modifier,
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
        Row(modifier = Modifier.padding(vertical = 40.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(R.string.my_weekly_task),
                    style  = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = TextColor
                    )
                )
                Text(
                    text = stringResource(R.string._10_task_pending),
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = GrayColor,
                        fontWeight = FontWeight.Light
                    )
                )
            }
        }
    }
}

fun LazyListScope.todayTaskRow() {
    item {
        Row(modifier = Modifier.padding(vertical = 40.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Today's Task",
                    style  = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = TextColor
                    )
                )
                Text(
                    text = stringResource(R.string._10_task_pending),
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = GrayColor,
                        fontWeight = FontWeight.Light
                    )
                )
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
                        fontWeight = FontWeight.Light
                    )
                )
                Text(
                    text = stringResource(R.string.jayant_kumar),
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = TextColor,
                        fontWeight = FontWeight.SemiBold
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
