package com.example.calculator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class CalculatorButton(val symbol: String) {
    CLEAR("C"), OPEN_PARENTHESIS("("), CLOSE_PARENTHESIS(")"), DIVIDE("/"),
    SEVEN("7"), EIGHT("8"), NINE("9"), MULTIPLY("*"),
    FOUR("4"), FIVE("5"), SIX("6"), ADD("+"),
    ONE("1"), TWO("2"), THREE("3"), SUBTRACT("-"),
    ALL_CLEAR("AC"), ZERO("0"), DECIMAL(","), EQUALS("=")
}

@Composable
fun Calculator(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel
) {
    val equationText = viewModel.equationText.observeAsState()
    val resultText = viewModel.resultText.observeAsState()

    Box(modifier = modifier) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = equationText.value ?: "",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 30.sp,
                    textAlign = TextAlign.End
                ),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = resultText.value ?: "",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 60.sp,
                    textAlign = TextAlign.End
                ),
                maxLines = 2,
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.fillMaxHeight()
            ) {
                items(CalculatorButton.entries) { button ->
                    CalculatorButton(
                        btn = button.symbol,
                        onClick = { viewModel.onButtonClick(button.symbol) }
                    )
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(btn: String, onClick: () -> Unit) {
    val haptic = LocalHapticFeedback.current
    Box(modifier = Modifier.padding(10.dp)) {
        FloatingActionButton(
            onClick = {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                onClick()
            },
            modifier = Modifier.size(80.dp),
            contentColor = Color.White,
            containerColor = getColor(btn)
        ) {
            Text(text = btn, fontSize = 25.sp, fontWeight = FontWeight.Bold)
        }
    }
}

fun getColor(btn: String): Color {
    return when (btn) {
        "C", "AC" -> Color(0xFFF44336)
        "(", ")" -> Color.Gray
        "/", "*", "-", "+", "=" -> Color(0xFFFF9800)
        else -> Color(0xFF00C8C9)
    }
}