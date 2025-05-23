package com.example.mobileapplication.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobileapplication.R
import com.example.mobileapplication.ui.theme.AppTheme

@Composable
fun EmailInputScreen(onBackToHome: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var validationMessage by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackToHome) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back",
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                isError = isError
            )
            if (validationMessage.isNotEmpty()) {
                Text(
                    text = validationMessage,
                    color = if (isError) Color.Red else Color.Green,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                when {
                    email.isEmpty() -> {
                        validationMessage = "Email không hợp lệ"
                        isError = true
                    }
                    !email.contains("@") -> {
                        validationMessage = "Email không đúng định dạng"
                        isError = true
                    }
                    else -> {
                        validationMessage = "Bạn đã nhập email hợp lệ"
                        isError = false
                    }
                }
            }) {
                Text("Kiểm tra")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmailInputScreenPreview() {
    AppTheme {
        EmailInputScreen(onBackToHome = {})
    }
}