package it.saimao.latthtautbaydin

import android.content.Intent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.saimao.latthtautbaydin.ui.LattHtautViewModel
import it.saimao.latthtautbaydin.ui.screen.AnswerScreen
import it.saimao.latthtautbaydin.ui.screen.ChooseNumberScreen
import it.saimao.latthtautbaydin.ui.screen.HomeScreen

object Destinations {
    const val Home = "home"
    const val LattHtaut = "latt-htaut"
    const val Answer = "result"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavCompose(modifier: Modifier = Modifier) {
    val viewModel: LattHtautViewModel = viewModel()
    val navController = rememberNavController()
    val uiState by viewModel.uiState

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (uiState.appName != R.string.select_question) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Go back"
                            )
                        }
                    }
                    Text(
                        text = stringResource(id = uiState.appName),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            })
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Destinations.Home
        ) {
            composable(Destinations.Home) {
                viewModel.setAppName(R.string.select_question)
                HomeScreen(
                    onSelectQuestion = {
                        viewModel.updateQuestion(it)
                        navController.navigate(Destinations.LattHtaut)
                    },
                    modifier = Modifier.padding(paddingValues)
                )
            }
            composable(Destinations.LattHtaut) {
                viewModel.setAppName(R.string.choose_number)
                ChooseNumberScreen(
                    onSelectNumber = {
                        viewModel.updateNumber(it)
                        navController.navigate(Destinations.Answer)
                    },
                    modifier = Modifier.padding(paddingValues),
                    numberScreenTab = uiState.numberScreenTab,
                    updateNumberScreenTab = viewModel::updateNumberScreenTab
                )
            }
            composable(Destinations.Answer) {
                viewModel.setAppName(R.string.your_answer)
                val context = LocalContext.current
                AnswerScreen(questionNumber = uiState.question,

                    answerNumber = uiState.number, navigateBack = {
                        navController.popBackStack(Destinations.Home, false)
                    },

                    modifier = Modifier.padding(paddingValues),
                    onShare = { question, answer ->

                        val summary = "အမေး\n$question\n\nအဟော\n$answer"

                        val intent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_SUBJECT, "လက်ထောက်ဗေဒင်အဟော")
                            putExtra(Intent.EXTRA_TEXT, summary)
                        }
                        context.startActivity(
                            Intent.createChooser(intent, context.getString(R.string.app_name))
                        )

                    })
            }
        }

    }

}