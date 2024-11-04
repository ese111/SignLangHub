package com.example.signlanghub.ui.login.content

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.signlanghub.ui.theme.InterFamily

@Composable
internal fun LoginContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Text(
            text = "SignLangHub",
            fontSize = 36.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = InterFamily
        )

        Card(
            shape = RoundedCornerShape(24.dp),
            border = BorderStroke(
                2.dp,
                color = Color.Gray
            )
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    label = {
                        Text(
                            text = "아이디",
                        )
                    }
                )

                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    label = {
                        Text(
                            text = "비밀번호"
                        )
                    }
                )

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    onClick = { },
                ) {
                    Text(
                        text = "Sign In",
                        fontFamily = InterFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
        }
    }

}

@Preview
@Composable
private fun LoginContentPreview() {
    LoginContent(

    )
}