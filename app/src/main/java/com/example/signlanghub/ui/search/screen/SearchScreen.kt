package com.example.signlanghub.ui.search.screen

import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.signlanghub.R
import com.example.signlanghub.ui.common.composable.banner.AdBanner
import com.example.signlanghub.ui.common.composable.text.DefaultText
import com.example.signlanghub.ui.common.composable.topbar.BasicAppbar
import com.example.signlanghub.ui.common.icon.IconPack
import com.example.signlanghub.ui.common.icon.myiconpack.Inbox
import com.example.signlanghub.ui.search.SearchContract
import com.example.signlanghub.ui.search.UiState
import com.example.signlanghub.ui.search.composable.dialog.VideoCheckDialog
import com.example.signlanghub.ui.search.composable.dialog.VideoProcessChoiceDialog
import com.example.signlanghub.ui.search.content.EmptyContent
import com.example.signlanghub.ui.search.content.SearchResultContent
import com.example.signlanghub.ui.search.content.TodaySignContent
import com.example.signlanghub.util.createVideoFile
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SearchScreen(
    state: SearchContract.State,
    effectFlow: Flow<SearchContract.Effect>?,
    onEventSent: (event: SearchContract.Event) -> Unit,
    onNavigationRequested: (SearchContract.Effect.Navigation) -> Unit,
) {
    val context = LocalContext.current

    // Camera permission state
    val cameraPermissionState =
        rememberMultiplePermissionsState(
            listOf(
                android.Manifest.permission.CAMERA,
            ),
        )

    LaunchedEffect(true) {
        cameraPermissionState.launchMultiplePermissionRequest()
    }

    val pickVideoLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
        ) { result: Uri? ->
            result?.let { uri ->
                Timber.i("Pick Video Uri : $uri")
                onEventSent(SearchContract.Event.GetVideoUri(uri))
                onEventSent(SearchContract.Event.ShowVideoCheckDialog)
            }
        }

    val takeVideoLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.CaptureVideo(),
            onResult = { isSuccess ->
                Timber.i("Take Video Result : $isSuccess uri : ${state.videoUri}")
                if (isSuccess) {
                    onEventSent(SearchContract.Event.ShowVideoCheckDialog)
                }
            },
        )

    LaunchedEffect(true) {
        effectFlow?.collect {
            when (it) {
                SearchContract.Effect.ShowErrorToast -> {
                    Toast.makeText(context, "검색 결과를 가져오는 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
                }

                SearchContract.Effect.Navigation.Banner -> {
                }

                SearchContract.Effect.Navigation.PopBackStack -> {
                }
            }
        }
    }

    Scaffold(
        topBar = {
            BasicAppbar(
                title = "검색하기",
                popbackStack = {
                    onNavigationRequested(
                        SearchContract.Effect.Navigation.PopBackStack,
                    )
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxWidth().padding(horizontal = 20.dp),
        ) {
            if (state.videoProcessDialogVisible) {
                if (cameraPermissionState.allPermissionsGranted) {
                    VideoProcessChoiceDialog(
                        onDismissRequest = {
                            onEventSent(SearchContract.Event.DismissVideoProcessDialog)
                        },
                        goToGallery = {
                            pickVideoLauncher.launch("video/*")
                        },
                        takePicture = {
                            createVideoFile(context.contentResolver)?.let { uri ->
                                onEventSent(SearchContract.Event.GetVideoUri(uri))
                                takeVideoLauncher.launch(uri)
                            }
                        },
                    )
                } else {
                    if (cameraPermissionState.permissions.all { it.status.shouldShowRationale }) {
                        Toast.makeText(context, "카메라 권한을 허용해야 해당 기능이 사용가능합니다.", Toast.LENGTH_SHORT).show()
                        onEventSent(SearchContract.Event.DismissVideoProcessDialog)
                    } else {
                        Toast.makeText(context, "카메라 권한을 허용해야 해당 기능이 사용가능합니다.", Toast.LENGTH_SHORT).show()
                        onEventSent(SearchContract.Event.DismissVideoProcessDialog)
                    }
                }
            }

            if (state.isVideoCheckDialogVisible) {
                VideoCheckDialog(
                    url = state.videoUri?.toString().orEmpty(),
                    onClickConfirm = { uri ->
                        state.videoUri?.let {
                            var columnIndex = 0
                            val proj = arrayOf(MediaStore.Video.Media.DATA)
                            val cursor = context.contentResolver.query(state.videoUri, proj, null, null, null)

                            if (cursor!!.moveToFirst()) {
                                columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
                            }

                            val url = cursor.getString(columnIndex)
                            onEventSent(SearchContract.Event.OnSearchVideo(url))
                        } ?: run {
                            Toast.makeText(context, "사진을 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                        }
                    },
                    onDismissRequest = {
                        onEventSent(SearchContract.Event.DismissVideoCheckDialog)
                    },
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(18.dp),
            ) {
                Icon(
                    modifier = Modifier.size(80.dp).background(Color.White).clip(RoundedCornerShape(8.dp)),
                    painter = painterResource(R.drawable.image_search),
                    contentDescription = "Search",
                    tint = Color.Unspecified,
                )

                DefaultText(
                    text = "일상 수어",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
            }
            OutlinedTextField(
                value = state.keyword,
                onValueChange = {
                    onEventSent(SearchContract.Event.OnChangeKeyword(it))
                },
                label = {
                    DefaultText(text = "검색어를 입력하세요")
                },
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                trailingIcon = {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        Icon(
                            modifier =
                                Modifier.clickable {
                                    onEventSent(SearchContract.Event.OnClickSearch)
                                },
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                        )
                        Icon(
                            modifier =
                                Modifier.clickable {
                                    onEventSent(SearchContract.Event.ShowVideoProcessDialog)
                                },
                            imageVector = IconPack.Inbox,
                            contentDescription = "Search",
                        )
                    }
                },
            )

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 30.dp),
            ) {
                when (state.uiState) {
                    UiState.NothingResult -> {
                        item {
                            EmptyContent(
                                text = "검색 결과가 없습니다.",
                            )
                        }
                    }

                    UiState.Main -> {
                        item {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                AdBanner(
                                    modifier = Modifier.fillMaxWidth(),
                                    title = "사단법인 함께하는 사랑밭&청각장애인생애지원센터가 함께하는 2024년 청각장애인 의료비 지원사업 『인공와우수술비 및 재활치료비 지원』안내",
                                    description = "사단법인 함께하는 사랑밭과 청각장애인생애지원센터는 2년 연속 상호협력하여 경제적으로 어려움을 겪는 청각장애인에게 인공달팽이관 수술 및 재활치료비를 지원합니다.",
                                    imageUrl = "https://api.lifeplanhd.kr/resources/image/V02c0HvZuD4Z0b9MfO2MFtsgSwz2.png",
                                    readCount = "1,000",
                                    onClick = {
                                        onNavigationRequested(SearchContract.Effect.Navigation.Banner)
                                    },
                                )
                                TodaySignContent()
                            }
                        }
                    }
                    UiState.SearchResult -> {
                        itemsIndexed(
                            items = state.searchResult,
                            key = { _, item -> item.id },
                        ) { index, item ->
                            val title = if (index == 0 && item.keyword == state.keyword) "[공식 수어]" else "[관련 수어]"

                            SearchResultContent(
                                title = title,
                                description = item.keyword,
                                videoUrl = item.videoUrl,
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                        }
                    }

                    UiState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth().height(320.dp),
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.align(Alignment.Center).size(50.dp),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun SearchScreenPreview() {
    SearchScreen(
        state = SearchContract.State(),
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {},
    )
}
