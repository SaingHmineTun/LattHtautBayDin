package it.saimao.latthtautbaydin

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.saimao.latthtautbaydin.ui.LattHtautViewModel
import it.saimao.latthtautbaydin.ui.screen.AnswerScreen
import it.saimao.latthtautbaydin.ui.screen.HomeScreen
import it.saimao.latthtautbaydin.ui.screen.NumberScreen

object Destinations {
    const val Home = "home"
    const val LattHtaut = "latt-htaut"
    const val Answer = "result"
}

@Composable
fun NavCompose(modifier: Modifier = Modifier) {
    val viewModel: LattHtautViewModel = viewModel()
    val uiState = viewModel._uiState.value
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destinations.Home) {
        composable(Destinations.Home) {
            HomeScreen(
                onSelectQuestion = {
                    viewModel.updateQuestion(it)
                    navController.navigate(Destinations.LattHtaut)
                }
            )
        }
        composable(Destinations.LattHtaut) {
            NumberScreen(
                onSelectNumber = {
                    viewModel.updateNumber(it)
                    navController.navigate(Destinations.Answer)
                }
            )
        }
        composable(Destinations.Answer) {
            val context = LocalContext.current
            AnswerScreen(question = uiState.question, number = uiState.number, navigateBack = {
                navController.popBackStack(Destinations.Home, false)
            }, onShare = { question, answer ->

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