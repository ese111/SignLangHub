package com.example.signlanghub.ui.search.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.signlanghub.ui.common.composable.text.DefaultText

@Composable
fun EmptyContent(
    modifier: Modifier = Modifier,
    text: String = "검색 결과가 없습니다.",
) {
    Box(
        modifier = modifier.fillMaxWidth().height(200.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(42.dp),
                imageVector = Icons.Default.Info,
                contentDescription = "검색 결과가 없습니다.",
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(12.dp))
            DefaultText(
                modifier = Modifier.fillMaxWidth(),
                text = text,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun EmptyContentPreview() {
    EmptyContent()
}