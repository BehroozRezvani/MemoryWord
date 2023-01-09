package live.snowy.memoryword.android.ui.practice


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import live.snowy.memoryword.android.model.Word
import live.snowy.memoryword.android.model.WordsViewModel
import live.snowy.memoryword.android.ui.theme.MemoryWordTheme

@Composable
fun FlashCardScreenTopLevel(
    navController: NavHostController,
    wordsViewModel: WordsViewModel = viewModel(),
    //score: Int,
    //alreadyPracticedWordsIDs: String
) {
    val allWords by wordsViewModel.allWords.observeAsState(listOf())
    var wordsList = allWords
    MultipleChoiceScreen(
        wordsList = wordsList,
        navController = navController,
        //score = score,
        //alreadyPracticedWordsIDs = alreadyPracticedWordsIDs,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultipleChoiceScreen(
    navController: NavHostController,
    wordsList: List<Word> = listOf(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            shadowElevation = 10.dp,
            shape = RoundedCornerShape(17.dp),
            color = MaterialTheme.colorScheme.secondaryContainer
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp),
            ) {
                MakeQuestion()
            }
        }
    }
}

@Composable
fun MakeQuestion(
    shuffledWordList: List<Word> = GetShuffledList()
){
    val randomList
    for (i in 0..3){

    }

    val randomList = mutableSetOf(IntRange(1, shuffledWordList.size).random())
    randomList.add(0)
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shadowElevation = 3.dp,
        shape = RoundedCornerShape(11.dp),
        //onClick = { /*TODO*/ },
    ) {
        Text(
            text = shuffledWordList[0].word,
            modifier = Modifier
                .padding(16.dp),
            fontSize = 20.sp
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
    QuestionChoice(choice = shuffledWordList[1].translation, onClick = { /*TODO*/ })
    QuestionChoice(choice = shuffledWordList[2].translation, onClick = { /*TODO*/ })
    QuestionChoice(choice = shuffledWordList[3].translation, onClick = { /*TODO*/ })
    QuestionChoice(choice = shuffledWordList[4].translation, onClick = { /*TODO*/ })
    Spacer(modifier = Modifier.height(12.dp))
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionChoice(
    choice: String,
    onClick: () -> Unit
){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        shadowElevation = 3.dp,
        shape = RoundedCornerShape(11.dp),
        onClick = onClick,
    ) {
        Text(
            text = choice,
            modifier = Modifier.padding(16.dp)
        )
    }
}


@Composable
fun GetShuffledList(
    wordsList: List<Word> = listOf(),
): List<Word> {
    return wordsList.shuffled()
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, backgroundColor = 0xFF1C1B1F)
@Composable
fun MultipleChoiceScreenPreview() {
    MemoryWordTheme() {
        MultipleChoiceScreen(navController = rememberNavController())
    }
}