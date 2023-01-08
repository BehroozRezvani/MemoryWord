package live.snowy.memoryword.android.ui.wordlist


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import live.snowy.memoryword.android.R
import live.snowy.memoryword.android.model.PartsOfSpeech
import live.snowy.memoryword.android.model.Word
import live.snowy.memoryword.android.model.WordsViewModel
import live.snowy.memoryword.android.ui.components.TopLevelScaffold

@Composable
fun AddWordScreenTopLevel(
    navController: NavHostController,
    wordsViewModel: WordsViewModel = viewModel()
) {
    AddWordScreen(
        navController = navController,
        addWordToDB = { newWord ->
            wordsViewModel.insertWord(newWord)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWordScreen(
    navController: NavHostController,
    addWordToDB: (Word) -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()

    val partsOfSpeechItems: MutableList<String> = mutableListOf()
    for (partOfSpeech in PartsOfSpeech.values()) partsOfSpeechItems += partOfSpeech.name

    var wordText by rememberSaveable { mutableStateOf("") }
    var translationText by rememberSaveable { mutableStateOf("") }
    var partsOfSpeechSelection by rememberSaveable { mutableStateOf(partsOfSpeechItems[0]) }

    TopLevelScaffold(
        whatPage = "Add Word",
        navController = navController,
        coroutineScope = coroutineScope,
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(10.dp)
                    .size(55.dp),
                onClick = {
                    AddingWord(
                        wordText = wordText,
                        translationText = translationText,
                        partsOfSpeechItems = partsOfSpeechSelection,
                        wordInsert = { newWord ->
                            addWordToDB(newWord)
                        }
                    )
                    navController.navigateUp()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(id = R.string.finishAddingWord)
                )
            }
        },
        pageContent = { innerPadding ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                WordTextInput(
                    wordText = wordText,
                    modifier = Modifier
                        .padding(top = 16.dp, start = 24.dp, end = 24.dp)
                        .fillMaxWidth(),
                    updateText = {
                        wordText = it
                    }
                )

            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordTextInput(
    wordText: String,
    modifier: Modifier,
    updateText: (String) -> Unit
) {
    OutlinedTextField(
        value = wordText,
        label = {
            Text(text = stringResource(id = R.string.word))
        },
        onValueChange = { updateText(it) },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartsOfSpeechInput(
    values: List<String>,
    modifier: Modifier,
    partsOfSpeechUpdate: (String) -> Unit
) {
    /*OutlinedTextField(
        value = wordText,
        label = {
            Text(text = stringResource(id = R.string.word))
        },
        onValueChange = { updateText(it) },
        modifier = modifier
    )*/
}


fun AddingWord(
    wordText: String,
    translationText: String,
    partsOfSpeechItems: String,
    wordInsert: (Word) -> Unit = {}
) {
    if (wordText.isNotEmpty() && translationText.isNotEmpty()) {
        val newWord = Word(
            id = 0,
            word = wordText,
            translation = translationText,
            partsOfSpeech = PartsOfSpeech.valueOf(partsOfSpeechItems)
        )
        wordInsert(newWord)
    }
}

