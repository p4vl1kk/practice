package com.example.calculator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

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

            ConstraintLayout(
                modifier = Modifier.fillMaxHeight(),
                constraintSet = decoupledConstraints()
            ) {
                CalculatorButton(
                    button = CalculatorButton.CLEAR,
                    onClick = { viewModel.onButtonClick(CalculatorButton.CLEAR) },
                    modifier = Modifier.layoutId(CalculatorButton.CLEAR.symbol)
                )
                CalculatorButton(
                    button = CalculatorButton.OPEN_PARENTHESIS,
                    onClick = { viewModel.onButtonClick(CalculatorButton.OPEN_PARENTHESIS) },
                    modifier = Modifier.layoutId(CalculatorButton.OPEN_PARENTHESIS.symbol)
                )
                CalculatorButton(
                    button = CalculatorButton.CLOSE_PARENTHESIS,
                    onClick = { viewModel.onButtonClick(CalculatorButton.CLOSE_PARENTHESIS) },
                    modifier = Modifier.layoutId(CalculatorButton.CLOSE_PARENTHESIS.symbol)
                )
                CalculatorButton(
                    button = CalculatorButton.DIVIDE,
                    onClick = { viewModel.onButtonClick(CalculatorButton.DIVIDE) },
                    modifier = Modifier.layoutId(CalculatorButton.DIVIDE.symbol)
                )
                CalculatorButton(
                    button = CalculatorButton.SEVEN,
                    onClick = { viewModel.onButtonClick(CalculatorButton.SEVEN) },
                    modifier = Modifier.layoutId(CalculatorButton.SEVEN.symbol)
                )
                CalculatorButton(
                    button = CalculatorButton.EIGHT,
                    onClick = { viewModel.onButtonClick(CalculatorButton.EIGHT) },
                    modifier = Modifier.layoutId(CalculatorButton.EIGHT.symbol)
                )
                CalculatorButton(
                    button = CalculatorButton.NINE,
                    onClick = { viewModel.onButtonClick(CalculatorButton.NINE) },
                    modifier = Modifier.layoutId(CalculatorButton.NINE.symbol)
                )
                CalculatorButton(
                    button = CalculatorButton.MULTIPLY,
                    onClick = { viewModel.onButtonClick(CalculatorButton.MULTIPLY) },
                    modifier = Modifier.layoutId(CalculatorButton.MULTIPLY.symbol)
                )
                CalculatorButton(
                    button = CalculatorButton.FOUR,
                    onClick = { viewModel.onButtonClick(CalculatorButton.FOUR) },
                    modifier = Modifier.layoutId(CalculatorButton.FOUR.symbol)
                )
                CalculatorButton(
                    button = CalculatorButton.FIVE,
                    onClick = { viewModel.onButtonClick(CalculatorButton.FIVE) },
                    modifier = Modifier.layoutId(CalculatorButton.FIVE.symbol)
                )
                CalculatorButton(
                    button = CalculatorButton.SIX,
                    onClick = { viewModel.onButtonClick(CalculatorButton.SIX) },
                    modifier = Modifier.layoutId(CalculatorButton.SIX.symbol)
                )
                CalculatorButton(
                    button = CalculatorButton.ADD,
                    onClick = { viewModel.onButtonClick(CalculatorButton.ADD) },
                    modifier = Modifier.layoutId(CalculatorButton.ADD.symbol)
                )
                CalculatorButton(
                    button = CalculatorButton.ONE,
                    onClick = { viewModel.onButtonClick(CalculatorButton.ONE) },
                    modifier = Modifier.layoutId(CalculatorButton.ONE.symbol)
                )
                CalculatorButton(
                    button = CalculatorButton.TWO,
                    onClick = { viewModel.onButtonClick(CalculatorButton.TWO) },
                    modifier = Modifier.layoutId(CalculatorButton.TWO.symbol)
                )
                CalculatorButton(
                    button = CalculatorButton.THREE,
                    onClick = { viewModel.onButtonClick(CalculatorButton.THREE) },
                    modifier = Modifier.layoutId(CalculatorButton.THREE.symbol)
                )
                CalculatorButton(
                    button = CalculatorButton.SUBTRACT,
                    onClick = { viewModel.onButtonClick(CalculatorButton.SUBTRACT) },
                    modifier = Modifier.layoutId(CalculatorButton.SUBTRACT.symbol)
                )
                CalculatorButton(
                    button = CalculatorButton.ALL_CLEAR,
                    onClick = { viewModel.onButtonClick(CalculatorButton.ALL_CLEAR) },
                    modifier = Modifier.layoutId(CalculatorButton.ALL_CLEAR.symbol)
                )
                CalculatorButton(
                    button = CalculatorButton.ZERO,
                    onClick = { viewModel.onButtonClick(CalculatorButton.ZERO) },
                    modifier = Modifier.layoutId(CalculatorButton.ZERO.symbol)
                )
                CalculatorButton(
                    button = CalculatorButton.DECIMAL,
                    onClick = { viewModel.onButtonClick(CalculatorButton.DECIMAL) },
                    modifier = Modifier.layoutId(CalculatorButton.DECIMAL.symbol)
                )
                CalculatorButton(
                    button = CalculatorButton.EQUALS,
                    onClick = { viewModel.onButtonClick(CalculatorButton.EQUALS) },
                    modifier = Modifier.layoutId(CalculatorButton.EQUALS.symbol)
                )
            }
        }
    }
}

