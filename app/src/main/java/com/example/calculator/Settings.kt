package com.example.calculator.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculator.R
import com.example.calculator.ThemePreference
import com.example.calculator.ui.theme.CalculatorTheme

@Composable
fun SettingsDialog(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(text = stringResource(R.string.settings))
        },
        text = {
            Column {
                Text(text = stringResource(R.string.choose_theme))
                Switch(
                    checked = isDarkTheme,
                    onCheckedChange = {
                        onThemeChange(it)
                        // Сохраняем выбранную тему
                        ThemePreference.setTheme(context, it)
                    }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = stringResource(R.string.okay))
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsDialog() {
    CalculatorTheme {
        SettingsDialog(
            isDarkTheme = false,
            onThemeChange = {},
            onDismiss = {}
        )
    }
}
