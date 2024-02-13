package it.saimao.latthtautbaydin

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
            AnswerScreen(question = uiState.question, number = uiState.number, navigateBack = {
                navController.popBackStack(Destinations.Home, false)
            })
        }
    }
}