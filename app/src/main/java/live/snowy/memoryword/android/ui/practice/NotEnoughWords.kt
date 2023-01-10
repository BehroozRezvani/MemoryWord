package live.snowy.memoryword.android.ui.practice

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import live.snowy.memoryword.android.R
import live.snowy.memoryword.android.ui.navigation.Screen
import live.snowy.memoryword.android.ui.theme.MemoryWordTheme

@Composable
fun NotEnoughWordsScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EnoughCard(
            cardName = stringResource(R.string.not_enough_word),
            cardDescription = stringResource(R.string.not_enough_words_description)
        )
        Spacer(modifier = Modifier.height(100.dp))
        Button(
            onClick = {
                navController.popBackStack()
                navController.navigate(Screen.WordList.route)
            },
            modifier = Modifier
                .padding(10.dp)
                ,
        ) {
            Text(text = stringResource(R.string.go_to_word_list))
        }
    }
}




@Composable
fun EnoughCard(
    cardName: String,
    cardDescription: String,
) {
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
        ,
        color = MaterialTheme.colorScheme.secondaryContainer,
        shadowElevation = 10.dp,
        shape = RoundedCornerShape(30.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = cardName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                textAlign = TextAlign.Center,
                text = cardDescription,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}











@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, backgroundColor = 0xFF1C1B1F)
@Composable
fun NotEnoughWordsPreview() {
    MemoryWordTheme {
        NotEnoughWordsScreen(navController = rememberNavController())
    }
}