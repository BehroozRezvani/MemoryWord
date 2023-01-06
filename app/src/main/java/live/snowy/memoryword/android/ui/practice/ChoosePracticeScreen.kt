package live.snowy.memoryword.android.ui.practice

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
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
import live.snowy.memoryword.android.ui.components.DefaultSnackbar
import live.snowy.memoryword.android.ui.components.TopLevelScaffold
import live.snowy.memoryword.android.ui.navigation.Screen
import live.snowy.memoryword.android.ui.theme.MemoryWordTheme


@Composable
fun ChoosePracticeScreen(
    navController: NavHostController
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    TopLevelScaffold(
        navController = navController,
        coroutineScope = coroutineScope,
        snackbarHostState = snackbarHostState,
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
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            PracticeCard(
                practiceOption = stringResource(id = R.string.multipleChoice),
                onClick = {navController.navigate(Screen.MultipleChoicePractice.route)},
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
            PracticeCard(
                practiceOption = stringResource(id = R.string.flashCard),
                onClick = {navController.navigate(Screen.FlashCardPractice.route)},
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeCard(
    practiceOption: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Text(
            text = practiceOption,
            modifier = Modifier.padding(16.dp)
        )
    }
}


@Preview
@Composable
fun ChoosePracticeScreenPreview() {
    MemoryWordTheme {
        ChoosePracticeScreen(navController = rememberNavController())
    }
}