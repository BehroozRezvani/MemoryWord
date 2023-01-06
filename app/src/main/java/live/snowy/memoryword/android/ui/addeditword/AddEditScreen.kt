package live.snowy.memoryword.android.ui.addeditword

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun AddEditWordScreen1() {

}


@Composable
fun AddEditWordScreen(
    navController: NavHostController
) {
    Surface(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Add / Edit Screen",
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}