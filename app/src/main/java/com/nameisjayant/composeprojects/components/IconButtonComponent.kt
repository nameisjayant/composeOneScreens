package com.nameisjayant.composeprojects.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun AppIconButton(
    modifier: Modifier = Modifier,
    @DrawableRes icon:Int?=null,
    imageVector: ImageVector?=null,
    tint:Color = Color.Unspecified,
    onClick:()->Unit
) {
    icon?.let {
        IconButton(onClick = onClick, modifier = modifier) {
            AppIcon(icon = it, tint = tint)
        }
    }
    imageVector?.let {
        IconButton(onClick = onClick, modifier = modifier) {
            AppIcon(imageVector = it, tint = tint)
        }
    }
}