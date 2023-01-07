package live.snowy.memoryword.android.ui.languageselection

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import live.snowy.memoryword.android.R
import live.snowy.memoryword.android.ui.components.Logo
import live.snowy.memoryword.android.ui.navigation.Screen
import live.snowy.memoryword.android.ui.theme.MemoryWordTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageSelectionScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Logo()
        CustomSurface(text = stringResource(R.string.want_to_learn_language))
        var text by remember { mutableStateOf("") }
        OutlinedTextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            label = { Text(stringResource(R.string.from_colon)) },
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(70.dp))
        CustomSurface(text = stringResource(R.string.native_language))
        OutlinedTextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            label = { Text(stringResource(R.string.to_colon)) },
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = {
                val navigationDest: String = Screen.WordList.route
            }
        ) {
            Text(text = stringResource(R.string.continue_button))
        }
    }
}

@Composable
fun CustomSurface(text: String) {
    Surface(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun LanguageSelectionScreenPreview() {
    MemoryWordTheme {
        LanguageSelectionScreen(navController = rememberNavController())
    }
}