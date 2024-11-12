package com.example.signlanghub.ui.search.content

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.signlanghub.ui.common.composable.video.Video

@Composable
fun SearchResultContent(
    title: String,
    description: String,
    videoUrl: String,
) {
    ItemContent(
        title = title,
        description = description,
    ) {
        Video(
            modifier = Modifier.size(200.dp),
            uri = videoUrl,
        )
    }
}

@Preview
@Composable
private fun SearchResultContentPreview() {
    SearchResultContent(
        title = "Title",
        description = "Description",
        videoUrl = "http://sldict.korean.go.kr/multimedia/multimedia_files/convert/20201211/785178/MOV000357024_700X466.mp4",
    )
}
