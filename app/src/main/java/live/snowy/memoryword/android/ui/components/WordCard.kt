package live.snowy.memoryword.android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import live.snowy.memoryword.android.model.PartsOfSpeech
import live.snowy.memoryword.android.model.Word


@Composable
fun WordCard(word: Word) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .fillMaxWidth()
    )  {
        CardContent(
            word = word.word,
            partsOfSpeech = word.partsOfSpeech,
            translation = word.translation
        )
    }
}


@Composable
fun CardContent(
    word: String,
    partsOfSpeech: PartsOfSpeech,
    translation: String
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Text(text = partsOfSpeech.name, fontSize = 8.sp)
        Text(text = word, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(text = translation, fontWeight = FontWeight.Normal, modifier = Modifier.padding(top = 4.dp))
    }
}


@Preview
@Composable
fun WordCardPreview() {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        CardContent(
            word = "word",
            partsOfSpeech = PartsOfSpeech.NOUN,
            translation = "translation"
        )
    }
}