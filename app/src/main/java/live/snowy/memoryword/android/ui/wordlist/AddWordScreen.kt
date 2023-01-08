package live.snowy.memoryword.android.ui.wordlist


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import live.snowy.memoryword.android.R
import live.snowy.memoryword.android.model.Word
import live.snowy.memoryword.android.model.WordsViewModel
import live.snowy.memoryword.android.ui.components.TopLevelScaffold
import live.snowy.memoryword.android.ui.theme.MemoryWordTheme

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

    //val partsOfSpeechItems: MutableList<String> = mutableListOf()
    //for (partOfSpeech in PartsOfSpeech.values()) partsOfSpeechItems += partOfSpeech.name

    var wordText by rememberSaveable { mutableStateOf("") }
    var translationText by rememberSaveable { mutableStateOf("") }
    var partsOfSpeechText by rememberSaveable { mutableStateOf("") }
    //var updatePartsOfSpeech by rememberSaveable { mutableStateOf("") }
    //var expanded by rememberSaveable { mutableStateOf(false) }
    //val icon = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore

    TopLevelScaffold(
        haveBottomBar = false,
        whatPage = "Add Word",
        navController = navController,
        coroutineScope = coroutineScope,
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(20.dp)
                    .size(55.dp),
                onClick = {
                    addingWord(
                        wordText = wordText,
                        translationText = translationText,
                        partsOfSpeechItems = partsOfSpeechText,
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
                    },
                    label = stringResource(id = R.string.word)
                )
                WordTextInput(
                    wordText = translationText,
                    modifier = Modifier
                        .padding(top = 16.dp, start = 24.dp, end = 24.dp)
                        .fillMaxWidth(),
                    updateText = {
                        translationText = it
                    },
                    label = stringResource(id = R.string.translation)
                )
                WordTextInput(
                    wordText = partsOfSpeechText,
                    modifier = Modifier
                        .padding(top = 16.dp, start = 24.dp, end = 24.dp)
                        .fillMaxWidth(),
                    updateText = {
                        partsOfSpeechText = it
                    },
                    label = stringResource(id = R.string.partsOfSpeech)
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
    label: String,
    updateText: (String) -> Unit
) {
    OutlinedTextField(
        value = wordText,
        label = { Text(label) },
        onValueChange = { updateText(it) },
        modifier = modifier
    )
}

fun addingWord(
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
            partsOfSpeech = partsOfSpeechItems
        )
        wordInsert(newWord)
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true, backgroundColor = 0xFF1C1B1F)
@Composable
fun AddWordScreenPreview() {
    MemoryWordTheme() {
        AddWordScreen(
            navController = rememberNavController()
        )
    }
}