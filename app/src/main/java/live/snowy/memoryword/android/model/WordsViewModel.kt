package live.snowy.memoryword.android.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import live.snowy.memoryword.android.data.MemoryWordRepository

class WordsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MemoryWordRepository = MemoryWordRepository(application)

    var allWords: LiveData<List<Word>> = repository.getAllWords()
        private set


}