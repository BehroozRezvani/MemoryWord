package live.snowy.memoryword.android.ui.practice

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import live.snowy.memoryword.android.R
import live.snowy.memoryword.android.model.Word
import live.snowy.memoryword.android.model.WordsViewModel
import live.snowy.memoryword.android.ui.navigation.Screen
import live.snowy.memoryword.android.ui.theme.MemoryWordTheme


@Composable
fun FlashCardScreenTopLevel(
    navController: NavHostController,
    //databaseName: String,
    wordsViewModel: WordsViewModel = viewModel(),
    alreadyPracticedWordsIDs: String = ""
){
    val allWords by wordsViewModel.allWordsLive.observeAsState(listOf())

    FlashCardScreen(
        wordsList = allWords,
        navController = navController,
        score = 0,
        alreadyPracticedWordsIDs = "",

    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlashCardScreen(
    navController: NavHostController,
    wordsList: List<Word> = listOf(),
    score: Int,
    alreadyPracticedWordsIDs: String
) {
    Scaffold(
        bottomBar = {
            NavigationBar() {
                NavigationBarItem(
                    icon =
                    {
                        Icon(
                            Icons.Default.ChevronRight,
                            contentDescription = stringResource(id = R.string.next)
                        )
                    },
                    selected = false,
                    onClick = {
                        navController.navigate(Screen.WordList.route){
                            popUpTo(Screen.WordList.route){
                                inclusive = true
                            }
                        }
                    },
                    label = {
                        Text(stringResource(id = R.string.next))
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

    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                        Log.i("Clicked FlashCardScreen", "FlashCardScreen")
                    },
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
                            text = "Life",
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
                        buttonColor = Color.Red,
                        onClick = {
                            val navigationDest: String = Screen.WordList.route
                        }
                    )
                    Buttons(
                        buttonText = stringResource(R.string.correct),
                        buttonColor = Color.Green,
                        onClick = {
                            val navigationDest: String = Screen.WordList.route
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
){
    Button(
        modifier = Modifier
            .weight(1f)
            .padding(15.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor= buttonColor.copy(alpha = 0.7f),
        ),
        onClick = onClick
    ) {
        Text(
            text = buttonText,
            color = Color.White
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlashCard(
    word: Word,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
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
}


fun getWordsAlreadyPracticed(alreadyPracticedWordsIDs: String): List<Long>{
    val wordsAlreadyPracticed = mutableListOf<Long>()

    if( alreadyPracticedWordsIDs.isNotEmpty() ){

        val wordsAlreadyPracticedIDs = alreadyPracticedWordsIDs.split("_").toTypedArray()

        for(wordID in wordsAlreadyPracticedIDs){
            wordsAlreadyPracticed.add(wordID.toLong())
        }
    }
    return wordsAlreadyPracticed
}

fun getWordsNotPracticed(wordsList: List<Word>, wordsAlreadyPracticed: List<Long>): List<Word>{
    val wordsNotPracticed = mutableListOf<Word>()

    for(word in wordsList){
        if( word.id !in wordsAlreadyPracticed ){
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
){
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
            wordsList = listOf(),
            score = 0,
            alreadyPracticedWordsIDs = ""
        )
    }
}
