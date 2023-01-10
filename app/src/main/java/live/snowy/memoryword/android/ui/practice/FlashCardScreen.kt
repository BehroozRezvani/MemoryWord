package live.snowy.memoryword.android.ui.practice

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import live.snowy.memoryword.android.R
import live.snowy.memoryword.android.model.Word
import live.snowy.memoryword.android.model.WordsViewModel
import live.snowy.memoryword.android.model.words1
import live.snowy.memoryword.android.ui.navigation.Screen
import live.snowy.memoryword.android.ui.theme.MemoryWordTheme


@Composable
fun FlashCardScreenTopLevel(
    navController: NavHostController,
    wordsViewModel: WordsViewModel = viewModel(),
) {
    val allWords = wordsViewModel.allWords

    FlashCardScreen(
        wordsList = allWords,
        navController = navController,
        //alreadyPracticedWordsIDs = "",
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlashCardScreen(
    navController: NavHostController,
    wordsList: List<Word> = listOf(),
    //alreadyPracticedWordsIDs: String
) {
    var scoreInside by remember { mutableStateOf(0) }
    val wrongRed = Color(0xFFAE344C)
    val correctGreen = Color(0xFF34AD79)
    Scaffold(
        bottomBar = {
            NavigationBar() {
                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Default.Cancel,
                            contentDescription = stringResource(id = R.string.end_practice),
                        )
                    },
                    selected = false,
                    onClick = {
                        val destination = "${Screen.ScoreScreen.basePath}${scoreInside}"
                        navController.navigate(destination) {
                            popUpTo(navController.graph.findStartDestination().id)
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                        }
                    },
                    label = {
                        Text(stringResource(id = R.string.end_practice))
                    }
                )
            }
        },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.flash_card_practice))
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        }

    ) { innerPadding ->
        val wordlist = mutableListOf<Word>()
        wordlist.addAll(wordsList)
        //wordlist.shuffle()

        var cardText by remember { mutableStateOf("") }
        //var wordMeaning by remember { mutableStateOf("") }
        var wordPOS by remember { mutableStateOf("") }

        var i by remember { mutableStateOf(0) }
        cardText = wordlist[i].word
        wordPOS = wordlist[i].partsOfSpeech



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Score: $scoreInside",
                fontSize = 20.sp,
                modifier = Modifier.weight(1f),
            )
            Box(
                modifier = Modifier
                    .weight(4f),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    modifier = Modifier
                        //.fillMaxSize()
                        .padding(4.dp)
                        .size(300.dp),
                    shadowElevation = 10.dp,
                    shape = RoundedCornerShape(20.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    onClick = {
                        cardText = if (cardText == wordlist[i].word) {
                            wordlist[i].translation
                        } else {
                            wordlist[i].word
                        }
                    },
                ) {
                    Text(
                        modifier = Modifier
                            .padding(20.dp),
                        text = wordPOS,
                        fontStyle = FontStyle.Italic,
                        fontSize = 15.sp
                    )
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = cardText,
                            modifier = Modifier
                                .padding(8.dp),
                            fontSize = 50.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            //Spacer(modifier = Modifier.height(0.dp))
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Buttons(
                        buttonText = stringResource(id = R.string.wrong),
                        buttonColor = wrongRed,
                        onClick = {
                            if (i < wordlist.size - 1) {
                                i++
                            } else {
                                val destination = "${Screen.ScoreScreen.basePath}${scoreInside}"
                                navController.navigate(destination) {
                                    popUpTo(navController.graph.findStartDestination().id)
                                    // Avoid multiple copies of the same destination when
                                    // reselecting the same item
                                    launchSingleTop = true
                                }
                            }
                        }
                    )
                    Buttons(
                        buttonText = stringResource(R.string.correct),
                        buttonColor = correctGreen,
                        onClick = {
                            if (i < wordlist.size - 1) {
                                i++
                                scoreInside++
                            } else {
                                val destination = "${Screen.ScoreScreen.basePath}${scoreInside}"
                                navController.navigate(destination) {
                                    popUpTo(navController.graph.findStartDestination().id)
                                    // Avoid multiple copies of the same destination when
                                    // reselecting the same item
                                    launchSingleTop = true
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun RowScope.Buttons(
    buttonText: String,
    buttonColor: Color,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .weight(1f)
            .padding(15.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
        ),
        onClick = onClick
    ) {
        Text(
            text = buttonText,
            color = Color.White
        )
    }
}

/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlashCard(
    word: Word,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .size(300.dp),
        shadowElevation = 10.dp,
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.secondaryContainer,
        onClick = onClick,
    ) {
        Text(
            modifier = Modifier
                .padding(20.dp),
            text = "NOUN",
            fontStyle = FontStyle.Italic,
            fontSize = 15.sp
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = word.word,
                modifier = Modifier
                    .padding(8.dp),
                fontSize = 50.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}*/


fun getWordsAlreadyPracticed(alreadyPracticedWordsIDs: String): List<Long> {
    val wordsAlreadyPracticed = mutableListOf<Long>()

    if (alreadyPracticedWordsIDs.isNotEmpty()) {

        val wordsAlreadyPracticedIDs = alreadyPracticedWordsIDs.split("_").toTypedArray()

        for (wordID in wordsAlreadyPracticedIDs) {
            wordsAlreadyPracticed.add(wordID.toLong())
        }
    }
    return wordsAlreadyPracticed
}

fun getWordsNotPracticed(wordsList: List<Word>, wordsAlreadyPracticed: List<Long>): List<Word> {
    val wordsNotPracticed = mutableListOf<Word>()

    for (word in wordsList) {
        if (word.id !in wordsAlreadyPracticed) {
            wordsNotPracticed.add(word)
        }
    }
    return wordsNotPracticed
}


@Composable
fun FlashCardText(
    partsOfSpeech: String,
    text: String,
    isClicked: Boolean
) {
    Text(
        modifier = Modifier
            .padding(20.dp),
        text = if (isClicked) partsOfSpeech else "",
        fontStyle = FontStyle.Italic,
        fontSize = 15.sp
    )
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(8.dp),
            fontSize = 50.sp,
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, backgroundColor = 0xFF1C1B1F)
@Composable
fun FlashCardScreenPreview() {
    MemoryWordTheme {
        FlashCardScreen(
            navController = rememberNavController(),
            wordsList = words1,
        )
    }
}
