package com.example.signlanghub.ui.search.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.signlanghub.ui.common.composable.text.DefaultText

@Composable
fun ItemContent(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DefaultText(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            content()

            description?.let { description ->
                DefaultText(
                    text = description,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemContentPreview() {
    ItemContent(
        title = "Title",
        description = "Description",
        content = {
            Box(modifier = Modifier.size(200.dp).background(color = Color.Gray))
        },
    )
}
