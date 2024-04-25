package com.nameisjayant.composeprojects.features.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nameisjayant.composeprojects.R
import com.nameisjayant.composeprojects.components.AppIcon
import com.nameisjayant.composeprojects.components.AppIconButton
import com.nameisjayant.composeprojects.components.SpacerHeight
import com.nameisjayant.composeprojects.components.SpacerWidth

@Composable
fun InstagramProfileScreen() {

    InstagramProfileRow(
        topBarSection = {
            TopBar(
                name = stringResource(R.string.hemantjain_17),
            )
        },
        profileSection = {
            ProfileSection()
        },
        buttonSection = {
            ButtonSection(modifier = Modifier.fillMaxWidth())
        },
        highlightSection = {
            HighlightSection()
        }
    ) {
        PostSection()
    }
}

@Composable
private fun HighlightSection(
    modifier: Modifier = Modifier
) {
    HighlightSection(
        highlights = InstagramProfileData.getHighlightsData(),
        modifier = modifier
            .fillMaxWidth()
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PostSection(
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(initialPage = 0) {
        InstagramProfileData.getPostsData().size
    }
    var selectedTabIndex by remember {
        mutableIntStateOf(value = 0)
    }
    LaunchedEffect(key1 = pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }
    Column(
        modifier = modifier
    ) {
        PostTabView(
            imageWithTexts = InstagramProfileData.getPostsData(),
            selectedTabIndex = selectedTabIndex
        ) {
            selectedTabIndex = it
        }
        Spacer(modifier = Modifier.height(10.dp))
        HorizontalPager(state = pagerState) {
            when (selectedTabIndex) {
                0 -> PostSection(
                    posts = listOf(
                        painterResource(id = R.drawable.ic_dubai),
                        painterResource(id = R.drawable.ic_italy),
                        painterResource(id = R.drawable.girl_3),
                        painterResource(id = R.drawable.ic_japan),
                        painterResource(id = R.drawable.monkey),
                        painterResource(id = R.drawable.girl_1),
                        painterResource(id = R.drawable.man),
                        painterResource(id = R.drawable.ic_usa),
                        painterResource(id = R.drawable.girl),
                        painterResource(id = R.drawable.girl_image),
                        painterResource(id = R.drawable.page_2),
                    )
                )

                1 -> PostSection(
                    posts = listOf(
                        painterResource(id = R.drawable.page_2),
                        painterResource(id = R.drawable.girl_1),
                        painterResource(id = R.drawable.monkey),
                        painterResource(id = R.drawable.girl_2),
                    )
                )

                2 -> PostSection(
                    posts = listOf(
                        painterResource(id = R.drawable.ic_italy),
                    )
                )

                3 -> PostSection(
                    posts = listOf(
                        painterResource(id = R.drawable.girl),
                        painterResource(id = R.drawable.girl_1),
                        painterResource(id = R.drawable.girl_2),
                        painterResource(id = R.drawable.girl_3),
                        painterResource(id = R.drawable.girl_image),
                        painterResource(id = R.drawable.man),
                        painterResource(id = R.drawable.ic_japan),
                    )
                )
            }
        }
    }
}

@Composable
private fun InstagramProfileRow(
    modifier: Modifier = Modifier,
    topBarSection: (@Composable () -> Unit)? = null,
    profileSection: (@Composable () -> Unit)? = null,
    buttonSection: (@Composable () -> Unit)? = null,
    highlightSection: (@Composable () -> Unit)? = null,
    postsSection: (@Composable () -> Unit)? = null,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        topBarSection?.invoke()
        profileSection?.invoke()
        buttonSection?.invoke()
        highlightSection?.invoke()
        postsSection?.invoke()
    }
}

@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    name: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(start = 20.dp)
            .fillMaxWidth(0.5F)
    ) {
        AppIconButton(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack, tint = Color.Black
        ) {}
        Text(
            text = name,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}

@Composable
private fun ProfileSection(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            RoundImage(
                image = painterResource(id = R.drawable.ic_profile_image),
                modifier = Modifier
                    .size(100.dp)
                    .weight(0.3f)
            )
            SpacerWidth(16.dp)
            StatSection(modifier = Modifier.weight(0.7f))
        }
        Spacer(modifier = Modifier.height(10.dp))
        ProfileDescriptionSection(
            displayName = stringResource(R.string.hemant_jain),
            description = stringResource(R.string.software_developer_android_at_55tech_jetpack_compose_android_kotlin),
            url = stringResource(R.string.https_www_linkedin_com_in_hemantjain99),
            followedBy = listOf("viratkohli", "mrbeast"),
            otherCount = 18
        )
    }
}

@Composable
private fun RoundImage(
    image: Painter,
    modifier: Modifier = Modifier
) {
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = CircleShape
            )
            .padding(3.dp)
            .clip(CircleShape)
    )
}

@Composable
private fun StatSection(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        InstagramProfileData.getStats().forEach {
            ProfileStatSection(number = it.stat, text = it.title)
        }
    }
}

