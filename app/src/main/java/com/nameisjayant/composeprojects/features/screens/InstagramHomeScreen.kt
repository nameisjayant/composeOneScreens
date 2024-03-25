package com.nameisjayant.composeprojects.features.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.nameisjayant.composeprojects.ui.theme.InstaBlue
import com.nameisjayant.composeprojects.ui.theme.InstaDark
import com.nameisjayant.composeprojects.ui.theme.InstaGray
import com.nameisjayant.composeprojects.ui.theme.InstaWhite
import com.nameisjayant.composeprojects.ui.theme.InstagramBlack
import com.nameisjayant.composeprojects.ui.theme.interFont


@Composable
fun InstagramHomeScreen() {
    InstagramHomeRow(header = { InstagramHeaderRow() },
        stories = { instaStorySection() },
        posts = { instaPostSection() })
}

@Composable
private fun InstagramHomeRow(
    modifier: Modifier = Modifier,
    header: (@Composable () -> Unit)? = null,
    stories: (LazyListScope.() -> Unit)? = null,
    posts: (LazyListScope.() -> Unit)? = null
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        header?.invoke()
        LazyColumn(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize()
        ) {
            stories?.invoke(this)
            posts?.invoke(this)
        }
    }
}

private fun LazyListScope.instaPostSection() {
    items(5, key = { it }) {
        PostRow()
    }
}

@Composable
private fun PostProfileSection(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            AppIcon(icon = R.drawable.man, modifier = Modifier.size(32.dp))
            SpacerWidth()
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.nameisjayant), style = TextStyle(
                            color = InstaWhite,
                            fontSize = 13.sp,
                            fontFamily = interFont,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    SpacerWidth(5.dp)
                    AppIcon(icon = R.drawable.blue_tick, modifier = Modifier.size(10.dp))
                }
                SpacerHeight(2.dp)
                Text(
                    text = stringResource(R.string.new_delhi_india), style = TextStyle(
                        color = InstaWhite,
                        fontSize = 11.sp,
                        fontFamily = interFont,
                    )
                )
            }
        }
        AppIcon(icon = R.drawable.hori_dots)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PostRow(
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })
    Column(modifier = modifier.padding(top = 16.dp)) {
        PostProfileSection()
        SpacerHeight()
        HorizontalPager(state = pagerState) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.photo_1),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
                Box(
                    modifier = Modifier
                        .padding(20.dp)
                        .background(InstaDark.copy(alpha = 0.33f), CircleShape)
                        .padding(8.dp)
                        .align(Alignment.TopEnd)
                ) {
                    Text(
                        text = "${pagerState.currentPage + 1}/${pagerState.pageCount}",
                        style = TextStyle(
                            color = InstaWhite,
                            fontWeight = FontWeight.Medium,
                            fontFamily = interFont,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
        PostActions(pagerState = pagerState)
        LikesRow()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PostActions(
    modifier: Modifier = Modifier, pagerState: PagerState
) {
    Row(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            AppIcon(icon = R.drawable.like)
            SpacerWidth(16.dp)
            AppIcon(icon = R.drawable.comment)
            SpacerWidth(16.dp)
            AppIcon(icon = R.drawable.message)
        }
        Row {
            repeat(3) {
                Spacer(
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .background(
                            if (pagerState.currentPage == it) InstaBlue else InstaGray, CircleShape
                        )
                        .size(6.dp)
                )
            }
        }
        AppIcon(icon = R.drawable.save)
    }
}

@Composable
private fun LikesRow(
    modifier: Modifier = Modifier
) {
    val text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = InstaWhite,
                fontFamily = interFont,
                fontSize = 13.sp
            )
        ) {
            append(stringResource(R.string.liked_by))
        }
        append(" ")
        withStyle(
            style = SpanStyle(
                color = InstaWhite,
                fontFamily = interFont,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
        ) {
            append(stringResource(R.string.craig_love))
        }
        append(" ")
        withStyle(
            style = SpanStyle(
                color = InstaWhite,
                fontFamily = interFont,
                fontSize = 13.sp
            )
        ) {
            append(stringResource(R.string.and))
        }
        append(" ")
        withStyle(
            style = SpanStyle(
                color = InstaWhite,
                fontFamily = interFont,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
        ) {
            append(stringResource(R.string._44_545_others))
        }
    }
    val des = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = InstaWhite,
                fontFamily = interFont,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
        ) {
            append(stringResource(id = R.string.nameisjayant))
        }
        append(" ")
        withStyle(
            style = SpanStyle(
                color = InstaWhite,
                fontFamily = interFont,
                fontSize = 13.sp,
            )
        ) {
            append(stringResource(R.string.the_game_in_india_was_amazing_and_i_want_to_share_some_photos))
        }
    }
    Column(modifier = modifier.padding(horizontal = 10.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AppIcon(icon = R.drawable.girl_1, modifier = Modifier.size(17.dp))
            SpacerWidth(5.dp)
            Text(text = text)
        }
        SpacerHeight(5.dp)
        Text(text = des)
    }
}

