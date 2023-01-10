package live.snowy.memoryword.android.ui.languageselection

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import live.snowy.memoryword.android.EditorViewModel
import live.snowy.memoryword.android.R
import live.snowy.memoryword.android.ui.components.Logo
import live.snowy.memoryword.android.ui.components.OutlinedTextFieldValidation
import live.snowy.memoryword.android.ui.navigation.Screen
import live.snowy.memoryword.android.ui.theme.MemoryWordTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageSelectionScreen(
    navController: NavHostController
) {
    SetOnBoardingDone()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val mContext = LocalContext.current
        Logo()

        CustomSurface(text = stringResource(R.string.want_to_learn_language))

        var fromLanguage by rememberSaveable { mutableStateOf("") }
        var toLanguage by rememberSaveable { mutableStateOf("") }
        var nextPageArgs by rememberSaveable { mutableStateOf("") }


        OutlinedTextField(
            value = fromLanguage,
            onValueChange = { newText ->
                fromLanguage = newText },
            label = { Text(stringResource(R.string.from_colon)) },
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(70.dp))

        CustomSurface(text = stringResource(R.string.native_language))

        /*OutlinedTextFieldValidation(
            value = toLanguage,
            onValueChange = { newText ->
                toLanguage = newText },
            label = { Text(stringResource(R.string.to_colon)) },
            error = "field cannot be empty",
            modifier = Modifier.padding(8.dp)
        )*/

        OutlinedTextField(
            value = toLanguage,
            onValueChange = { newText ->
                toLanguage = newText },
            label = { Text(stringResource(R.string.to_colon)) },
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                if(toLanguage.isEmpty() || fromLanguage.isEmpty()){
                    Toast.makeText(mContext, "Fields can not be empty!", Toast.LENGTH_SHORT).show()
                }
                if(toLanguage.isNotEmpty() && fromLanguage.isNotEmpty()){
                    Toast.makeText(mContext, "Welcome!", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                    navController.navigate(Screen.WordList.route)
                }
            },
            /*{
                if (fromLanguage.isNotEmpty() && toLanguage.isNotEmpty()) {
                    *//*nextPageArgs = "${fromLanguage}_${toLanguage}"
                    val destination = Screen.WordList.route + "/$nextPageArgs"*//*
                    navController.popBackStack()
                    navController.navigate(Screen.WordList.route)
                }
            }*/
        ) {
            Text(text = stringResource(R.string.continue_button))
        }

    }
}


@Composable
fun SetOnBoardingDone(editorViewModel: EditorViewModel = viewModel()) =
    editorViewModel.setDoneOnboarding()


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