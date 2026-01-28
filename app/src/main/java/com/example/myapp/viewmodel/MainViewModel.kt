package com.example.myapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapp.model.UiState

class MainViewModel : ViewModel() {

    // Күйді жаңарту үшін қолданылатын айнымалы [cite: 46, 50]
    private val _state = MutableLiveData<UiState>()
    val state: LiveData<UiState> get() = _state

    /**
     * Сома мен жеңілдікті есептеу функциясы [cite: 47]
     */
    fun calculate(amountStr: String, discountStr: String) {
        val amount = amountStr.toDoubleOrNull() // Мәтінді санға айналдыру [cite: 48]
        val discount = discountStr.toDoubleOrNull()

        // Валидация: Бос немесе теріс мәндерді тексеру [cite: 20, 49]
        if (amount == null || discount == null) {
            _state.value = UiState(errorText = "Мәндерді толтырыңыз", isValid = false)
            return
        }

        if (amount < 0 || discount < 0 || discount > 90) {
            _state.value = UiState(errorText = "Жеңілдік 0-90% аралығында болуы керек", isValid = false)
            return
        }

        // Бизнес-логика: Есептеу [cite: 19]
        val finalSum = amount * (1 - discount / 100)

        _state.value = UiState(
            resultText = "Қорытынды: $finalSum тенге",
            errorText = "",
            isValid = true
        )
    }
}