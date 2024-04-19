package com.nameisjayant.composeprojects.features.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nameisjayant.composeprojects.R
import com.nameisjayant.composeprojects.components.AppIcon
import com.nameisjayant.composeprojects.components.AppIconButton
import com.nameisjayant.composeprojects.components.SpacerHeight
import com.nameisjayant.composeprojects.components.SpacerWidth
import com.nameisjayant.composeprojects.ui.theme.ScoreBoardGray
import com.nameisjayant.composeprojects.ui.theme.ScoreLightGray
import com.nameisjayant.composeprojects.ui.theme.ScoreLinearGradient
import com.nameisjayant.composeprojects.ui.theme.ScoreboardBg
import com.nameisjayant.composeprojects.ui.theme.ScoreboardDarkGray
import com.nameisjayant.composeprojects.ui.theme.interFont
import com.nameisjayant.composeprojects.ui.theme.montserratFont


@Composable
fun ScoreboardScreen() {

    ScoreboardRow(
        search = {
            ScoreboardSearchField()
        },
        selectGame = {
            GameSelection()
        }
    ) {
        ScoreBoardSection()
    }
}


@Composable
private fun ScoreboardRow(
    modifier: Modifier = Modifier,
    search: (@Composable () -> Unit)? = null,
    selectGame: (@Composable () -> Unit)? = null,
    scoreboard: (@Composable () -> Unit)? = null
) {

    Column(
        modifier = modifier
            .background(ScoreboardBg)
            .fillMaxSize()
    ) {
        search?.invoke()
        selectGame?.invoke()
        scoreboard?.invoke()
    }

}


@Composable
private fun ScoreboardSearchField(
    modifier: Modifier = Modifier
) {
    var search by remember { mutableStateOf("") }
    BasicTextField(
        value = search,
        onValueChange = {
            search = it
        },
        modifier = modifier.padding(28.dp),
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        textStyle = TextStyle(
            color = Color.White
        ),
        cursorBrush = SolidColor(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .background(ScoreboardDarkGray, RoundedCornerShape(14.dp))
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppIcon(
                icon = R.drawable.search,
                tint = ScoreBoardGray,
                modifier = Modifier.size(19.dp)
            )
            SpacerWidth(5.dp)
            Box {
                if (search.isEmpty())
                    Text(
                        text = stringResource(R.string.search_your_competition), style = TextStyle(
                            color = ScoreBoardGray,
                            fontSize = 14.sp,
                            fontFamily = interFont
                        )
                    )
                it.invoke()
            }
        }
    }
}