@Composable
private fun InstagramHeaderRow(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(InstagramBlack)
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppIcon(icon = R.drawable.camera_icon)
        AppIcon(icon = R.drawable.insta_logo, modifier = Modifier.width(105.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppIcon(icon = R.drawable.igtv)
            SpacerWidth(16.dp)
            AppIcon(icon = R.drawable.message)
        }
    }
}


private fun LazyListScope.instaStorySection() {
    item {
        SpacerHeight()
        LazyRow {
            items(instaModalList, key = { it.id }) {
                InstaStoryRow(data = it)
            }
        }
    }
}

@Composable
private fun InstaStoryRow(
    modifier: Modifier = Modifier, data: InstaModal
) {
    Column(
        modifier = modifier.padding(start = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.height(80.dp)) {
            Box(
                modifier = Modifier
                    .border(
                        3.dp, data.color, CircleShape
                    )
                    .padding(8.dp)
            ) {
                AppIcon(icon = data.icon, modifier = Modifier.size(60.dp))
                if (data.isLive)
                    LiveButton(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .offset(x = 0.dp, y = 11.dp)
                    )
            }
        }
        Text(
            text = data.name, style = TextStyle(
                color = InstaWhite, fontSize = 12.sp, fontFamily = interFont
            )
        )
    }
}

@Composable
private fun LiveButton(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(3.dp, Color.Black, RoundedCornerShape(3.dp))
            .background(
                brush = Brush.linearGradient(
                    listOf(Color(0XFFC90083), Color(0XFFD32463), Color(0XFFE10038))
                ), RoundedCornerShape(3.dp)
            )
            .padding(vertical = 5.dp, horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.live), style = TextStyle(
                color = InstaWhite,
                fontSize = 8.sp,
                fontFamily = interFont,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}


private data class InstaModal(
    val id: Int,
    val name: String,
    val color: Brush,
    @DrawableRes val icon: Int,
    val isLive: Boolean = false
)

private val instaModalList = listOf(
    InstaModal(
        1, "Your Story", Brush.linearGradient(
            listOf(
                Color(0XFFFBAA47), Color(0XFFD91A46), Color(0XFFA60F93)
            )
        ), R.drawable.man
    ), InstaModal(
        2, "Karanne", Brush.linearGradient(
            listOf(
                Color(0XFFC90083), Color(0XFFD32463), Color(0XFFE10038)
            )
        ), R.drawable.girl_1,
        isLive = true
    ), InstaModal(
        3, "Zackjohn", Brush.linearGradient(
            listOf(
                Color(0XFFFBAA47), Color(0XFFD91A46), Color(0XFFA60F93)
            )
        ), R.drawable.girl_2
    ), InstaModal(
        4, "koren", Brush.linearGradient(
            listOf(
                Color(0XFFC90083), Color(0XFFD32463), Color(0XFFE10038)
            )
        ), R.drawable.girl_3
    ), InstaModal(
        5, "Neneo", Brush.linearGradient(
            listOf(
                Color(0XFFFBAA47), Color(0XFFD91A46), Color(0XFFA60F93)
            )
        ), R.drawable.girl
    ),
    InstaModal(
        6, "nameen", Brush.linearGradient(
            listOf(
                Color(0XFFFBAA47), Color(0XFFD91A46), Color(0XFFA60F93)
            )
        ), R.drawable.man
    ),
    InstaModal(
        7, "Zackjohn", Brush.linearGradient(
            listOf(
                Color(0XFFFBAA47), Color(0XFFD91A46), Color(0XFFA60F93)
            )
        ), R.drawable.girl_2
    )
)
