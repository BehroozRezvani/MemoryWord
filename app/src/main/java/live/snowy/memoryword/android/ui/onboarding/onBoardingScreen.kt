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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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
            text = "OnBoardingScreen",
        )
        Button(
            onClick = {
                /*welcomeViewModel.saveOnBoardingState(completed = true)*/
                navController.popBackStack()
                navController.navigate(Screen.WordList.route)
            }
        ) {
            Text(
                text = "Start",
                modifier = Modifier
                    .padding(8.dp)
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