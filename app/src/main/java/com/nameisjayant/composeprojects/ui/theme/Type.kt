package com.nameisjayant.composeprojects.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nameisjayant.composeprojects.R

val interFont = FontFamily(Font(R.font.inter))
val kronaFont = FontFamily(Font(R.font.krona_one))
val montserratFont = FontFamily(Font(R.font.montserrat))



val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)