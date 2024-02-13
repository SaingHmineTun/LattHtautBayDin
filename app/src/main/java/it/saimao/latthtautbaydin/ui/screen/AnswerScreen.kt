package it.saimao.latthtautbaydin.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.saimao.latthtautbaydin.ui.Utility

@Composable
fun AnswerScreen(question: Int, number: Int, navigateBack: () -> Unit) {

    val answer = Utility.getJsonData(LocalContext.current).answers.first {
        it.questionNo == question && it.answerNo == number
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = answer.answerResult, modifier = Modifier.padding(16.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navigateBack.invoke() }) {
            Text(text = "Start over")
        }

    }

    Log.d("Kham", answer.answerResult)

}

@Preview
@Composable
fun AnswerScreenPreview() {
    AnswerScreen(question = 1, number = 1, {})
}