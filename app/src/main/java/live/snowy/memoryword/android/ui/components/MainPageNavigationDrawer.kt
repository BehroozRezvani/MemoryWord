package live.snowy.memoryword.android.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import live.snowy.memoryword.android.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import live.snowy.memoryword.android.ui.navigation.Screen
import live.snowy.memoryword.android.ui.theme.MemoryWordTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPageNavigationDrawer(
    //added code
    //currentRoute: String,
    //added code
    navController: NavHostController,
    drawerState: DrawerState,
    closeDrawer: () -> Unit = {},
    content: @Composable () -> Unit = {},
    //modifier: Modifier = Modifier
) {
    val items = listOf(
        Pair(
            Icons.Default.AddCircle,
            stringResource(id = R.string.addWord)
        ),
        Pair(
            Icons.Default.Language,
            stringResource(id = R.string.languageSelection)
        ),
        Pair(
            Icons.Default.Help,
            stringResource(id = R.string.help_info)
        )
    )

    /*ModalDrawerSheet(modifier) {
        Image(
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp, top = 16.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.app_name),
            contentScale = ContentScale.Fit
        )
        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.addWord)) },
            icon = { Icon(Icons.Filled.AddCircle, null) },
            selected = currentRoute == Screen.AddEditWord.route,
            onClick = {  },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.languageSelection)) },
            icon = { Icon(Icons.Filled.Language, null) },
            selected = currentRoute == Screen.LanguageSelection.route,
            onClick = {  },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
    }*/

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                val selectedItem = rememberSaveable{ mutableStateOf(0) }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Image(
                        modifier = Modifier
                            .size(200.dp)
                            .padding(bottom = 16.dp, top = 16.dp),
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = stringResource(id = R.string.app_name),
                        contentScale = ContentScale.Fit
                    )
                    items.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            icon = {
                                Icon(
                                    imageVector = item.first,
                                    contentDescription = item.second
                                )
                            },
                            label = { Text(item.second) },
                            selected = index == selectedItem.value,
                            onClick = {
                                selectedItem.value = index
                                when (index) {
                                    0 -> {
                                        closeDrawer()
                                        navController.navigate(Screen.AddEditWord.route)
                                    }
                                    1 -> {
                                        closeDrawer()
                                        navController.navigate(Screen.LanguageSelection.route)
                                    }
                                    2 -> {
                                        closeDrawer()
                                        navController.navigate(Screen.OnBoarding.route)
                                    }
                                }
                            }
                        )
                    }
                }
            }
        },
        content = content
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun MainPageNavigationDrawerPreview() {
    MemoryWordTheme {
        MainPageNavigationDrawer(
            navController = rememberNavController(),
            drawerState = rememberDrawerState(DrawerValue.Open)
        ) {
            Text("Content")
        }
    }
}