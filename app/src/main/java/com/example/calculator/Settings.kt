package com.example.calculator.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsDialog(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Settings") },
        text = {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Dark Theme")
                    Spacer(modifier = Modifier.weight(1f))
                    Switch(
                        checked = isDarkTheme,
                        onCheckedChange = { onThemeChange(it) }
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Confirm")
            }
        }
    )
}