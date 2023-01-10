package live.snowy.memoryword.android.ui.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import live.snowy.memoryword.android.ui.navigation.Screen
import live.snowy.memoryword.android.ui.theme.MemoryWordTheme

@Composable
fun OnBoardingScreen(
    navController: NavHostController,
    /*welcomeViewModel: WelcomeViewModel = hiltViewModel()*/
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Logo()
        Text(
            text = stringResource(R.string.on_boarding_text),
            modifier = Modifier
                .padding(25.dp),
            textAlign = TextAlign.Center
        )
        Button(
            onClick = {
                /*welcomeViewModel.saveOnBoardingState(completed = true)*/
                navController.popBackStack()
                navController.navigate(Screen.LanguageSelection.route)
            }
        ) {
            Text(
                text = stringResource(R.string.start),
                modifier = Modifier
                    .padding(5.dp)
            )
        }
    }
}






@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, backgroundColor = 0xFF1C1B1F, showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    MemoryWordTheme() {
        OnBoardingScreen(navController = rememberNavController())
    }

}