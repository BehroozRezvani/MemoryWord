package live.snowy.memoryword.android.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import live.snowy.memoryword.android.data.MemoryWordRepository

class WordsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MemoryWordRepository = MemoryWordRepository(application)

    var allWordsLive: LiveData<List<Word>> = repository.getAllWordsLive()
        private set

    var allWords: List<Word> = repository.getAllWords()
        private set

    fun insertWord(word: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertSingleWord(word)
        }
    }

    fun deleteWord(word: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteSingleWord(word)
        }
    }
}