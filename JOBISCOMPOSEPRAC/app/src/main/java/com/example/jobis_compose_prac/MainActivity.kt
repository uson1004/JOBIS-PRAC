package com.example.jobis_compose_prac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jobis_compose_prac.ui.theme.Grayscale60
import com.example.jobis_compose_prac.ui.theme.JOBISCOMPOSEPRACTheme
import com.example.jobis_compose_prac.ui.theme.Primary20

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JOBISCOMPOSEPRACTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    Column(modifier) {
        BackStackRow()
        LoginJOBISElement()
        InputLayoutColumns()
    }
}

@Composable
fun BackStackRow(
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 20.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.arrow),
            contentDescription = null,
            modifier = modifier.size(28.dp)
        )
    }
}

@Composable
fun LoginJOBISElement(
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 24.dp)
    ) {
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = Primary20)) {
                    append("JOBIS")
                }
                append("에서 로그인하기")
            },
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun InputLayoutColumns(
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier.fillMaxWidth()) {
        Column(modifier.padding(vertical = 12.dp, horizontal = 24.dp)) {
            Text(
                text = "이메일",
                style = TextStyle(fontSize = 14.sp),
                modifier = modifier.padding(bottom = 4.dp)
            )
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                singleLine = true,
                placeholder = {
                    Text(
                        text = "example"
                    )
                    Text(
                        text = "@dsm.hs.kr",
                        textAlign = TextAlign.End
                    )
                },
                modifier = modifier.fillMaxWidth()
            )
        }
        Column(modifier.padding(vertical = 12.dp, horizontal = 24.dp)) {
            Text(
                text = "비밀번호",
                style = TextStyle(fontSize = 14.sp),
                modifier = modifier.padding(bottom = 4.dp)
            )
            OutlinedTextField(
                modifier = modifier.fillMaxWidth(),
                value = password,
                onValueChange = {
                    password = it
                },
                singleLine = true,
                placeholder = {
                    Text(text = "비밀번호를 입력해주세요.")
                },
                trailingIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.eye_close),
                            contentDescription = null,
                            modifier = modifier.size(24.dp)
                        )
                    }
                }
            )
        }
    }
}