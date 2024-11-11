package com.example.signlanghub.ui.home.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.example.signlanghub.ui.home.HomeContract
import com.example.signlanghub.ui.main.composable.MenuCard

@Composable
internal fun HomeContent(
    modifier: Modifier = Modifier,
    state: HomeContract.State,
    onEvent: (HomeContract.Event) -> Unit,
    onNavigationRequested: (HomeContract.Effect.Navigation) -> Unit
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "SignLangHub",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal
                )

                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                )
            }
        },
        bottomBar = {
            AdBanner(
                title = "광고",
                description = "광고 설명",
                image = Icons.Default.AddCircle,
                readCount = "100"
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            MenuCard(
                image = {
                    Icon(
                        modifier = Modifier.size(132.dp),
                        painter = painterResource(id = R.drawable.image_location),
                        contentDescription = "위치기반 수어통역 서비스",
                        tint = Color.Unspecified
                    )
                },
                text = "위치기반\n수어통역 서비스",
                onClick = {
                     onNavigationRequested(HomeContract.Effect.Navigation.Localization)
                }
            )

            MenuCard(
                image = {
                    Icon(
                        modifier = Modifier.size(132.dp),
                        painter = painterResource(id = R.drawable.image_translation),
                        contentDescription = "한국어 한국수어 문법 번역 서비스",
                        tint = Color.Unspecified
                    )

                },
                text = "한국어 한국수어\n문법 번역 서비스",
                onClick = {
                     onNavigationRequested(HomeContract.Effect.Navigation.Translation)
                }
            )

            MenuCard(
                image = {
                    Row(
                        modifier = Modifier.size(132.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            modifier = Modifier.weight(1f).fillMaxHeight(),
                            painter = painterResource(id = R.drawable.image_word),
                            contentDescription = "한국수어사전 검색 서비스",
                            tint = Color.Unspecified
                        )
                        Icon(
                            modifier = Modifier.weight(1f).fillMaxHeight(),
                            painter = painterResource(id = R.drawable.image_total),
                            contentDescription = "한국수어사전 검색 서비스",
                            tint = Color.Unspecified
                        )
                    }
                },
                text = "한국수어사전\n검색 서비스",
                onClick = {
                    onNavigationRequested(HomeContract.Effect.Navigation.Search)
                }
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview
@Composable
private fun MainContentPreview() {
    HomeContent(
        state = HomeContract.State(),
        onEvent = {},
        onNavigationRequested = {}
    )
}