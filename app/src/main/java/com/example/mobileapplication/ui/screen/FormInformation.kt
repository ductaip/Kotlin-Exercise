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
import androidx.compose.ui.unit.sp
import com.example.mobileapplication.R
import com.example.mobileapplication.ui.theme.AppTheme
import org.w3c.dom.Text

@Composable
fun FormInformation(onBackToHome: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
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
            // Full Name Input
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Họ và tên") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Age Input
            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text("Tuổi") },
                modifier = Modifier.fillMaxWidth(),
                isError = isError
            )
            if (isError) {
                Text(
                    text = "Tuổi phải là một số hợp lệ",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Check Button
            Button(onClick = {
                val ageInt = age.toIntOrNull()
                if (ageInt == null) {
                    isError = true
                    category = ""
                } else {
                    isError = false
                    category = when {
                        ageInt > 65 -> "Người già"
                        ageInt in 6..65 -> "Người lớn"
                        ageInt in 2..5 -> "Trẻ em"
                        ageInt < 2 -> "Em bé"
                        else -> "Không xác định"
                    }
                }
            }) {
                Text("Kiểm tra")
            }
            Spacer(modifier = Modifier.height(16.dp))

             //Display Category
            if (category.isNotEmpty()) {
                Text(
                    text = "Danh mục: $category",
                    color = Color.Blue,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormInformationPreview() {
    AppTheme {
        FormInformation(onBackToHome = {})
    }
}