@Composable
private fun ProfileStatSection(
    number: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = number,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        SpacerHeight(4.dp)
        Text(text = text)
    }
}

@Composable
private fun ProfileDescriptionSection(
    displayName: String,
    description: String,
    url: String,
    followedBy: List<String>,
    otherCount: Int
) {
    val letterSpacing = 0.5.sp
    val lineHeight = 20.sp
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = displayName,
            fontWeight = FontWeight.Bold,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        Text(
            text = description,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        Text(
            text = url,
            color = Color(0xFF3D3D91),
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        if (followedBy.isNotEmpty()) {
            Text(
                text = buildAnnotatedString {
                    val boldStyle = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    append(stringResource(R.string.followed_by))
                    followedBy.forEachIndexed { index, name ->
                        pushStyle(boldStyle)
                        append(name)
                        pop()
                        if (index < followedBy.size - 1) {
                            append(", ")
                        }
                    }
                    if (otherCount > 2) {
                        append(stringResource(R.string.and_))
                        pushStyle(boldStyle)
                        append(stringResource(R.string.others, otherCount))
                    }
                },
                letterSpacing = letterSpacing,
                lineHeight = lineHeight
            )
        }
    }
}

@Composable
private fun ButtonSection(
    modifier: Modifier = Modifier
) {
    val minWidth = 110.dp
    val height = 30.dp
    val list = listOf("Following", "Message", "Email")
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.padding(vertical = 15.dp)
    ) {
        list.forEachIndexed { index, s ->
            ActionButton(
                text = s,
                icon = if (index == 0) Icons.Default.KeyboardArrowDown else null,
                modifier = Modifier
                    .defaultMinSize(minWidth = minWidth)
                    .height(height)
            )
        }
        ActionButton(
            icon = Icons.Default.KeyboardArrowDown,
            modifier = Modifier.size(height)
        )
    }
}

@Composable
private fun ActionButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    icon: ImageVector? = null
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(5.dp)
            )
    ) {
        text?.let {
            Text(
                text = it,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }
        icon?.let {
            AppIcon(
                imageVector = it, tint = Color.Black
            )
        }
    }
}

@Composable
private fun HighlightSection(
    modifier: Modifier = Modifier,
    highlights: List<ImageWithText>
) {
    LazyRow(modifier = modifier) {
        item {
            SpacerWidth(20.dp)
        }
        items(highlights.size) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(end = 15.dp)
            ) {
                RoundImage(
                    image = painterResource(id = highlights[it].image),
                    modifier = Modifier.size(70.dp)
                )
                Text(
                    text = highlights[it].text,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun PostTabView(
    modifier: Modifier = Modifier,
    imageWithTexts: List<ImageWithText>,
    selectedTabIndex: Int,
    onTabSelected: (selectedIndex: Int) -> Unit
) {
    val inactiveColor = Color(color = 0xFF777777)
    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.Transparent,
        contentColor = Color.Black,
        modifier = modifier.padding(top = 10.dp)
    ) {
        imageWithTexts.forEachIndexed { index, _ ->
            Tab(
                modifier = Modifier.height(60.dp),
                selected = selectedTabIndex == index,
                selectedContentColor = Color.Black,
                unselectedContentColor = inactiveColor,
                onClick = {
                    onTabSelected(index)
                }
            ) {
                Text(
                    text = imageWithTexts[index].text,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
private fun PostSection(
    posts: List<Painter>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 3)
    ) {
        items(posts.size) {
            Image(
                painter = posts[it],
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(
                        width = 1.dp,
                        color = Color.White
                    )
            )
        }
    }
}

data class ImageWithText(
    @DrawableRes val image: Int,
    val text: String
)

data class StatsModal(
    val title: String,
    val stat: String
)

private object InstagramProfileData {

    fun getStats(): List<StatsModal> {
        return listOf(
            StatsModal("Posts", "72"),
            StatsModal("Followers", "785"),
            StatsModal("Following", "242")
        )
    }

    fun getHighlightsData() = listOf(
        ImageWithText(
            image = R.drawable.ic_france,
            text = "France"
        ),
        ImageWithText(
            image = R.drawable.ic_dubai,
            text = "Dubai"
        ),
        ImageWithText(
            image = R.drawable.ic_italy,
            text = "Italy"
        ),
        ImageWithText(
            image = R.drawable.ic_spain,
            text = "Spain"
        ),
        ImageWithText(
            image = R.drawable.ic_usa,
            text = "USA"
        ),
        ImageWithText(
            image = R.drawable.ic_japan,
            text = "Japan"
        ),
    )

    fun getPostsData() = listOf(
        ImageWithText(
            image = R.drawable.girl_1,
            text = "Posts"
        ),
        ImageWithText(
            image = R.drawable.girl_1,
            text = "Reels"
        ),
        ImageWithText(
            image = R.drawable.igtv,
            text = "IGTV"
        ),
        ImageWithText(
            image = R.drawable.girl_1,
            text = "Tags"
        ),
    )


}