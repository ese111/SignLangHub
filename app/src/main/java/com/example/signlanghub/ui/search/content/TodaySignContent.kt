package com.example.signlanghub.ui.search.content

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.signlanghub.ui.common.composable.video.Video

@Composable
fun TodaySignContent(
    modifier: Modifier = Modifier
) {
    ItemContent(
        modifier = modifier,
        title = "오늘의 수화",
        content = {
            Video(
                modifier = Modifier.clip(RoundedCornerShape(12.dp)).size(200.dp),
                uri = "http://sldict.korean.go.kr/multimedia/multimedia_files/convert/20201211/785178/MOV000357024_700X466.mp4"
            )
        },
    )
}

@Preview
@Composable
private fun TodaySignContentPreview() {
    TodaySignContent()
}
