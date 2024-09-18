package com.demo.marketplacemobileapp.presentation.ui.composable.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HomeHeader() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = "Handmade with love,",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 80.sp,
            softWrap = true,
            overflow = TextOverflow.Clip,
        )
        Text(
            text = "crafted for you.",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 40.sp,
            softWrap = true
        )
    }
}