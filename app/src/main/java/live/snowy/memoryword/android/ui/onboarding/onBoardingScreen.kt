package live.snowy.memoryword.android.ui.onboarding

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun OnBoardingScreen(
    navController: NavHostController
) {
    Surface(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "On Boarding Screen",
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

