package com.nameisjayant.composeprojects.features.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nameisjayant.composeprojects.features.main.data.MainData
import com.nameisjayant.composeprojects.features.main.data.MainDataObject

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(
            items = MainDataObject.getMainDataList()
        ) { _, item ->
            ScreenButton(data = item) {

            }
        }
    }
}


@Composable
fun ScreenButton(
    modifier: Modifier = Modifier,
    data: MainData,
    onClick: (route: Any) -> Unit
) {
    Button(onClick = { onClick(data.route) }, modifier) {
        Text(text = data.title)
    }
}