@Composable
private fun GameSelection(
    modifier: Modifier = Modifier
) {
    var selected by remember {
        mutableIntStateOf(
            ScoreboardData.getGameData().first().id
        )
    }
    LazyRow(modifier = modifier) {
        item {
            SpacerWidth(28.dp)
        }
        items(ScoreboardData.getGameData(), key = { it.id }) {
            Row(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable {
                        selected = it.id

                    }
                    .drawBehind {
                        if (selected == it.id)
                            drawRoundRect(
                                brush = ScoreLinearGradient,
                                cornerRadius = CornerRadius(50.dp.toPx())
                            )
                        else
                            drawCircle(
                                color = ScoreboardDarkGray
                            )
                    }
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppIcon(icon = it.icon)
                if (it.id == selected) {
                    SpacerWidth()
                    Text(
                        text = it.title, style = TextStyle(
                            color = Color.White,
                            fontSize = 14.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun ScoreBoardSection(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            CountryHeader(
                icon = R.drawable.spain,
                league = stringResource(R.string.la_liga),
                country = stringResource(R.string.spain)
            )
            ScoreBoard(data = ScoreboardData.getLaigaData())
            CountryHeader(
                icon = R.drawable.england,
                league = stringResource(R.string.premier_league),
                country = stringResource(R.string.england)
            )
            ScoreBoard(data = ScoreboardData.getPremierLeagueData())
            CountryHeader(
                icon = R.drawable.spain,
                league = stringResource(R.string.la_liga),
                country = stringResource(R.string.spain)
            )
            ScoreBoard(data = ScoreboardData.getLaigaData())
        }
    }
}

@Composable
private fun ScoreBoard(
    modifier: Modifier = Modifier,
    data: List<Score>
) {

    Column(
        modifier = modifier
            .padding(horizontal = 28.dp)
            .background(ScoreboardDarkGray, RoundedCornerShape(19.dp))
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Row {
            Text(
                text = stringResource(R.string.team), style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = montserratFont,
                    color = Color.White
                ),
                modifier = Modifier.weight(0.4f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Column(modifier = Modifier.weight(0.6f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ScoreboardData.getPoint().forEach {
                        Text(
                            text = it,
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = montserratFont,
                                color = Color.White
                            )
                        )
                    }
                }
                SpacerHeight()
                HorizontalDivider(
                    color = ScoreboardBg,
                    thickness = 2.dp
                )
            }
        }
        data.forEach {
            Row(modifier = Modifier.padding(top = 16.dp)) {
                Row(
                    modifier = Modifier.weight(0.4f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppIcon(icon = it.flag, modifier = Modifier.size(16.dp))
                    SpacerWidth()
                    Text(
                        text = it.name,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = montserratFont,
                            color = Color.White
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                SpacerWidth()
                Column(modifier = Modifier.weight(0.6f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        it.points.forEach {
                            Text(
                                text = "$it",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = montserratFont,
                                    color = Color.White
                                ),
                            )
                        }
                    }
                    SpacerHeight()
                    HorizontalDivider(
                        color = ScoreboardBg,
                        thickness = 2.dp
                    )
                }
            }
        }
    }
}

@Composable
private fun CountryHeader(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    league: String,
    country: String
) {
    Row(modifier = modifier.padding(28.dp), verticalAlignment = Alignment.CenterVertically) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            AppIcon(icon = icon)
            SpacerWidth()
            Column {
                Text(
                    text = league, style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                SpacerHeight(5.dp)
                Text(
                    text = country, style = TextStyle(
                        color = ScoreLightGray,
                        fontSize = 16.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
        AppIconButton(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            tint = Color.White
        ) {

        }
    }
}

data class Game(
    val id: Int,
    val title: String,
    @DrawableRes val icon: Int
)

data class Score(
    val id: Int,
    val name: String,
    @DrawableRes val flag: Int,
    val points: List<Int>
)

object ScoreboardData {

    fun getGameData(): List<Game> {
        return listOf(
            Game(
                1, "Soccer", R.drawable.soccer
            ), Game(
                2, "BasketBall", R.drawable.basketball
            ), Game(
                3, "Rugby", R.drawable.rugby
            ), Game(
                4, "BaseBall", R.drawable.baseball
            ), Game(
                5, "Cricket", R.drawable.cricket
            )
        )
    }

    fun getLaigaData(): List<Score> {
        return listOf(
            Score(
                1, "Atlético Madrid FC", R.drawable.atletico, listOf(2, 1, 6, 23, 38)
            ),
            Score(
                2, "Real Madrid", R.drawable.realmadrid, listOf(4, 3, 7, 15, 37)
            ),
            Score(
                3, "Barcelona", R.drawable.barcelona, listOf(4, 4, 9, 20, 34)
            ), Score(
                4, "Villareal", R.drawable.villareal, listOf(8, 2, 10, 16, 32)
            )
        )
    }

    fun getPremierLeagueData(): List<Score> {
        return listOf(
            Score(
                1, "Liverpool", R.drawable.liverpool, listOf(2, 1, 6, 23, 38)
            ),
            Score(
                2, "Man United", R.drawable.manchester, listOf(4, 3, 7, 15, 37)
            ),
            Score(
                3, "Leicester City", R.drawable.leicester, listOf(4, 4, 9, 20, 34)
            ), Score(
                4, "Villareal", R.drawable.villareal, listOf(8, 2, 10, 16, 32)
            )
        )
    }

    fun getPoint(): List<String> = listOf("D", "L", "Ga", "Gd", "Pts")

}