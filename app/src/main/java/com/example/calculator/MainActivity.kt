package com.example.calculator

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.ui.SettingsDialog
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val calculatorViewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            var isDarkTheme by remember { mutableStateOf(ThemePreference.getTheme(context)) }
            var showDialog by remember { mutableStateOf(false) }

            CalculatorTheme(darkTheme = isDarkTheme) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    interactionSource = remember {
                                        MutableInteractionSource()
                                    },
                                    indication = rememberRipple(color = Color.Red),
                                    onClick = {
                                        HapticFeedBack().triggerHapticFeedback(this@MainActivity, 500)
                                    }
                                ),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Button(
                                onClick = { showDialog = true },
                                modifier = Modifier.padding(
                                    start = Padding.m,
                                    end = Padding.m
                                )
                            ) {
                                Text(text = stringResource(R.string.settings))
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

object Padding {
    val m = 8.dp
    val s = 4.dp
    val l = 16.dp
}
