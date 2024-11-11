package com.example.signlanghub.ui.common.composable.banner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun AdBanner(
    modifier: Modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 8.dp),
    title: String,
    description: String,
    image: ImageVector,
    readCount: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "광고 및 복지 정보",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                modifier = Modifier.size(80.dp).clip(RoundedCornerShape(16.dp)),
                imageVector = image,
                contentDescription = "Profile",
            )
            Column(
            ) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.fillMaxWidth().height(2.dp)
                )

                Text(
                    text = description,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(
                    modifier = Modifier.fillMaxWidth().height(10.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "Add",
                    )

                    Text(
                        text = "Today • $readCount",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun AdBannerPreview() {
    AdBanner(
        title = "Title",
        description = "Description",
        image = Icons.Default.AddCircle,
        readCount = "100"
    )
}