private fun decoupledConstraints(): ConstraintSet {
    return ConstraintSet {
        val clearButton = createRefFor(CalculatorButton.CLEAR.symbol)
        val openParenthesisButton = createRefFor(CalculatorButton.OPEN_PARENTHESIS.symbol)
        val closeParenthesisButton = createRefFor(CalculatorButton.CLOSE_PARENTHESIS.symbol)
        val divideButton = createRefFor(CalculatorButton.DIVIDE.symbol)

        val sevenButton = createRefFor(CalculatorButton.SEVEN.symbol)
        val eightButton = createRefFor(CalculatorButton.EIGHT.symbol)
        val nineButton = createRefFor(CalculatorButton.NINE.symbol)
        val multiplyButton = createRefFor(CalculatorButton.MULTIPLY.symbol)

        val fourButton = createRefFor(CalculatorButton.FOUR.symbol)
        val fiveButton = createRefFor(CalculatorButton.FIVE.symbol)
        val sixButton = createRefFor(CalculatorButton.SIX.symbol)
        val addButton = createRefFor(CalculatorButton.ADD.symbol)

        val oneButton = createRefFor(CalculatorButton.ONE.symbol)
        val twoButton = createRefFor(CalculatorButton.TWO.symbol)
        val threeButton = createRefFor(CalculatorButton.THREE.symbol)
        val subtractButton = createRefFor(CalculatorButton.SUBTRACT.symbol)

        val allClearButton = createRefFor(CalculatorButton.ALL_CLEAR.symbol)
        val zeroButton = createRefFor(CalculatorButton.ZERO.symbol)
        val decimalButton = createRefFor(CalculatorButton.DECIMAL.symbol)
        val equalsButton = createRefFor(CalculatorButton.EQUALS.symbol)

        constrain(clearButton) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        }
        constrain(openParenthesisButton) {
            top.linkTo(clearButton.top)
            start.linkTo(clearButton.end)
        }
        constrain(closeParenthesisButton) {
            top.linkTo(openParenthesisButton.top)
            start.linkTo(openParenthesisButton.end)
        }
        constrain(divideButton) {
            top.linkTo(closeParenthesisButton.top)
            start.linkTo(closeParenthesisButton.end)
        }

        constrain(sevenButton) {
            top.linkTo(clearButton.bottom)
            start.linkTo(parent.start)
        }
        constrain(eightButton) {
            top.linkTo(sevenButton.top)
            start.linkTo(sevenButton.end)
        }
        constrain(nineButton) {
            top.linkTo(eightButton.top)
            start.linkTo(eightButton.end)
        }
        constrain(multiplyButton) {
            top.linkTo(nineButton.top)
            start.linkTo(nineButton.end)
        }

        constrain(fourButton) {
            top.linkTo(sevenButton.bottom)
            start.linkTo(parent.start)
        }
        constrain(fiveButton) {
            top.linkTo(fourButton.top)
            start.linkTo(fourButton.end)
        }
        constrain(sixButton) {
            top.linkTo(fiveButton.top)
            start.linkTo(fiveButton.end)
        }
        constrain(addButton) {
            top.linkTo(sixButton.top)
            start.linkTo(sixButton.end)
        }

        constrain(oneButton) {
            top.linkTo(fourButton.bottom)
            start.linkTo(parent.start)
        }
        constrain(twoButton) {
            top.linkTo(oneButton.top)
            start.linkTo(oneButton.end)
        }
        constrain(threeButton) {
            top.linkTo(twoButton.top)
            start.linkTo(twoButton.end)
        }
        constrain(subtractButton) {
            top.linkTo(threeButton.top)
            start.linkTo(threeButton.end)
        }

        constrain(allClearButton) {
            top.linkTo(oneButton.bottom)
            start.linkTo(parent.start)
        }
        constrain(zeroButton) {
            top.linkTo(allClearButton.top)
            start.linkTo(allClearButton.end)
        }
        constrain(decimalButton) {
            top.linkTo(zeroButton.top)
            start.linkTo(zeroButton.end)
        }
        constrain(equalsButton) {
            top.linkTo(decimalButton.top)
            start.linkTo(decimalButton.end)
        }
    }
}

@Composable
fun CalculatorButton(button: CalculatorButton, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val haptic = LocalHapticFeedback.current
    Box(modifier = modifier.padding(10.dp)) {
        FloatingActionButton(
            onClick = {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                onClick()
            },
            modifier = Modifier.size(80.dp),
            contentColor = Color.White,
            containerColor = getColor(button)
        ) {
            Text(text = button.symbol, fontSize = 25.sp, fontWeight = FontWeight.Bold)
        }
    }
}

fun getColor(button: CalculatorButton): Color {
    return when (button) {
        CalculatorButton.CLEAR, CalculatorButton.ALL_CLEAR -> Color(0xFFF44336)
        CalculatorButton.OPEN_PARENTHESIS, CalculatorButton.CLOSE_PARENTHESIS -> Color.Gray
        CalculatorButton.DIVIDE, CalculatorButton.MULTIPLY, CalculatorButton.SUBTRACT, CalculatorButton.ADD, CalculatorButton.EQUALS -> Color(0xFFFF9800)
        else -> Color(0xFF00C8C9)
    }
}
