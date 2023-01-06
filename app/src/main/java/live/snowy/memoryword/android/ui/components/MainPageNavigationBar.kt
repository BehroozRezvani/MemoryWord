package live.snowy.memoryword.android.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material.icons.outlined.Translate
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import live.snowy.memoryword.android.R
import live.snowy.memoryword.android.ui.navigation.Screen
import live.snowy.memoryword.android.ui.theme.MemoryWordTheme

val screens = listOf(
    Screen.WordList,
    Screen.Practice,
)

@Composable
fun MainPageNavigationBar(
    navController: NavController
) {
    val icons = mapOf(
        Screen.WordList to IconGroup(
            filledIcon = Icons.Filled.Translate,
            outlineIcon = Icons.Outlined.Translate,
            label = stringResource(id = R.string.word_list)
        ),
        Screen.Practice to IconGroup(
            filledIcon = Icons.Filled.Quiz,
            outlineIcon = Icons.Outlined.Quiz,
            label = stringResource(id = R.string.practice)
        )
    )
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        screens.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            val labelText = icons[screen]!!.label
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (isSelected)
                            icons[screen]!!.filledIcon
                        else
                            icons[screen]!!.outlineIcon,
                        contentDescription = labelText
                    )
                },
                label = { Text(labelText) },
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun TestPreview() {
    val navController = rememberNavController()
    MemoryWordTheme(dynamicColor = false) {
        MainPageNavigationBar(navController)
    }
}