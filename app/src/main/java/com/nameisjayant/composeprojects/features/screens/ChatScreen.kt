package com.nameisjayant.composeprojects.features.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nameisjayant.composeprojects.R
import com.nameisjayant.composeprojects.components.AppIcon
import com.nameisjayant.composeprojects.components.AppIconButton
import com.nameisjayant.composeprojects.components.SpacerHeight
import com.nameisjayant.composeprojects.components.SpacerWidth
import com.nameisjayant.composeprojects.ui.theme.ChatBlue
import com.nameisjayant.composeprojects.ui.theme.ChatDarkBlue
import com.nameisjayant.composeprojects.ui.theme.ChatDarkColor
import com.nameisjayant.composeprojects.ui.theme.ChatGrayColor
import com.nameisjayant.composeprojects.ui.theme.ChatLightBlue
import com.nameisjayant.composeprojects.ui.theme.ChatLightPink
import com.nameisjayant.composeprojects.ui.theme.ChatRedColor
import com.nameisjayant.composeprojects.ui.theme.interFont


@Composable
fun ChatScreen() {
    ChatRow(
        header = {
            ChatHeader()
        },
        search = {
            SearchTextField()
        },
        story = {
            chatStorySection()
        },
        chat = {
            chatSection()
        }
    )
}

@Composable
private fun ChatRow(
    modifier: Modifier = Modifier,
    header: (@Composable () -> Unit)? = null,
    search: (@Composable () -> Unit)? = null,
    story: (LazyListScope.() -> Unit)? = null,
    chat: (LazyListScope.() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        header?.invoke()
        search?.invoke()
        LazyColumn {
            story?.invoke(this)
            chat?.invoke(this)
        }
    }
}

private fun LazyListScope.chatSection() {
    item {
        SpacerHeight(24.dp)
    }
    items(chatModalList, key = { it.id }) {
        ChattingRow(chat = it)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ChattingRow(
    modifier: Modifier = Modifier,
    chat: ChatModal
) {
    Row(
        modifier = modifier.padding(bottom = 24.dp, start = 16.dp, end = 16.dp)
    ) {
        Row(modifier = Modifier.weight(1f)) {
            if (!chat.isGroup) {
                Box {
                    AppIcon(icon = chat.image, modifier = Modifier.size(54.dp))
                    if (chat.isOnline)
                        Box(
                            modifier = Modifier
                                .background(Color.White, CircleShape)
                                .padding(3.dp)
                                .align(Alignment.BottomEnd)
                        ) {
                            Spacer(
                                modifier = Modifier
                                    .background(
                                        color = Color(0XFF5AE558),
                                        CircleShape
                                    )
                                    .size(10.dp)
                            )
                        }
                }
            } else {
                FlowRow(
                    maxItemsInEachRow = 2,
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    repeat(chatModalList.size) {
                        if (it < 3)
                            AppIcon(icon = chatModalList[it].image, modifier = Modifier.size(25.dp))
                    }
                    Box(
                        modifier = Modifier
                            .background(ChatBlue, CircleShape)
                            .size(25.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string._4), style = TextStyle(
                                color = Color.White,
                                fontFamily = interFont,
                                fontWeight = FontWeight.Medium,
                                fontSize = 11.sp
                            )
                        )
                    }
                }
            }
            SpacerWidth()
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = if (chat.isGroup) stringResource(R.string.product_design) else chat.name,
                    style = TextStyle(
                        color = ChatDarkColor,
                        fontFamily = interFont,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                SpacerHeight(5.dp)
                Text(
                    text = if (chat.isTyping) stringResource(R.string.typing) else chat.message,
                    style = TextStyle(
                        color = ChatLightPink,
                        fontFamily = interFont,
                        fontSize = 13.sp,
                    )
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Text(
                text = chat.time,
                style = TextStyle(
                    color = ChatLightPink,
                    fontFamily = interFont,
                    fontSize = 11.sp,
                )
            )
            if (!chat.isDoubleTick)
                Box(
                    modifier = Modifier
                        .padding(top = 2.dp)
                        .background(ChatBlue, CircleShape)
                        .size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string._6), style = TextStyle(
                            color = Color.White,
                            fontFamily = interFont,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            else
                AppIcon(
                    icon = R.drawable.double_tick,
                    tint = if (chat.receive) Color.Unspecified else ChatLightPink
                )
        }
    }
}

private fun LazyListScope.chatStorySection(
    modifier: Modifier = Modifier
) {
    item {
        SpacerHeight(24.dp)
        LazyRow(modifier = modifier) {
            items(chatModalList, key = { it.id }) {
                StoryRow(chat = it)
            }
        }
        SpacerHeight(24.dp)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(
                    Brush.linearGradient(
                        listOf(
                            Color(0XFFFFFFFF),
                            Color(0XFF7E80B4),
                            Color(0XFFFFFFFF)
                        )
                    )
                )
        )
    }
}

@Composable
private fun StoryRow(
    modifier: Modifier = Modifier,
    chat: ChatModal
) {
    Column(
        modifier = modifier.padding(start = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .border(
                    2.dp,
                    Brush.linearGradient(
                        listOf(ChatDarkBlue, ChatLightBlue)
                    ), CircleShape
                )
                .padding(5.dp)
        ) {
            AppIcon(icon = chat.image, modifier = Modifier.size(60.dp))

        }
        SpacerHeight(5.dp)
        Text(
            text = chat.name, style = TextStyle(
                color = ChatLightPink,
                fontSize = 13.sp,
                fontFamily = interFont
            )
        )
    }
}

@Composable
private fun SearchTextField(
    modifier: Modifier = Modifier
) {
    var search by remember { mutableStateOf("") }
    BasicTextField(
        value = search,
        onValueChange = {
            search = it
        },
        modifier = modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(ChatGrayColor, CircleShape)
                .padding(16.dp),
        ) {
            AppIcon(icon = R.drawable.search, tint = ChatLightPink, modifier = Modifier.size(22.dp))
            SpacerWidth(5.dp)
            Box {
                if (search.isEmpty())
                    Text(
                        text = stringResource(R.string.search), style = TextStyle(
                            color = ChatLightPink,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = interFont
                        )
                    )
                it.invoke()
            }
        }
    }
}

@Composable
private fun ChatHeader(
    modifier: Modifier = Modifier
) {
    val headerText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = ChatDarkColor,
                fontFamily = interFont
            )
        ) {
            append(stringResource(R.string.hello))
        }
        append(" ")
        withStyle(
            style = SpanStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold,
                color = ChatDarkColor,
                fontFamily = interFont
            )
        ) {
            append(stringResource(R.string.david))
        }
    }
    Row(
        modifier = modifier.padding(top = 16.dp, end = 16.dp, start = 16.dp),
    ) {
        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            AppIcon(icon = R.drawable.man, modifier = Modifier.size(46.dp))
            SpacerWidth()
            Text(text = headerText)
        }
        Row(
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Box {
                AppIconButton(icon = R.drawable.notification, modifier = Modifier.size(24.dp)) {}
                Spacer(
                    modifier = Modifier
                        .padding(2.5.dp)
                        .background(ChatRedColor, CircleShape)
                        .size(7.dp)
                        .align(Alignment.TopEnd)
                )
            }
        }
    }
}


