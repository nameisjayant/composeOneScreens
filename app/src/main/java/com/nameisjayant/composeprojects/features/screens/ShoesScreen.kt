package com.nameisjayant.composeprojects.features.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.nameisjayant.composeprojects.R
import com.nameisjayant.composeprojects.components.AppIcon
import com.nameisjayant.composeprojects.components.AppIconButton
import com.nameisjayant.composeprojects.components.ImageComponent
import com.nameisjayant.composeprojects.components.SpacerHeight
import com.nameisjayant.composeprojects.components.SpacerWidth
import com.nameisjayant.composeprojects.ui.theme.Black10
import com.nameisjayant.composeprojects.ui.theme.Neutral20
import com.nameisjayant.composeprojects.ui.theme.Neutral40
import com.nameisjayant.composeprojects.ui.theme.Neutral80
import com.nameisjayant.composeprojects.ui.theme.Orange
import com.nameisjayant.composeprojects.ui.theme.TextColor_1
import com.nameisjayant.composeprojects.ui.theme.interFont


@Composable
fun ShoesScreen() {
    var search by remember { mutableStateOf("") }

    ShoesRow(
        headerSection = {
            ShoesHeader()
        },
        searchSection = {
            SearchSection(value = search, onValueChange = { search = it })
        },
        walletSection = {
            WalletRow()
        },
        categorySection = {
            ShopByCategory()
        }
    ) {
        ShoeShowCase()
    }
}

@Composable
private fun ShoesRow(
    modifier: Modifier = Modifier,
    headerSection: (@Composable () -> Unit)? = null,
    searchSection: (@Composable () -> Unit)? = null,
    walletSection: (@Composable () -> Unit)? = null,
    categorySection: (@Composable () -> Unit)? = null,
    forYouSection: (@Composable () -> Unit)? = null,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            headerSection?.invoke()
            searchSection?.invoke()
            walletSection?.invoke()
            categorySection?.invoke()
            forYouSection?.invoke()
        }
    }
}

@Composable
private fun ShoesHeader(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Box(modifier = Modifier.weight(0.5f)) {}
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f),
            horizontalArrangement = Arrangement.Absolute.SpaceAround
        ) {
            ShoesData.getHeaderData().forEach {
                AppIconButton(icon = it.icon, modifier = Modifier.size(20.dp)) {}
            }
        }
    }
}


@Composable
private fun WalletRow(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(top = 16.dp)
            .height(IntrinsicSize.Min)
            .padding(16.dp)
    ) {
        WalletTextRow(
            title = stringResource(R.string.wallet_balance),
            icon = R.drawable.wallet,
            des = stringResource(R.string.rp1_000_000),
            modifier = Modifier.weight(1f, false)
        )
        SpacerWidth(20.dp)
        VerticalDivider()
        SpacerWidth(20.dp)
        WalletTextRow(
            title = stringResource(R.string.top_up_balance),
            icon = R.drawable.add,
            des = stringResource(R.string.top_up)
        )
    }
}

@Composable
private fun WalletTextRow(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes icon: Int,
    des: String
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title, style = MaterialTheme.typography.labelMedium.copy(
                color = TextColor_1,
                fontWeight = FontWeight.W500
            )
        )
        SpacerHeight(8.dp)
        Row {
            AppIcon(icon = icon, modifier = Modifier.size(24.dp))
            SpacerWidth()
            Text(
                text = des, style = LocalTextStyle.current.copy(
                    fontWeight = FontWeight.W700,
                    color = TextColor_1
                )
            )
        }
    }
}

typealias onValueChange = (String) -> Unit

@Composable
private fun SearchFieldComponent(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: onValueChange
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = modifier
                .height(48.dp)
                .background(
                    Color.White, RoundedCornerShape(10.dp)
                )
                .border(1.dp, Neutral40, RoundedCornerShape(10.dp))
                .padding(start = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            if (value.isEmpty())
                Text(
                    text = stringResource(R.string.search_items),
                    style = TextStyle.Default.copy(
                        color = Neutral80,
                        fontWeight = FontWeight.W400
                    )
                )
            it.invoke()
        }
    }
}

