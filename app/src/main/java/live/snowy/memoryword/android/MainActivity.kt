package live.snowy.memoryword.android

//import live.snowy.memoryword.android.model.words
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import live.snowy.memoryword.android.model.WordsViewModel
import live.snowy.memoryword.android.ui.languageselection.LanguageSelectionScreen
import live.snowy.memoryword.android.ui.navigation.Screen
import live.snowy.memoryword.android.ui.onboarding.OnBoardingScreen
import live.snowy.memoryword.android.ui.practice.*
import live.snowy.memoryword.android.ui.theme.MemoryWordTheme
import live.snowy.memoryword.android.ui.wordlist.AddWordScreenTopLevel
import live.snowy.memoryword.android.ui.wordlist.WordListScreenTopLevel

class MainActivity : ComponentActivity() {

    /*@Inject
    lateinit var splashViewModel: SplashViewModel*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*installSplashScreen().setKeepOnScreenCondition{
            !splashViewModel.isLoading.value
        }*/

        setContent {
            MemoryWordTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BuildNavigationGraph( startDestination = checkOnBoardingDone())
                }
            }
        }
    }
}

@Composable
fun checkOnBoardingDone(editorViewModel: EditorViewModel = viewModel()): String =
    if (editorViewModel.doneOnboardingExists()) Screen.WordList.route else Screen.OnBoarding.route


@Composable
private fun BuildNavigationGraph(
    wordsViewModel: WordsViewModel = viewModel(),
    startDestination: String
) {
    val navController = rememberNavController()
    //val databaseName = remember { mutableStateOf("") }
    val score = remember { mutableStateOf(0) }

    NavHost(navController = navController, startDestination = startDestination) {
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
            ChoosePracticeScreenTopLevel(navController = navController)
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
            FlashCardScreenTopLevel(navController = navController)
        }
        composable(Screen.MultipleChoicePractice.route) {
            MultipleChoiceScreenTopLevel(navController = navController)
        }
        composable(Screen.WordList.route) {
            WordListScreenTopLevel(navController = navController)
        }
        /*composable(
            Screen.WordList.route,
            arguments = listOf(navArgument(Screen.WordList.argument){ type = NavType.StringType })
            ) {backStackEntry ->
            backStackEntry.arguments?.let {
                if (it.containsKey(Screen.WordList.argument)) {
                    databaseName.value = it.getString(Screen.WordList.argument).toString()
                }
                WordListScreenTopLevel(navController = navController, databaseName = databaseName.value)
            }
        }*/
        composable(Screen.NotEnoughWords.route) {
            NotEnoughWordsScreen(navController = navController)
        }
        /*composable(Screen.ScoreScreen.route) {
            ScoreScreen(navController = navController, score = 0)
        }*/
        composable(
            Screen.ScoreScreen.routePath(),
            arguments = listOf(navArgument(Screen.ScoreScreen.argument){ type = NavType.IntType })
        ) { backStackEntry ->
            backStackEntry.arguments?.let {
                if (it.containsKey(Screen.ScoreScreen.argument)) {
                    score.value = (it.getInt(Screen.ScoreScreen.argument))
                }
                ScoreScreen(navController, score = score.value)
            }
        }
    }
}
