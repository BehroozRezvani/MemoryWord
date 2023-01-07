package live.snowy.memoryword.android.ui.practice

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
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
        pageContent = { innerPadding ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {

                PracticeCard(
                    cardName = stringResource(id = R.string.multiple_choice),
                    cardDescription = stringResource(id = R.string.multiple_choice_description),
                    onClick = {
                        navController.navigate(Screen.MultipleChoicePractice.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }},
                )
                PracticeCard(
                    cardName = stringResource(id = R.string.flash_card),
                    cardDescription = stringResource(id = R.string.flash_card_description),
                    onClick = {navController.navigate(Screen.FlashCardPractice.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }},
                )

            }
        },
        whatPage = stringResource(R.string.practice_selection)
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeCard(
    cardName: String,
    cardDescription: String,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(180.dp),
        color = MaterialTheme.colorScheme.secondaryContainer,
        shadowElevation = 10.dp,
        shape = RoundedCornerShape(30.dp),
        onClick = onClick
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = cardName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Divider(
                //thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.35f),
                modifier = Modifier
                    .width(300.dp)
                    .padding(8.dp)
            )
            Text(
                textAlign = TextAlign.Center,
                text = cardDescription,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES, backgroundColor = 0xFF1C1B1F, showBackground = true)
@Composable
fun ChoosePracticeScreenPreview() {
    MemoryWordTheme {
        ChoosePracticeScreen(navController = rememberNavController())
    }
}