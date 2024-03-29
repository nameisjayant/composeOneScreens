package com.nameisjayant.composeprojects.features.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nameisjayant.composeprojects.R
import com.nameisjayant.composeprojects.components.AppIcon
import com.nameisjayant.composeprojects.components.AppIconButton
import com.nameisjayant.composeprojects.components.SpacerHeight
import com.nameisjayant.composeprojects.components.SpacerWidth
import com.nameisjayant.composeprojects.ui.theme.TwitterBg
import com.nameisjayant.composeprojects.ui.theme.TwitterBlack
import com.nameisjayant.composeprojects.ui.theme.TwitterBlue
import com.nameisjayant.composeprojects.ui.theme.TwitterDarkGray
import com.nameisjayant.composeprojects.ui.theme.TwitterGray
import com.nameisjayant.composeprojects.ui.theme.TwitterText
import com.nameisjayant.composeprojects.ui.theme.montserratFont


@Composable
fun TwitterProfileScreen() {
    TwitterProfileRow(header = { TwitterHeader() }, profile = { TwitterProfileSection() }) {

    }
}

@Composable
private fun TwitterProfileRow(
    modifier: Modifier = Modifier,
    header: (@Composable () -> Unit)? = null,
    profile: (@Composable () -> Unit)? = null,
    tabs: (@Composable () -> Unit)? = null
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        header?.invoke()
        profile?.invoke()
        tabs?.invoke()
    }
}

@Composable
private fun TwitterHeader(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.End
    ) {
        Box(
            modifier = Modifier
                .background(TwitterBg)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(top = 15.dp, start = 16.dp, bottom = 55.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppIcon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    tint = Color.White,
                    modifier = Modifier.drawBehind {
                        drawCircle(
                            color = TwitterBlack
                        )
                    }.padding(5.dp))
                SpacerWidth(30.dp)
                Text(
                    text = stringResource(R.string.digital_goodies_team), style = TextStyle(
                        color = TwitterDarkGray,
                        fontFamily = montserratFont,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            AppIcon(icon = R.drawable.profile_pic,
                modifier = Modifier
                    .size(68.dp)
                    .offset(x = 16.dp, y = 40.dp)
                    .drawBehind {
                        drawCircle(
                            color = Color.White,
                        )
                    }
                    .align(Alignment.BottomStart))
        }
        Text(text = stringResource(R.string.edit_profile), style = TextStyle(
            fontSize = 14.sp,
            fontFamily = montserratFont,
            fontWeight = FontWeight.Bold,
            color = TwitterBlue
        ), modifier = Modifier
            .padding(top = 16.dp, end = 20.dp)
            .drawBehind {
                drawRoundRect(
                    color = TwitterBlue, cornerRadius = CornerRadius(50.dp.toPx()),
                    style = Stroke(
                        width = 2f
                    )
                )
            }
            .padding(vertical = 5.dp, horizontal = 10.dp))
    }
}

@Composable
private fun TwitterProfileSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.pixsellz), style = TextStyle(
                color = TwitterText,
                fontFamily = montserratFont,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold
            )
        )
        Text(
            text = stringResource(R.string.pixsellz__), style = TextStyle(
                color = TwitterGray,
                fontFamily = montserratFont,
                fontSize = 16.sp,
            )
        )
        SpacerHeight(16.dp)
        Text(
            text = stringResource(R.string.digital_goodies_team_web_mobile_ui_ux_development_graphics_illustrations),
            style = TextStyle(
                color = TwitterText,
                fontFamily = montserratFont,
                fontSize = 16.sp,
            )
        )
        SpacerHeight()
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppIcon(icon = R.drawable.link_icon, modifier = Modifier.size(14.dp))
            SpacerWidth(5.dp)
            Text(
                text = stringResource(R.string.pixsellz_io),
                style = TextStyle(
                    color = TwitterBlue,
                    fontFamily = montserratFont,
                    fontSize = 14.sp,
                )
            )
            SpacerWidth()
            AppIcon(icon = R.drawable.calendar_icon, modifier = Modifier.size(14.dp))
            SpacerWidth(5.dp)
            Text(
                text = stringResource(R.string.joined_september_2018),
                style = TextStyle(
                    color = TwitterGray,
                    fontFamily = montserratFont,
                    fontSize = 14.sp,
                )
            )
        }
        SpacerHeight()
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string._217),
                style = TextStyle(
                    color = TwitterText,
                    fontFamily = montserratFont,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            SpacerWidth(5.dp)
            Text(
                text = stringResource(R.string.following),
                style = TextStyle(
                    color = TwitterGray,
                    fontFamily = montserratFont,
                    fontSize = 14.sp,
                )
            )
            SpacerWidth(10.dp)
            Text(
                text = stringResource(R.string._118),
                style = TextStyle(
                    color = TwitterText,
                    fontFamily = montserratFont,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            SpacerWidth(5.dp)
            Text(
                text = stringResource(R.string.followers),
                style = TextStyle(
                    color = TwitterGray,
                    fontFamily = montserratFont,
                    fontSize = 14.sp,
                )
            )
        }
    }
}