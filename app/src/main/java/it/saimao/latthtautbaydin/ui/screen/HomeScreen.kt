package it.saimao.latthtautbaydin.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import it.saimao.latthtautbaydin.data.Question
import it.saimao.latthtautbaydin.ui.Utility

@Composable
fun HomeScreen(onSelectQuestion: (Int) -> Unit, modifier: Modifier = Modifier) {

    val questions = Utility.getJsonData(LocalContext.current).questions

    var uiState by remember {
        mutableStateOf(questions)
    }
    var text by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier.padding(8.dp)
    ) {

        OutlinedTextField(value = text,
            label = {
                Text(text = "Filter questions")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            onValueChange = { inputText ->
                text = inputText
                uiState = if (inputText.isNotEmpty()) {
                    questions.filter {
                        it.questionName.contains(inputText)
                    }
                } else {
                    questions
                }
            })
        Spacer(modifier = Modifier.height(16.dp))
        CardList(listOfQuestion = uiState, onSelectQuestion = onSelectQuestion)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardItem(modifier: Modifier = Modifier, onSelectQuestion: (Int) -> Unit, question: Question) {
    Card(modifier = modifier, onClick = {
        onSelectQuestion(question.questionNo)
    }) {
        Row(modifier = Modifier.padding(8.dp)) {
            Text(text = question.questionNo.toString(), modifier = Modifier.width(30.dp))
            Text(text = question.questionName, modifier = Modifier.weight(1F))
        }
    }
}

@Composable
fun CardList(
    listOfQuestion: List<Question>,
    onSelectQuestion: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(listOfQuestion) {
            CardItem(
                question = it,
                onSelectQuestion = onSelectQuestion,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}


@Preview
@Composable
fun CardItemPreview() {
    CardItem(
        question = Question(
            questionNo = 1,
            questionName = "ဇနီးမောင်နှံ၌ သားသမီးရကိန်းနှင့် ပတ်သက်သော အဟော။"
        ),
        onSelectQuestion = {}
    )
}

@Preview
@Composable
fun CardListPreview() {
    CardList(
        listOfQuestion = Utility.getJsonData(LocalContext.current).questions,
        onSelectQuestion = {})
}