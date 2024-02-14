package it.saimao.latthtautbaydin.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.saimao.latthtautbaydin.R
import it.saimao.latthtautbaydin.ui.Utility

@Composable
fun AnswerScreen(
    questionNumber: Int,
    answerNumber: Int,
    navigateBack: () -> Unit,
    onShare: (question: String, answer: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val jsonData = Utility.getJsonData(LocalContext.current)

    val answerString = jsonData.answers.first {
        it.questionNo == questionNumber && it.answerNo == answerNumber
    }.answerResult

    val questString = jsonData.questions.first {
        it.questionNo == questionNumber
    }.questionName

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.question),
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                )
                Text(
                    text = questString,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                )
                Text(
                    text = stringResource(id = R.string.answer),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                )
                Text(
                    text = answerString,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
                Log.d("Kham", answerString)
                Log.d("Kham", answerNumber.toString())

            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Button(onClick = { navigateBack.invoke() }, Modifier.width(125.dp)) {
                Text(text = "Start over")
            }

            Button(
                onClick = { onShare(questString, answerString) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier.width(125.dp)
            ) {
                Text(text = "Share")
            }
        }

    }

}

@Preview
@Composable
fun AnswerScreenPreview() {
    AnswerScreen(questionNumber = 1, answerNumber = 1, {}, { a, b -> })
}