package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.ui.SettingsDialog
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val calculatorViewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }
            var showDialog by remember { mutableStateOf(false) }

            CalculatorTheme(darkTheme = isDarkTheme) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)) {

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                            Button(
                                onClick = { showDialog = true },
                                modifier = Modifier.padding(start = 8.dp, top = 0.dp, bottom = 0.dp, end = 16.dp)
                            ) {
                                Text(text = "Settings")
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Calculator(
                            modifier = Modifier.weight(1f),
                            viewModel = calculatorViewModel
                        )
                    }

                    if (showDialog) {
                        SettingsDialog(
                            isDarkTheme = isDarkTheme,
                            onThemeChange = { isDarkTheme = it },
                            onDismiss = { showDialog = false }
                        )
                    }
                }
            }
        }
    }
}
