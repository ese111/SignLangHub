package com.example.signlanghub.ui.search.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.signlanghub.ui.search.content.TodaySignContent
import kotlinx.coroutines.flow.Flow

@Composable
fun SearchScreen(
    state: SearchContract.State,
    effectFlow: Flow<SearchContract.Effect>?,
    onEventSent: (event: SearchContract.Event) -> Unit,
    onNavigationRequested: (SearchContract.Effect.Navigation) -> Unit
) {
    Scaffold(
        topBar = {
            BasicAppbar(
                title = "검색하기"
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxWidth().padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Icon(
                    modifier = Modifier.size(110.dp),
                    painter = painterResource(R.drawable.image_search),
                    contentDescription = "Search",
                    tint = Color.Unspecified
                )

                DefaultText(
                    text = "일상 수어",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
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
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            modifier = Modifier.clickable {  },
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                        )
                        Icon(
                            modifier = Modifier.clickable {  },
                            imageVector = MyIconPack.Inbox,
                            contentDescription = "Search",
                        )
                    }

                }
            )

            AdBanner(
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                title = "광고",
                description = "광고 설명",
                image = Icons.Default.AddCircle,
                readCount = "100"
            )

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    TodaySignContent()
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
        onNavigationRequested = {}
    )
}
