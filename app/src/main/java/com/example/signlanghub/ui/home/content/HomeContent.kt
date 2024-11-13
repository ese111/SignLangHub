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
    onNavigationRequested: (HomeContract.Effect.Navigation) -> Unit,
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "SignLangHub",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal,
                )

                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                )
            }
        },
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            MenuCard(
                image = {
                    Icon(
                        modifier = Modifier.size(132.dp),
                        painter = painterResource(id = R.drawable.image_location),
                        contentDescription = "위치기반 수어통역 서비스",
                        tint = Color.Unspecified,
                    )
                },
                text = "위치기반\n수어통역 서비스",
                onClick = {
                    onNavigationRequested(HomeContract.Effect.Navigation.Localization)
                },
            )

            MenuCard(
                image = {
                    Icon(
                        modifier = Modifier.size(132.dp),
                        painter = painterResource(id = R.drawable.image_translation),
                        contentDescription = "한국어 한국수어 문법 번역 서비스",
                        tint = Color.Unspecified,
                    )
                },
                text = "한국어 한국수어\n문법 번역 서비스",
                onClick = {
                    onNavigationRequested(HomeContract.Effect.Navigation.Translation)
                },
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
                            tint = Color.Unspecified,
                        )
                        Icon(
                            modifier = Modifier.weight(1f).fillMaxHeight(),
                            painter = painterResource(id = R.drawable.image_total),
                            contentDescription = "한국수어사전 검색 서비스",
                            tint = Color.Unspecified,
                        )
                    }
                },
                text = "한국수어사전\n검색 서비스",
                onClick = {
                    onNavigationRequested(HomeContract.Effect.Navigation.Search)
                },
            )

            AdBanner(
                title = "사단법인 함께하는 사랑밭&청각장애인생애지원센터가 함께하는 2023년 청각장애인 의료비 지원사업 『인공와우수술비 및 재활치료비 지원』안내",
                description = "청각장애인생애지원센터는 '함께하는 사랑밭'과 함께 저소득 청각장애인을 대상으로 인공달팽이관 수술비, 언어재활치료비 등을 지원합니다. \n이와 관련하여 지원을 받고자 하는 회원님들은 신청서 양식을 작성 후 접수하여 주시기 바랍니다.",
                imageUrl = "https://api.lifeplanhd.kr/resources/image/JfQct53OjfF020plbv04bJQSgbd2.jpg",
                readCount = "100",
            )
        }
    }
}

@Preview
@Composable
private fun MainContentPreview() {
    HomeContent(
        state = HomeContract.State(),
        onEvent = {},
        onNavigationRequested = {},
    )
}
