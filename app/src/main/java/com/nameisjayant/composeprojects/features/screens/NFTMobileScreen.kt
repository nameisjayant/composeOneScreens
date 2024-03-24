package com.nameisjayant.composeprojects.features.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nameisjayant.composeprojects.R
import com.nameisjayant.composeprojects.components.AppIcon
import com.nameisjayant.composeprojects.components.SpacerHeight
import com.nameisjayant.composeprojects.components.SpacerWidth
import com.nameisjayant.composeprojects.ui.theme.NFTBackgroundColor
import com.nameisjayant.composeprojects.ui.theme.NFTBlueColor
import com.nameisjayant.composeprojects.ui.theme.NFTBorderColor
import com.nameisjayant.composeprojects.ui.theme.NFTDarkBlueColor
import com.nameisjayant.composeprojects.ui.theme.NFTGrayColor
import com.nameisjayant.composeprojects.ui.theme.NFTSectionColor
import com.nameisjayant.composeprojects.ui.theme.kronaFont
import com.nameisjayant.composeprojects.ui.theme.montserratFont


@Composable
fun NFTMobileScreen() {
    NTFMobileRow(
        header = { NFTHeader() },
        selection = { NFTSelection() },
        section = { nftSectionRow() },
        collections = { collectionList() }
    )
}

@Composable
private fun NTFMobileRow(
    modifier: Modifier = Modifier,
    header: (@Composable () -> Unit)? = null,
    selection: (@Composable () -> Unit)? = null,
    section: (LazyListScope.() -> Unit)? = null,
    collections: (LazyListScope.() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .background(NFTBackgroundColor)
            .fillMaxSize()
            .padding(20.dp)
    ) {
        header?.invoke()
        selection?.invoke()
        LazyColumn {
            section?.invoke(this)
            collections?.invoke(this)
        }
    }
}


private fun LazyListScope.collectionList() {
    items(5) {
        CollectionRow()
    }
}

@Composable
private fun CollectionRow(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(bottom = 16.dp)) {
        SpacerHeight(24.dp)
        Text(
            text = stringResource(R.string.latest_collection),
            style = androidx.compose.material3.LocalTextStyle.current.copy(
                fontFamily = kronaFont,
                color = NFTGrayColor,
                fontSize = 18.sp
            )
        )
        SpacerHeight(24.dp)
        Column(
            modifier = Modifier
                .background(Color.Transparent, RoundedCornerShape(16.dp))
                .border(
                    1.dp,
                    NFTBorderColor.copy(0.33f), RoundedCornerShape(16.dp)
                )
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.cool_looking_app),
                style = androidx.compose.material3.LocalTextStyle.current.copy(
                    fontFamily = kronaFont,
                    color = NFTGrayColor,
                )
            )
            SpacerHeight(5.dp)
            Text(
                text = stringResource(R.string.digicore),
                style = TextStyle(
                    fontFamily = montserratFont,
                    color = NFTGrayColor,
                    fontSize = 10.sp
                ),
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string._0_20eth),
                    style = TextStyle(
                        fontFamily = montserratFont,
                        color = NFTGrayColor,
                        fontSize = 10.sp
                    ),
                    modifier = Modifier.weight(1f)
                )
                LikeRow(isBorder = true, bg = Color(0XFF1d283a).copy(0.33f))
            }
        }
    }
}

private fun LazyListScope.nftSectionRow(
    modifier: Modifier = Modifier
) {
    item {
        Box(
            modifier = modifier
                .padding(top = 30.dp, end = 16.dp, start = 16.dp)
                .height(311.dp)
                .background(NFTSectionColor, RoundedCornerShape(35.dp))
                .fillMaxWidth()
        ) {
            LikeRow(modifier = Modifier.align(Alignment.TopEnd))
            Image(
                painter = painterResource(id = R.drawable.monkey),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .height(280.dp)
                    .align(Alignment.BottomCenter)
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    .background(Color.Black.copy(0.66f), RoundedCornerShape(16.dp))
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    text = stringResource(R.string.ape_illustration),
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontFamily = kronaFont,
                        color = NFTGrayColor
                    )
                )
                SpacerHeight(16.dp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(id = R.string.jayant_kumar),
                        style = TextStyle(
                            fontFamily = montserratFont,
                            color = NFTGrayColor,
                            fontSize = 10.sp
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = stringResource(R.string._0_20eth),
                        style = TextStyle(
                            fontFamily = montserratFont,
                            color = NFTGrayColor,
                            fontSize = 10.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun LikeRow(
    modifier: Modifier = Modifier,
    isBorder: Boolean = false,
    bg: Color = Color(0XFF700eca).copy(0.33f)
) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = modifier
            .padding(if (isBorder) 0.dp else 16.dp)
            .background(bg, RoundedCornerShape(16.dp))
            .border(
                1.dp,
                if (isBorder) NFTGrayColor.copy(alpha = 0.33f) else Color.Transparent,
                CircleShape
            )
            .padding(vertical = 12.dp, horizontal = 16.dp)

    ) {
        Text(
            text = stringResource(R.string._320),
            style = androidx.compose.material3.LocalTextStyle.current.copy(
                fontFamily = montserratFont,
                color = NFTGrayColor
            )
        )
        SpacerWidth()
        AppIcon(icon = R.drawable.heart)
    }
}

@Composable
private fun NFTSelection(
    modifier: Modifier = Modifier
) {
    val list = listOf("Trending", "Popular", "Following")
    var selected by remember { mutableIntStateOf(0) }
    Row(
        modifier = modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
    ) {
        list.forEachIndexed { index, s ->
            SelectionRow(selected = index == selected, index = index, title = s, onValueChange = {
                selected = it
            })
        }
    }
}

@Composable
private fun SelectionRow(
    modifier: Modifier = Modifier,
    selected: Boolean,
    index: Int,
    title: String,
    onValueChange: (Int) -> Unit
) {
    Button(
        onClick = { onValueChange(index) }, modifier = modifier
            .padding(end = 8.dp)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = if (selected) NFTGrayColor else NFTDarkBlueColor,
            containerColor = if (selected) NFTDarkBlueColor.copy(alpha = 0.33f) else Color.Transparent
        ),
        border = if (selected) BorderStroke(1.dp, NFTBorderColor.copy(alpha = 0.33f)) else null,
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = title, style = androidx.compose.material3.LocalTextStyle.current.copy(
                fontFamily = montserratFont
            )
        )
    }
}

@Composable
private fun NFTHeader(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(top = 20.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppIcon(icon = R.drawable.nav)
            AppIcon(icon = R.drawable.search, modifier = Modifier
                .drawBehind {
                    drawRoundRect(
                        color = NFTBlueColor.copy(alpha = 0.333f),
                        cornerRadius = CornerRadius(20f),
                    )
                }
                .padding(10.dp), tint = Color.White)
        }
        SpacerHeight(20.dp)
        Text(
            text = stringResource(R.string.find_your_nft_s_today), style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = kronaFont,
            )
        )
    }
}