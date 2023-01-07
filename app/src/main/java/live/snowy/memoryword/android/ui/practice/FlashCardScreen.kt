package live.snowy.memoryword.android.ui.practice

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import live.snowy.memoryword.android.R
import live.snowy.memoryword.android.ui.navigation.Screen
import live.snowy.memoryword.android.ui.theme.MemoryWordTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlashCardScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
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
                Button(
                    onClick = {
                        val navigationDest: String = Screen.WordList.route
                    }
                ) {
                    Text(text = stringResource(R.string.continue_button))
                }
                Button(
                    onClick = {
                        val navigationDest: String = Screen.WordList.route
                    }
                ) {
                    Text(text = stringResource(R.string.continue_button))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, backgroundColor = 0xFF1C1B1F)
@Composable
fun FlashCardScreenPreview() {
    MemoryWordTheme {
        FlashCardScreen(navController = rememberNavController())
    }
}
