package com.example.signlanghub.ui.search.screen

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.example.signlanghub.ui.common.composable.banner.AdBanner
import com.example.signlanghub.ui.common.composable.text.DefaultText
import com.example.signlanghub.ui.common.composable.topbar.BasicAppbar
import com.example.signlanghub.ui.common.icon.MyIconPack
import com.example.signlanghub.ui.common.icon.myiconpack.Inbox
import com.example.signlanghub.ui.search.SearchContract
import com.example.signlanghub.ui.search.content.SearchResultContent
import com.example.signlanghub.ui.search.content.TodaySignContent
import kotlinx.coroutines.flow.Flow

@Composable
fun SearchScreen(
    state: SearchContract.State,
    effectFlow: Flow<SearchContract.Effect>?,
    onEventSent: (event: SearchContract.Event) -> Unit,
    onNavigationRequested: (SearchContract.Effect.Navigation) -> Unit,
) {
    val context = LocalContext.current
    LaunchedEffect(true) {
        effectFlow?.collect {
            when (it) {
                SearchContract.Effect.ShowErrorToast -> {
                    Toast.makeText(context, "검색 결과를 가져오는 중 오류가 발생했습니다.", Toast.LENGTH_SHORT,).show()
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
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        Icon(
                            modifier = Modifier.clickable {
                                onEventSent(SearchContract.Event.OnClickSearch)
                            },
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                        )
                        Icon(
                            modifier = Modifier.clickable { },
                            imageVector = MyIconPack.Inbox,
                            contentDescription = "Search",
                        )
                    }
                },
            )

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
            ) {

                if (state.searchResult.isEmpty()) {
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            AdBanner(
                                modifier = Modifier.fillMaxWidth(),
                                title = "사단법인 함께하는 사랑밭&청각장애인생애지원센터가 함께하는 2023년 청각장애인 의료비 지원사업 『인공와우수술비 및 재활치료비 지원』안내",
                                description = "청각장애인생애지원센터는 '함께하는 사랑밭'과 함께 저소득 청각장애인을 대상으로 인공달팽이관 수술비, 언어재활치료비 등을 지원합니다. \n이와 관련하여 지원을 받고자 하는 회원님들은 신청서 양식을 작성 후 접수하여 주시기 바랍니다.",
                                imageUrl = "https://api.lifeplanhd.kr/resources/image/JfQct53OjfF020plbv04bJQSgbd2.jpg",
                                readCount = "100",
                            )
                            TodaySignContent()
                        }

                    }
                } else {
                    itemsIndexed(
                        items = state.searchResult,
                        key = { _, item -> item.id },
                    ) { index, item ->
                        val title = if (index == 0) "[공식 수어]" else "[관련 수어]"
                        SearchResultContent(
                            title = title,
                            description = item.keyword,
                            videoUrl = item.videoUrl,
                        )
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
