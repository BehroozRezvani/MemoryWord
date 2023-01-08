package live.snowy.memoryword.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

import live.snowy.memoryword.android.ui.theme.MemoryWordTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import live.snowy.memoryword.android.model.WordsViewModel
//import live.snowy.memoryword.android.model.words
import live.snowy.memoryword.android.ui.addeditword.AddEditWordScreen
import live.snowy.memoryword.android.ui.languageselection.LanguageSelectionScreen
import live.snowy.memoryword.android.ui.navigation.Screen
import live.snowy.memoryword.android.ui.onboarding.OnBoardingScreen
import live.snowy.memoryword.android.ui.practice.ChoosePracticeScreen
import live.snowy.memoryword.android.ui.practice.FlashCardScreen
import live.snowy.memoryword.android.ui.practice.MultipleChoiceScreen
import live.snowy.memoryword.android.ui.wordlist.AddWordScreenTopLevel
import live.snowy.memoryword.android.ui.wordlist.WordListScreen
import live.snowy.memoryword.android.ui.wordlist.WordListScreenTopLevel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemoryWordTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BuildNavigationGraph()
                }
            }
        }
    }
}


@Composable
private fun BuildNavigationGraph(
    wordsViewModel: WordsViewModel = viewModel()
) {
    val navController = rememberNavController()
    val chosenLanguageFrom = "English"
    val chosenLanguageTo = "French"
    val databaseName = remember { mutableStateOf(chosenLanguageFrom + "_" + chosenLanguageTo) }

    NavHost(navController = navController, startDestination = Screen.WordList.route) {
        /*composable(
            Screen.WordList.route,
            arguments = listOf(navArgument(Screen.WordList.argument){ type = NavType.StringType })
        ) {backStackEntry ->
            backStackEntry.arguments?.let {
                if (it.containsKey(Screen.WordList.argument)) {
                    databaseName.value = it.getString(Screen.WordList.argument).toString()
                }
                WordListScreen(navController= navController, wordsList = words, databaseName = databaseName.value)
            }
        }*/
        /*composable(Screen.WordList.route) {
            WordListScreen(navController = navController, databaseName = "English_French")
        }*/
        composable(Screen.ChoosePractice.route) {
            ChoosePracticeScreen(navController = navController)
        }
        composable(Screen.AddEditWord.route) {
            AddWordScreenTopLevel(navController = navController)
        }
        composable(Screen.LanguageSelection.route) {
            LanguageSelectionScreen(navController = navController)
        }
        composable(Screen.OnBoarding.route) {
            OnBoardingScreen(navController = navController)
        }
        composable(Screen.FlashCardPractice.route) {
            FlashCardScreen(navController = navController)
        }
        composable(Screen.MultipleChoicePractice.route) {
            MultipleChoiceScreen(navController = navController)
        }
        composable(Screen.WordList.route) {
            WordListScreenTopLevel(navController = navController, databaseName = "English_French", wordsViewModel = wordsViewModel)
        }
    }
}
