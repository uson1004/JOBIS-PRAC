package com.example.jobis_compose_prac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jobis_compose_prac.ui.theme.Grayscale30
import com.example.jobis_compose_prac.ui.theme.Grayscale50
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
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(modifier) {
            BackStackRow()
            LoginJOBISElement()
            InputLayoutColumns()
            ForgetButtonElement()
        }

        JOBISLoginElement(modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun BackStackRow(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Spacer(modifier = modifier.width(14.dp))
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(R.drawable.arrow_back),
                contentDescription = null,
                modifier = modifier.size(24.dp),
            )
        }
    }
}

@Composable
fun LoginJOBISElement(
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        Spacer(modifier = modifier.width(24.dp))
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputLayoutColumns(
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var showPassword by remember { mutableStateOf(false) }

    Column(modifier.fillMaxWidth()) {
        Column(modifier.padding(vertical = 12.dp, horizontal = 24.dp)) {
            Text(
                text = "이메일",
                style = TextStyle(fontSize = 14.sp),
                modifier = modifier.padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                singleLine = true,
                placeholder = {
                    Row(modifier = modifier.fillMaxWidth()) {
                        Text(
                            text = "example",
                            textAlign = TextAlign.Start,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = modifier.width(200.dp))
                        Text(
                            text = "@dsm.hs.kr",
                            textAlign = TextAlign.End,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                },
                modifier = modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Grayscale30,
                    unfocusedBorderColor = Grayscale30,
                    containerColor = Grayscale30,
                    focusedPlaceholderColor = Grayscale60,
                    unfocusedPlaceholderColor = Grayscale60,
                )
            )
        }
        Column(modifier.padding(vertical = 12.dp, horizontal = 24.dp)) {
            Text(
                text = "비밀번호",
                style = TextStyle(fontSize = 14.sp),
                modifier = modifier.padding(bottom = 8.dp)
            )
            OutlinedTextField(
                modifier = modifier.fillMaxWidth(),
                value = password,
                onValueChange = {
                    password = it
                },
                singleLine = true,
                placeholder = {
                    Text(
                        text = "비밀번호를 입력해주세요.",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold
                        ),
                    )
                },
                visualTransformation =
                if (showPassword)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                trailingIcon = {
                    if (showPassword) {
                        IconButton(onClick = { showPassword = false }) {
                            Icon(
                                painter = painterResource(id = R.drawable.eye_open),
                                contentDescription = null,
                                modifier = modifier.size(24.dp),
                            )
                        }
                    } else {
                        IconButton(onClick = { showPassword = true }) {
                            Icon(
                                painter = painterResource(id = R.drawable.eye_close),
                                contentDescription = null,
                                modifier = modifier.size(24.dp),
                            )
                        }
                    }
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Grayscale30,
                    unfocusedBorderColor = Grayscale30,
                    containerColor = Grayscale30,
                    focusedPlaceholderColor = Grayscale60,
                    unfocusedPlaceholderColor = Grayscale60
                ),
            )
        }
    }
}

@Composable
fun ForgetButtonElement(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {},
            modifier = modifier
                .padding(horizontal = 74.5.dp, vertical = 8.dp)
                .align(Alignment.Center)
                .height(48.dp),
            shape = RoundedCornerShape(32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Grayscale30,
                contentColor = Color.Black,
            ),
        ) {
            Icon(
                painter = painterResource(R.drawable.unlock_password),
                contentDescription = null,
                modifier = modifier
                    .size(24.dp)
                    .padding(end = 4.dp)
            )
            Text(text = "비밀번호를 잊으셨나요?")
        }
    }
}

@Composable
fun JOBISLoginElement(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Button(
            onClick = {},
            modifier = modifier
                .padding(horizontal = 24.dp, vertical = 12.dp)
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary20,
                contentColor = Color.White,
                disabledContainerColor = Grayscale50,
                disabledContentColor = Color.White
            )
        ) {
            Text(
                text = "로그인",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
            Icon(
                painter = painterResource(R.drawable.arrow_right),
                contentDescription = null,
                modifier = modifier
                    .padding(start = 8.dp)
                    .size(24.dp)
            )
        }
    }
}