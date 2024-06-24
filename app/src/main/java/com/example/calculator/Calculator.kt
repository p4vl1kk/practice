package com.example.calculator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

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
    val progress = viewModel.progress.observeAsState(0)

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

            LinearProgressIndicator(progress = progress.value / 100f)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { viewModel.startCalculation() }) {
                    Text("Start Calculation")
                }
                Button(onClick = { viewModel.cancelCalculation() }) {
                    Text("Cancel Calculation")
                }
            }

            CalculatorButtons(
                modifier = Modifier.fillMaxWidth(),
                buttons = CalculatorButton.entries,
                onItemClick = { viewModel.onButtonClick(it) }
            )
        }
    }
}

@Composable
fun CalculatorButtons(
    modifier: Modifier = Modifier,
    buttons: List<CalculatorButton>,
    onItemClick: (CalculatorButton) -> Unit
) {
    ConstraintLayout(modifier = modifier) {
        val buttonRefs = buttons.map { createRef() }

        buttons.forEachIndexed { index, button ->
            val ref = buttonRefs[index]
            CalculatorButton(
                button = button,
                onClick = { onItemClick(button) },
                modifier = Modifier
                    .constrainAs(ref) {
                        val row = index / 4
                        val col = index % 4
                        top.linkTo(if (row == 0) parent.top else buttonRefs[index - 4].bottom, margin = 8.dp)
                        start.linkTo(if (col == 0) parent.start else buttonRefs[index - 1].end, margin = 8.dp)
                        if (col == 3) {
                            end.linkTo(parent.end, margin = 8.dp)
                        }
                        if (row == buttons.size / 4) {
                            bottom.linkTo(parent.bottom, margin = 8.dp)
                        }
                        width = Dimension.fillToConstraints
                    })
        }
    }
}

@Composable
fun CalculatorButton(button: CalculatorButton, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val haptic = LocalHapticFeedback.current
    FloatingActionButton(
        onClick = {
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            onClick()
        },
        modifier = modifier.size(80.dp),
        contentColor = Color.White,
        containerColor = getColor(button)
    ) {
        Text(text = button.symbol, fontSize = 25.sp, fontWeight = FontWeight.Bold)
    }
}

private fun getColor(button: CalculatorButton): Color {
    return when (button) {
        CalculatorButton.CLEAR, CalculatorButton.ALL_CLEAR -> Color(0xFFF44336)
        CalculatorButton.OPEN_PARENTHESIS, CalculatorButton.CLOSE_PARENTHESIS -> Color.Gray
        CalculatorButton.DIVIDE, CalculatorButton.MULTIPLY, CalculatorButton.SUBTRACT, CalculatorButton.ADD, CalculatorButton.EQUALS -> Color(0xFFFF9800)
        else -> Color(0xFF00C8C9)
    }
}
