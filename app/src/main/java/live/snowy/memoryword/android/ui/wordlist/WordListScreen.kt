package live.snowy.memoryword.android.ui.wordlist


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import live.snowy.memoryword.android.R
import live.snowy.memoryword.android.model.Word
import live.snowy.memoryword.android.model.words
import live.snowy.memoryword.android.ui.components.DefaultSnackbar
import live.snowy.memoryword.android.ui.components.TopLevelScaffold
import live.snowy.memoryword.android.ui.components.WordCard
import live.snowy.memoryword.android.ui.theme.MemoryWordTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordListScreen(
    wordsList: List<Word> = listOf(),
    navController: NavHostController,
    databaseName: String
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    TopLevelScaffold(
        navController = navController,
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(10.dp)
                    .size(55.dp),
                onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(databaseName)
                    }
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
                modifier = Modifier.padding(innerPadding)
            ) {
                items(items = words) { word ->
                    WordCard(word = word)
                }
            }
        },
        whatPage = stringResource(id = R.string.word_list)
    )
}


@Preview
@Composable
fun WordListScreenPreview() {
    MemoryWordTheme(dynamicColor = false) {
        WordListScreen(navController = rememberNavController(), databaseName = "test")
    }
}


