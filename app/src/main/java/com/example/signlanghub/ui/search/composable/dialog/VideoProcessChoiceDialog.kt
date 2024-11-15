package com.example.signlanghub.ui.search.composable.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.signlanghub.ui.common.composable.text.DefaultText
import com.example.signlanghub.ui.common.icon.IconPack
import com.example.signlanghub.ui.common.icon.myiconpack.PhotoCamera
import com.example.signlanghub.ui.common.icon.myiconpack.PhotoLibrary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoProcessChoiceDialog(
    onDismissRequest: () -> Unit,
    takePicture: () -> Unit,
    goToGallery: () -> Unit,
) {
    BasicAlertDialog(
        modifier = Modifier.padding(20.dp),
        onDismissRequest = onDismissRequest,
    ) {
        Surface(
            shape = RoundedCornerShape(24.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 12.dp),
            ) {
                Column(
                    modifier =
                        Modifier
                            .clickable {
                                goToGallery()
                                onDismissRequest()
                            }.fillMaxWidth()
                            .padding(vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    AsyncImage(
                        contentDescription = "choice",
                        modifier = Modifier.size(88.dp),
                        model = "http://sldict.korean.go.kr/multimedia/multimedia_files/convert/20151222/232703/IMG000227073_700X466.jpg",
                    )

                    DefaultText(
                        text = "녹화 방식 선택하기",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                    )
                }

                Row(
                    modifier =
                        Modifier
                            .clickable {
                                goToGallery()
                                onDismissRequest()
                            }.fillMaxWidth()
                            .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = IconPack.PhotoLibrary,
                        contentDescription = "gallery",
                    )

                    DefaultText(
                        text = "앨범에서 선택하기",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                    )
                }

                Row(
                    modifier =
                        Modifier
                            .clickable {
                                takePicture()
                                onDismissRequest()
                            }.fillMaxWidth()
                            .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = IconPack.PhotoCamera,
                        contentDescription = "take photo",
                    )

                    DefaultText(
                        text = "사진 촬영하기",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignLangBottomSheetPreview() {
    VideoProcessChoiceDialog(
        onDismissRequest = { },
        takePicture = {},
        goToGallery = {},
    )
}
