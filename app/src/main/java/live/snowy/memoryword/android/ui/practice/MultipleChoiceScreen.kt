package live.snowy.memoryword.android.ui.practice


import android.app.Application
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import live.snowy.memoryword.android.model.words1
import live.snowy.memoryword.android.ui.theme.MemoryWordTheme

@Composable
fun MultipleChoiceScreenTopLevel(
    navController: NavHostController,
    wordsViewModel: WordsViewModel = viewModel()
) {
    val allWordsFromDB = wordsViewModel.allWords
    MultipleChoiceScreen(
        wordsList = allWordsFromDB,
        navController = navController,
    )
}


@Composable
fun MultipleChoiceScreen(
    navController: NavHostController,
    wordsList: List<Word> = listOf(),
) {
    var score by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Multiple Choice Screen",
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp),
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Score:${score}",
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp),
            color = MaterialTheme.colorScheme.onSurface
        )
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
                MakeQuestion(wordsList, score)
            }
        }
    }
}

@Composable
fun MakeQuestion (
    wordList: List<Word> = listOf(),
    score : Int
) {
    var scoreInside by remember { mutableStateOf(0) }
    val randomIDSet = mutableSetOf<Int>()
    while (randomIDSet.size < 3) randomIDSet.add((1..wordList.size).random())
    randomIDSet.add(0)
    var List2: List<Int> = randomIDSet.toList().shuffled()
    //List2 = List2.shuffled()
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shadowElevation = 3.dp,
        shape = RoundedCornerShape(11.dp),
    ) {
        Text(
            text = wordList[0].word,
            modifier = Modifier
                .padding(16.dp),
            fontSize = 20.sp
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
    QuestionChoice(choice = wordList[0].translation,
        onClick = {
            checkAnswer(
                answer = wordList[0].word,
                word = wordList[0]
            )
            scoreInside++
        })
    QuestionChoice(
        choice = wordList[1].translation,
        onClick = {
            checkAnswer(
                answer = wordList[0].word,
                word = wordList[1]
            )
        })
    QuestionChoice(
        choice = wordList[2].translation,
        onClick = {
            checkAnswer(
                answer = wordList[0].word,
                word = wordList[2]
            )
        })
    QuestionChoice(
        choice = wordList[3].translation,
        onClick = {
            checkAnswer(
                answer = wordList[0].word,
                word = wordList[3]
            )
        })
    Spacer(modifier = Modifier.height(12.dp))
    score = scoreInside
}

fun checkAnswer(
    answer: String,
    word: Word
): Boolean {
    if (answer == word.translation) {
        return true
    }
    return false
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionChoice(
    choice: String,
    onClick: () -> Unit
) {
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
        MultipleChoiceScreen(navController = rememberNavController(), wordsList = words1)
    }
}