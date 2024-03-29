package com.nameisjayant.composeprojects.features.screens

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import com.nameisjayant.composeprojects.ui.theme.TextColor
import com.nameisjayant.composeprojects.ui.theme.TwitterBg
import com.nameisjayant.composeprojects.ui.theme.TwitterBlack
import com.nameisjayant.composeprojects.ui.theme.TwitterBlue
import com.nameisjayant.composeprojects.ui.theme.TwitterDarkGray
import com.nameisjayant.composeprojects.ui.theme.TwitterGray
import com.nameisjayant.composeprojects.ui.theme.TwitterText
import com.nameisjayant.composeprojects.ui.theme.montserratFont


@Composable
fun TwitterProfileScreen() {
    TwitterProfileRow(header = { TwitterHeader() }, profile = { TwitterProfileSection() }, tabs = {
        TabSection()
    })
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
                    modifier = Modifier
                        .drawBehind {
                            drawCircle(
                                color = TwitterBlack
                            )
                        }
                        .padding(5.dp))
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
                        width = 3f
                    )
                )
            }
            .padding(vertical = 8.dp, horizontal = 10.dp))
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TabSection(
    modifier: Modifier = Modifier
) {

    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Tweets", "replies", "Media", "Likes")
    val pagerState = rememberPagerState(initialPage = 0) {
        tabs.size
    }
    LaunchedEffect(key1 = pagerState.currentPage) {
        selectedTab = pagerState.currentPage
    }
    Column {
        TabRow(selectedTabIndex = selectedTab, modifier = modifier
            .padding(top = 16.dp)
            .fillMaxWidth(), indicator = {
            TabRowDefaults.PrimaryIndicator(
                color = TwitterBlue,
                modifier = Modifier.tabIndicatorOffset(it[selectedTab]),
                width = 100.dp
            )
        }) {
            tabs.forEachIndexed { index, s ->
                Tab(
                    selected = selectedTab == index,
                    selectedContentColor = TwitterBlue,
                    onClick = {
                        selectedTab = index
                    },
                    unselectedContentColor = TwitterGray,
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    Text(
                        text = s, style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = montserratFont
                        )
                    )
                }
            }
        }
        SpacerHeight()
        HorizontalPager(state = pagerState) {
            LazyColumn {
                items(5) {
                    TabsContent(isPinned = it == 0)
                }
            }
        }
    }
}

@Composable
private fun TabsContent(
    modifier: Modifier = Modifier,
    isPinned:Boolean
) {
    var isShow by rememberSaveable { mutableStateOf(true) }
    Column(
        modifier = modifier.padding(
            start = 16.dp,
            top = 10.dp,
            end = 30.dp,
            bottom = 10.dp
        )
    ) {
        if (isPinned)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 40.dp)
        ) {
            AppIcon(icon = R.drawable.pinned, modifier = Modifier.size(12.dp))
            SpacerWidth()
            Text(
                text = stringResource(R.string.pinned_tweet), style = TextStyle(
                    color = TwitterGray,
                    fontFamily = montserratFont,
                    fontSize = 14.sp
                )
            )
        }
        Row {
            AppIcon(icon = R.drawable.profile_pic, modifier = Modifier.size(55.dp))
            SpacerWidth()
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.pixsellz), style = TextStyle(
                                color = TextColor,
                                fontFamily = montserratFont,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                        SpacerWidth(5.dp)
                        Text(
                            text = stringResource(R.string.pixsellz_7_31_19), style = TextStyle(
                                color = TwitterGray,
                                fontFamily = montserratFont,
                                fontSize = 16.sp,
                            )
                        )
                    }
                    AppIconButton(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        modifier = Modifier.size(16.dp),
                        tint = TwitterGray
                    ){
                        isShow = !isShow
                    }
                }
              AnimatedVisibility(visible = isShow) {
                  Column {
                      Text(
                          text = stringResource(R.string.scheme_constructor_your_ultimate_prototyping_kit_for_all_ux_web_and_mobileepp_design),
                          style = TextStyle(
                              color = TwitterGray,
                              fontFamily = montserratFont,
                              fontSize = 16.sp,
                          )
                      )
                      Text(
                          text = stringResource(R.string.constructor_pixsellz_io),
                          style = TextStyle(
                              color = TwitterBlue,
                              fontFamily = montserratFont,
                              fontSize = 16.sp,
                          )
                      )
                      SpacerHeight(30.dp)
                      Text(
                          text = stringResource(R.string.prototyping_wireframe_uiux_ux),
                          style = TextStyle(
                              color = TwitterBlue,
                              fontFamily = montserratFont,
                              fontSize = 16.sp,
                          )
                      )
                      SpacerHeight()
                      Box(modifier = Modifier.fillMaxWidth()) {
                          Image(
                              painter = painterResource(id = R.drawable.page_2),
                              contentDescription = null,
                              contentScale = ContentScale.FillWidth,
                          )
                          Text(
                              text = stringResource(R.string._0_11),
                              style = TextStyle(
                                  color = Color.White,
                                  fontFamily = montserratFont,
                                  fontSize = 12.sp,
                                  fontWeight = FontWeight.Medium
                              ),
                              modifier = Modifier
                                  .padding(16.dp)
                                  .align(Alignment.BottomStart)
                          )
                          AppIcon(icon = R.drawable.play, modifier = Modifier
                              .size(30.dp)
                              .align(Alignment.Center)
                              .drawBehind {
                                  drawCircle(
                                      color = Color.White
                                  )
                                  drawCircle(
                                      color = TwitterBlue,
                                      radius = size.minDimension / 2.5f
                                  )
                              }
                              .padding(10.dp))
                      }
                      SpacerHeight(5.dp)
                      Text(
                          text = stringResource(R.string._109_views),
                          style = TextStyle(
                              color = TwitterGray,
                              fontFamily = montserratFont,
                              fontSize = 14.sp,
                          )
                      )
                      SpacerHeight(16.dp)
                      Row(
                          horizontalArrangement = Arrangement.spacedBy(40.dp)
                      ) {
                          TweetActions(
                              icon = R.drawable.twitter_comment,
                              title = stringResource(R.string._2)
                          )
                          TweetActions(
                              icon = R.drawable.twitter_repost,
                              title = stringResource(R.string._2)
                          )
                          TweetActions(
                              icon = R.drawable.twitter_like,
                              title = stringResource(R.string._15)
                          )
                          TweetActions(
                              icon = R.drawable.twitter_share,
                              title = null
                          )

                      }
                  }
              }
            }
        }
    }
}

@Composable
private fun TweetActions(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    title: String?
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        AppIcon(icon = icon, modifier = Modifier.size(15.dp))
        SpacerWidth(4.dp)
        title?.let {
            Text(
                text = it,
                style = TextStyle(
                    color = TwitterGray,
                    fontFamily = montserratFont,
                    fontSize = 12.sp,
                )
            )
        }
    }
}