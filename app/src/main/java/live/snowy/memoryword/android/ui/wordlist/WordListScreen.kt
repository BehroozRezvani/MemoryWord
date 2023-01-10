package live.snowy.memoryword.android.ui.wordlist

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import live.snowy.memoryword.android.EditorViewModel
import live.snowy.memoryword.android.R
import live.snowy.memoryword.android.model.Word
import live.snowy.memoryword.android.model.WordsViewModel
import live.snowy.memoryword.android.ui.components.DefaultSnackbar
import live.snowy.memoryword.android.ui.components.TopLevelScaffold
import live.snowy.memoryword.android.ui.components.WordCard
import live.snowy.memoryword.android.ui.navigation.Screen
import live.snowy.memoryword.android.ui.theme.MemoryWordTheme


@Composable
fun WordListScreenTopLevel(
    navController: NavHostController,
    //databaseName: String,
    wordsViewModel: WordsViewModel = viewModel()
){
    val allWords by wordsViewModel.allWordsLive.observeAsState(listOf())

    WordListScreen(
        wordsList = allWords,
        navController = navController,
        //databaseName = databaseName,
        deleteWord = { word ->
            wordsViewModel.deleteWord(word)
        },
        /*deleteAllWords = {
            wordsViewModel.deleteAllWords()
        }*/
    )
}


@Composable
fun WordListScreen(
    wordsList: List<Word> = listOf(),
    navController: NavHostController,
    deleteWord: (Word) -> Unit = {},
    //databaseName: String,
    //deleteAllWords: () -> Unit = {},
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    /*if (databaseName.isNotEmpty()){
        val listOfLanguages = databaseName.split("_")
        val fromLang = listOfLanguages[0]
        val toLang = listOfLanguages[1]
        if (shouldDeleteDB(fromLang = fromLang, toLang = toLang)){
            deleteAllWords()
        }
    }*/


    TopLevelScaffold(
        navController = navController,
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                modifier = Modifier
                    .padding(10.dp),
                onClick = {
                    navController.navigate(Screen.AddEditWord.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.addWord)
                )
            }
        },
        snackbarContent = { data ->
            DefaultSnackbar(
                data = data,
                modifier = Modifier.padding(bottom = 4.dp),
                onDismiss = {
                    coroutineScope.launch {
                        snackbarHostState.currentSnackbarData?.dismiss()
                    }
                }
            )
        },
        coroutineScope = coroutineScope,
        snackbarHostState = snackbarHostState,
        pageContent = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                items(items = wordsList) { word ->
                    WordCard(
                        word = word,
                        deleteOnClick = {
                            deleteWord(word)
                        }
                    )
                }
            }
        },
        whatPage = stringResource(id = R.string.word_list)
    )
}

/*@Composable
fun getLanguages(
    editorViewModel: EditorViewModel = viewModel()
): List<String> {
    return editorViewModel.readFile()
}

@Composable
fun languagesExist(fromLang: String, toLang: String, languages: List<String> = getLanguages()): Boolean {
    return (fromLang == languages[0] && toLang == languages[1])
}

@Composable
fun isListEmpty(langs: List<String> = getLanguages()): Boolean = langs.isEmpty()

@Composable
fun shouldDeleteDB(fromLang: String, toLang: String): Boolean {
    if (!isListEmpty()) {
        return !languagesExist(fromLang = fromLang, toLang = toLang)
    }
    return true
}*/









@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES, backgroundColor = 0xFF1C1B1F, showBackground = true)
@Composable
fun WordListScreenPreview() {
    MemoryWordTheme(dynamicColor = false) {
        WordListScreen(
            navController = rememberNavController(),
            //databaseName = ""
        )
    }
}




