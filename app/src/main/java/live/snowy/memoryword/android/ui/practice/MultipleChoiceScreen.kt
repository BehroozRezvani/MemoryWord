package live.snowy.memoryword.android.ui.practice


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import live.snowy.memoryword.android.ui.theme.MemoryWordTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultipleChoiceScreen(
    navController: NavHostController
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
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp),
                    shadowElevation = 3.dp,
                    shape = RoundedCornerShape(11.dp),
                    onClick = { /*TODO*/ },
                ) {
                    Text(
                        text = "What is the meaning of life?",
                        modifier = Modifier
                            .padding(16.dp),
                        fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        //.shadow(1.dp, RoundedCornerShape(11.dp))
                        .padding(6.dp),
                    shadowElevation = 3.dp,
                    shape = RoundedCornerShape(11.dp),
                ) {
                    Text(
                        text = "A. Love",
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        //.shadow(1.dp, RoundedCornerShape(11.dp))
                        .padding(6.dp),
                    shadowElevation = 3.dp,
                    shape = RoundedCornerShape(11.dp),
                ) {
                    Text(
                        text = "B. Not Love",
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        //.shadow(1.dp, RoundedCornerShape(11.dp))
                        .padding(6.dp),
                    shadowElevation = 3.dp,
                    shape = RoundedCornerShape(11.dp),
                ) {
                    Text(
                        text = "C. Maybe Love",
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        //.shadow(1.dp, RoundedCornerShape(11.dp))
                        .padding(6.dp),
                    shadowElevation = 3.dp,
                    shape = RoundedCornerShape(11.dp),
                ) {
                    Text(
                        text = "D. Pomegranate",
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, backgroundColor = 0xFF1C1B1F)
@Composable
fun MultipleChoiceScreenPreview() {
    MemoryWordTheme() {
        MultipleChoiceScreen(navController = rememberNavController())
    }
}