data class ChatModal(
    val id: Int,
    val name: String,
    @DrawableRes val image: Int,
    val isGroup: Boolean = false,
    val isTyping: Boolean = false,
    val messagePending: Boolean = false,
    val isDoubleTick: Boolean = false,
    val isOnline: Boolean = false,
    val message: String,
    val time: String,
    val receive: Boolean = true
)

val chatModalList = listOf(
    ChatModal(
        1,
        "John",
        R.drawable.man,
        message = "When the meeting is Schedule",
        time = "12:35",
        isDoubleTick = true,
        isOnline = true
    ),
    ChatModal(
        2,
        "Emily",
        R.drawable.girl,
        message = "Nice Work I love it.",
        time = "Yesterday",
        isTyping = true
    ),
    ChatModal(
        3,
        "Ashely",
        R.drawable.girl_1,
        message = "Hey",
        time = "Today",
        isDoubleTick = true,
        isGroup = true
    ),
    ChatModal(
        4,
        "Olivia",
        R.drawable.girl_2,
        message = "How are you?",
        time = "02:34",
        isDoubleTick = true,
        receive = false
    ),
    ChatModal(
        5, "Darcy", R.drawable.girl_3, message = "I am good!", time = "12/04/2024"
    ),
    ChatModal(
        6, "John", R.drawable.man, message = "Good Work John", time = "01:40", isDoubleTick = true,
        receive = false
    ),
    ChatModal(
        7,
        "Emily",
        R.drawable.girl,
        message = "Nice to see you girl!l",
        time = "Yesterday",
        isDoubleTick = true

    ),
    ChatModal(
        8,
        "Ashely",
        R.drawable.girl_1,
        message = "Hey",
        time = "Today",
        isDoubleTick = true,
        isGroup = true
    ),
    ChatModal(
        9,
        "Olivia",
        R.drawable.girl_2,
        message = "How are you?",
        time = "02:34",
        isDoubleTick = true
    ),
)