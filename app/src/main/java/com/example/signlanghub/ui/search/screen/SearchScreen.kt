package com.example.signlanghub.ui.search.screen

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.signlanghub.R
import com.example.signlanghub.ui.common.composable.VideoProcessChoiceDialog
import com.example.signlanghub.ui.common.composable.banner.AdBanner
import com.example.signlanghub.ui.common.composable.text.DefaultText
import com.example.signlanghub.ui.common.composable.topbar.BasicAppbar
import com.example.signlanghub.ui.common.icon.IconPack
import com.example.signlanghub.ui.common.icon.myiconpack.Inbox
import com.example.signlanghub.ui.search.SearchContract
import com.example.signlanghub.ui.search.UiState
import com.example.signlanghub.ui.search.content.EmptyContent
import com.example.signlanghub.ui.search.content.SearchResultContent
import com.example.signlanghub.ui.search.content.TodaySignContent
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

@Composable
fun SearchScreen(
    state: SearchContract.State,
    effectFlow: Flow<SearchContract.Effect>?,
    onEventSent: (event: SearchContract.Event) -> Unit,
    onNavigationRequested: (SearchContract.Effect.Navigation) -> Unit,
) {
    val context = LocalContext.current

    val pickImageLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
        ) { result: Uri? ->
            result?.let { uri ->
                Timber.i("createImageFile Selected Image Uri : $uri")
            }
        }

    val takePictureLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture(),
            onResult = { isSuccess ->
                Timber.i("Take Picture Result : $isSuccess")
            },
        )

    LaunchedEffect(true) {
        effectFlow?.collect {
            when (it) {
                SearchContract.Effect.ShowErrorToast -> {
                    Toast.makeText(context, "검색 결과를 가져오는 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            BasicAppbar(
                title = "검색하기",
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxWidth().padding(horizontal = 20.dp),
        ) {
            if (state.videoProcessDialogVisible) {
                VideoProcessChoiceDialog(
                    onDismissRequest = {
                        onEventSent(SearchContract.Event.DismissVideoProcessBottomSheet)
                    },
                    goToGallery = {
                    },
                    takePicture = {
                    },
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(18.dp),
            ) {
                Icon(
                    modifier = Modifier.size(80.dp),
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
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
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
                                    onEventSent(SearchContract.Event.ShowVideoProcessBottomSheet)
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
