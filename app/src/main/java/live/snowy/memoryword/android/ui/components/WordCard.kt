package live.snowy.memoryword.android.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import live.snowy.memoryword.android.R
import live.snowy.memoryword.android.model.Word


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordCard(
    word: Word,
    deleteOnClick: () -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .fillMaxWidth(),
        onClick = {
            expanded = !expanded
        }
    ) {
        CardContent(
            word = word.word,
            partsOfSpeech = word.partsOfSpeech,
            translation = word.translation,
            expanded = expanded,
            deleteOnClick = deleteOnClick
        )
    }
}


@Composable
fun CardContent(
    word: String,
    partsOfSpeech: String,
    translation: String,
    expanded: Boolean,
    deleteOnClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(start = 15.dp, top = 15.dp, end = 5.dp, bottom = 15.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = partsOfSpeech,
            fontSize = 10.sp
        )
        Text(
            text = word,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Text(
            text = translation,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(top = 4.dp)
        )
        if (expanded) {
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                Button(
                    enabled = false,
                    onClick = { },
                    modifier = Modifier
                        .padding(top = 4.dp, bottom = 0.dp, end = 8.dp),
                    /*colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue.copy(alpha = 0.7f, blue = 0.5f),
                    )*/
                ) {
                    Text(text = stringResource(R.string.edit))
                }
                Button(
                    onClick = { deleteOnClick() },
                    modifier = Modifier
                        .padding(top = 4.dp, bottom = 0.dp, end = 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red.copy(alpha = 0.7f),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = stringResource(R.string.delete))
                }
            }
        }
    }
}


@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF1C1B1F,
    showBackground = true
)
@Composable
fun WordCardPreview() {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        CardContent(
            word = "word",
            partsOfSpeech = "NOUN",
            translation = "translation",
            expanded = true,
            deleteOnClick = {}
        )
    }
}