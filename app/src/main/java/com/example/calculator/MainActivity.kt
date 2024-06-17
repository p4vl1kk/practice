package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.ui.SettingsDialog
import com.example.calculator.ui.theme.CalculatorTheme

//todo вибрация

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val calculatorViewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            val isd = isSystemInDarkTheme()
            var isDarkTheme by remember { mutableStateOf(isd) } //todo isDarkTheme по-умолчанию брать из настроек системы: isSystemInDarkTheme()
            var showDialog by remember { mutableStateOf(false) }

            CalculatorTheme(darkTheme = isDarkTheme) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {

                        Spacer(modifier = Modifier.height(16.dp))

                        //todo форматирование аргументов
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                            Button(
                                onClick = { showDialog = true },
                                //todo вынести размеры в отдельный объект
                                //todo нет смысла указывать 0.dp
                                modifier = Modifier.padding(start = 8.dp, top = 0.dp, bottom = 0.dp, end = 16.dp)
                            ) {
                                Text(text = "Settings") //todo локализация строк
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Calculator(
                            modifier = Modifier.weight(1f),
                            viewModel = calculatorViewModel
                        )
                    }

                    if (showDialog) {
                        //todo запоминать значение isDarkTheme в настройках
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
