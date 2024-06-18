package com.example.calculator

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

class CalculatorViewModel : ViewModel() {

    private val _equationText = MutableLiveData("")
    val equationText: LiveData<String> = _equationText

    private val _resultText = MutableLiveData("0")
    val resultText: LiveData<String> = _resultText

    private val maxInputLength = 15

    fun onButtonClick(btn: String) {
        Log.i("CalculatorViewModel", "Button clicked: $btn")

        _equationText.value?.let {
            when (btn) {
                "AC" -> clearAll()
                "C" -> deleteLastCharacter()
                "=" -> evaluateResult()
                else -> appendCharacter(btn)
            }
        }
    }

    private fun clearAll() {
        Log.i("CalculatorViewModel", "Clearing all")
        _equationText.value = ""
        _resultText.value = "0"
    }

    private fun deleteLastCharacter() {
        Log.i("CalculatorViewModel", "Deleting last character")
        _equationText.value?.let {
            if (it.isNotEmpty()) {
                _equationText.value = it.substring(0, it.length - 1)
            }
        }
    }

    private fun evaluateResult() {
        Log.i("CalculatorViewModel", "Calculating equation")
        _equationText.value?.let {
            try {
                val result = calculateResult(it)
                _equationText.value = result
                _resultText.value = result
                Log.i("CalculatorViewModel", "Calculation result: ${_resultText.value}")
            } catch (e: Exception) {
                Log.e("CalculatorViewModel", "Error calculating equation", e)
            }
        }
    }

    private fun appendCharacter(btn: String) {
        Log.i("CalculatorViewModel", "Appending character: $btn")
        _equationText.value?.let {
            if (it.length < maxInputLength) {
                _equationText.value = it + btn
                try {
                    _resultText.value = calculateResult(_equationText.value.toString())
                } catch (e: Exception) {
                    Log.e("CalculatorViewModel", "Error calculating equation", e)
                }
            }
        }
    }

    private fun calculateResult(equation: String): String {
        val context: Context = Context.enter()
        context.optimizationLevel = -1
        val scriptable: Scriptable = context.initStandardObjects()
        var finalResult = context.evaluateString(scriptable, equation, "Javascript", 1, null).toString()
        if (finalResult.endsWith(".0"))
            finalResult = finalResult.replace(".0", "")
        return finalResult
    }
}