@Composable
private fun SearchSection(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: onValueChange,
) {
    Column {
        SpacerHeight(16.dp)
        Row(
            modifier = modifier
        ) {
            SearchFieldComponent(
                value = value,
                modifier = Modifier.weight(1f),
                onValueChange = onValueChange
            )
            SpacerWidth(16.dp)
            AppIcon(
                icon = R.drawable.search,
                modifier = Modifier
                    .drawBehind {
                        drawRoundRect(
                            color = Orange,
                            cornerRadius = CornerRadius(8.dp.toPx())
                        )
                    }
                    .padding(10.dp)
                    .align(Alignment.CenterVertically),
                tint = Color.White
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ShopByCategory(
    modifier: Modifier = Modifier
) {
    Column {
        SpacerHeight(16.dp)
        HomeTitleText(title = stringResource(R.string.shop_by_category))
        SpacerHeight(16.dp)
        FlowRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ShoesData.getCategoryData().forEach {
                ShopRow(title = stringResource(it.title), icon = it.icon)
            }
        }
    }
}

@Composable
private fun ShopRow(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes icon: Int
) {
    Box(
        modifier = modifier
            .padding(end = 10.dp)
            .background(Neutral20, RoundedCornerShape(10.dp))
            .width(108.dp)
            .height(103.dp),
        contentAlignment = Alignment.Center
    ) {
        Column {
            AppIcon(
                icon = icon, modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.CenterHorizontally)
            )
            SpacerHeight(8.dp)
            HomeTitleText(title = title, color = Orange)
        }
    }
}

@Composable
private fun HomeTitleText(
    modifier: Modifier = Modifier,
    title: String,
    color: Color = TextColor_1
) {
    Text(
        text = title, style = MaterialTheme.typography.titleMedium.copy(
            color = color,
            fontWeight = FontWeight.Bold,
            fontFamily = interFont
        ),
        modifier = modifier
    )
}

@Composable
private fun ShoesShowCaseRow(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    title: String,
    name: String,
    des: String
) {
    val width = (LocalConfiguration.current.screenWidthDp.dp - 50.dp) / 2
    Column(
        modifier = modifier.padding(top = 16.dp)
    ) {
        ImageComponent(
            image = image, modifier = Modifier
                .width(width)
                .clip(RoundedCornerShape(10.dp))
        )
        SpacerHeight()
        Text(
            text = title, style = LocalTextStyle.current.copy(
                color = TextColor_1,
                fontWeight = FontWeight.W700
            )
        )
        SpacerHeight(8.dp)
        Text(
            text = name, style = MaterialTheme.typography.labelMedium.copy(
                color = Black10,
                fontWeight = FontWeight.W500
            )
        )
        SpacerHeight(8.dp)
        Text(
            text = des, style = MaterialTheme.typography.labelLarge.copy(
                color = Orange,
                fontWeight = FontWeight.W700
            )
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ShoeShowCase(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        HomeTitleText(title = stringResource(R.string.for_you))
        FlowRow(
            maxItemsInEachRow = 2,
            modifier = Modifier.fillMaxWidth()
        ) {
            ShoesData.getForYouData().forEach {
                ShoesShowCaseRow(
                    image = it.image,
                    title = stringResource(it.title),
                    name = stringResource(id = it.des),
                    des = stringResource(it.price),
                    modifier = Modifier.weight(0.5f)
                )
            }
        }
    }
}

internal data class HeaderModel(
    @DrawableRes val icon: Int,
    val path: String
)

internal data class CategoryModal(
    @StringRes val title: Int,
    @DrawableRes val icon: Int
)

internal data class ForYouModal(
    @StringRes val title: Int,
    @StringRes val des: Int,
    @StringRes val price: Int,
    @DrawableRes val image: Int
)

private object ShoesData {

    fun getHeaderData() = listOf(
        HeaderModel(R.drawable.wishlist, "/wishlist"),
        HeaderModel(R.drawable.shop, "/wishlist"),
        HeaderModel(R.drawable.notification_1, "/wishlist"),
    )

    fun getCategoryData() = listOf(
        CategoryModal(
            R.string.footwear,
            R.drawable.footwear
        ),
        CategoryModal(
            R.string.bags,
            R.drawable.bags
        ),
        CategoryModal(
            R.string.apparel,
            R.drawable.shirt
        )
    )

    fun getForYouData() = listOf(
        ForYouModal(
            R.string.air_legging_sport,
            R.string.apparel,
            R.string.rp200_000,
            R.drawable.shoe_1
        ),
        ForYouModal(
            R.string.aero_sport_infinity_max,
            R.string.footwear,
            R.string.rp450_000,
            R.drawable.shoe_2
        ),
        ForYouModal(
            R.string.sport_runner_blue_edition,
            R.string.footwear,
            R.string.rp250_000,
            R.drawable.shoe_3
        ),
        ForYouModal(
            R.string.sport_bag,
            R.string.bags,
            R.string.rp350_000,
            R.drawable.shoe_4
        )
    )

}