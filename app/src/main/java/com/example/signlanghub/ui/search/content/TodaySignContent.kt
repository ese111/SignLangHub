package com.example.signlanghub.ui.search.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.signlanghub.ui.common.composable.video.Video

@Composable
fun TodaySignContent(
    modifier: Modifier = Modifier,
) {

    ItemContent {
        Video()
    }
}

@Preview
@Composable
private fun TodaySignContentPreview() {
    TodaySignContent()
}