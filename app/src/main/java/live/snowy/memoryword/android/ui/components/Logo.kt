package live.snowy.memoryword.android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import live.snowy.memoryword.android.R

@Composable
fun Logo(modifier: Modifier = Modifier) {
    Image(
        modifier = Modifier
            .size(200.dp)
            .padding(16.dp),
        painter = painterResource(if (isSystemInDarkTheme()) R.drawable.logo_dark else R.drawable.logo),
        contentDescription = stringResource(id = R.string.app_name),
        contentScale = ContentScale.Fit,
    )
}