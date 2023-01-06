package live.snowy.memoryword.android.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import live.snowy.memoryword.android.R
import live.snowy.memoryword.android.ui.theme.MemoryWordTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPageTopAppBar(
    onClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(id = R.string.app_name))},
        navigationIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector =Icons.Filled.Menu,
                    contentDescription = stringResource(R.string.nav_drawer_menu)
                )
            }
        }
    )
}

@Preview
@Composable
private fun MainPageTopAppBarPreview() {
    MemoryWordTheme {
        MainPageTopAppBar()
    }
}