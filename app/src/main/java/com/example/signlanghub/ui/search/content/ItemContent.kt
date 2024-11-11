package com.example.signlanghub.ui.search.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ItemContent(
    content: @Composable () -> Unit
) {
    Column {
        Row {
            content()
        }
    }
}

@Preview
@Composable
private fun ItemContentPreview() {
    ItemContent(
        content = {
            Box(modifier = Modifier.size(20.dp))
        }
    )
}