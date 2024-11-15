package com.example.signlanghub.ui.search.composable.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.signlanghub.R
import com.example.signlanghub.ui.common.composable.text.DefaultText
import com.example.signlanghub.ui.common.composable.video.MediaVideo
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoCheckDialog(
    url: String,
    onClickConfirm: (String) -> Unit,
    onDismissRequest: () -> Unit
) {

    BasicAlertDialog(
        modifier = Modifier.padding(20.dp),
        onDismissRequest = onDismissRequest,
    ) {
        Surface(
            shape = RoundedCornerShape(24.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp),
            ) {
                Column(
                    modifier =
                        Modifier
                            .clickable {
                                onClickConfirm(url)
                                onDismissRequest()
                            }
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Timber.d("url : $url")
                    MediaVideo(
                       modifier = Modifier.size(width = 200.dp, height = 150.dp),
                       uri = url
                   )

                    DefaultText(
                        text = "영상을 확인해주세요.",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                    )

                    Button(
                        onClick = {
                            onClickConfirm(url)
                            onDismissRequest()
                        }
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                        ) {
                            Surface(
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .clip(RoundedCornerShape(8.dp))
                                    .size(50.dp),
                                color = Color.White
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.image_ok),
                                    contentDescription = "confirm",
                                    tint = Color.Unspecified
                                )
                            }

                            DefaultText(
                                modifier = Modifier.align(Alignment.Center),
                                text = "확인",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                            )
                        }
                    }

                    Button(
                        onClick = onDismissRequest
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                        ) {
                            Surface(
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .size(50.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                color = Color.White
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.image_retry),
                                    contentDescription = "retry",
                                    tint = Color.Unspecified
                                )
                            }

                            DefaultText(
                                modifier = Modifier.align(Alignment.Center),
                                text = "다시 선택",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                            )
                        }
                    }
                }



            }
        }
    }
}

@Preview
@Composable
private fun VideoCheckDialogPreview() {
    VideoCheckDialog(
        url = "",
        onClickConfirm = {},
        onDismissRequest = {}
    )
}