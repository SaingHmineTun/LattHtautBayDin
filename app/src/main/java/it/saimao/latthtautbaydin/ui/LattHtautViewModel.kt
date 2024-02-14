package it.saimao.latthtautbaydin.ui

import androidx.annotation.StringRes
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import it.saimao.latthtautbaydin.R

data class LattHtautUiState(
    val question: Int = -1,
    val number: Int = -1,
    val answer: Int = -1,
    @StringRes val appName: Int = R.string.select_question,
    val numberScreenTab: Int = 0
)

class LattHtautViewModel : ViewModel() {
    var uiState = mutableStateOf(LattHtautUiState())

    fun setAppName(@StringRes appName: Int) {
        uiState.value = uiState.value.copy(appName = appName)
    }

    fun updateNumberScreenTab(tab: Int) {
        uiState.value = uiState.value.copy(numberScreenTab = tab)
    }

    fun updateQuestion(newQuestion: Int) {
        uiState.value = uiState.value.copy(question = newQuestion)
    }

    fun updateNumber(newLattHtaut: Int) {
        uiState.value = uiState.value.copy(number = newLattHtaut)
    }

    fun updateAnswer(newAnswer: Int) {
        uiState.value = uiState.value.copy(answer = newAnswer)
    }

}