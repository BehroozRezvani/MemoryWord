package live.snowy.memoryword.android.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import live.snowy.memoryword.android.R
import live.snowy.memoryword.android.ui.theme.MemoryWordTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeTopAppBar(
    practiceTitle: String,

) {
    CenterAlignedTopAppBar(
        title = { Text(text = practiceTitle) },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.nav_back_button)
                )
            }
        }
    )
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, backgroundColor = 0xFF1C1B1F)
@Composable
private fun PracticeTopAppBarPreview() {
    MemoryWordTheme {
        PracticeTopAppBar("Practice 1")
    }
}