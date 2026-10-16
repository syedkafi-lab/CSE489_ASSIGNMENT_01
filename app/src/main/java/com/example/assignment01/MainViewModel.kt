package com.example.assignment01

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MainViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    companion object {
        const val KEY_INPUT_AMOUNT = "key_input_amount"
        private const val MAX_INPUT_LENGTH = 8
        val TAKA_NOTES = listOf(500, 100, 50, 20, 10, 5, 2, 1)
    }

    private val _inputAmount = MutableLiveData(savedStateHandle.get<String>(KEY_INPUT_AMOUNT) ?: "")
    val inputAmount: LiveData<String> = _inputAmount

    private val _noteCounts = MutableLiveData<Map<Int, Int>>()
    val noteCounts: LiveData<Map<Int, Int>> = _noteCounts

    init {
        updateNoteCounts(_inputAmount.value.orEmpty())
    }

    fun onDigitClicked(digit: String) {
        val currentInput = _inputAmount.value.orEmpty()

        // Prevent leading zero
        if (currentInput.isEmpty() && (digit == "0")) {
            return
        }

        // Limit maximum digits
        if (currentInput.length >= MAX_INPUT_LENGTH) {
            return
        }

        val newInput = currentInput + digit
        _inputAmount.value = newInput
        savedStateHandle[KEY_INPUT_AMOUNT] = newInput
        updateNoteCounts(newInput)
    }

    fun onClearClicked() {
        _inputAmount.value = ""
        savedStateHandle[KEY_INPUT_AMOUNT] = ""
        updateNoteCounts("")
    }

    fun restoreAmount(amount: String) {
        _inputAmount.value = amount
        savedStateHandle[KEY_INPUT_AMOUNT] = amount
        updateNoteCounts(amount)
    }

    private fun updateNoteCounts(input: String) {
        val totalAmount = input.toLongOrNull() ?: 0L
        _noteCounts.value = calculateChange(totalAmount)
    }

    fun calculateChange(amount: Long): Map<Int, Int> {
        var remaining = amount
        val result = mutableMapOf<Int, Int>()

        for (note in TAKA_NOTES) {
            val count = (remaining / note).toInt()
            result[note] = count
            remaining %= note
        }

        return result
    }
}
