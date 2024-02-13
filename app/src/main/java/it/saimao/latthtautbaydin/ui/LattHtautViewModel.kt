package it.saimao.latthtautbaydin.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class LattHtautUiState(
    val question: Int = -1,
    val number: Int = -1,
    val answer: Int = -1
)

class LattHtautViewModel : ViewModel() {
    var _uiState = mutableStateOf(LattHtautUiState())

    fun updateQuestion(newQuestion: Int) {
        _uiState.value = _uiState.value.copy(question = newQuestion)
    }

    fun updateNumber(newLattHtaut: String) {
        _uiState.value = _uiState.value.copy(number = Utility.convertToEngNum(newLattHtaut))
    }

    fun updateAnswer(newAnswer: Int) {
        _uiState.value = _uiState.value.copy(answer = newAnswer)
    }

}