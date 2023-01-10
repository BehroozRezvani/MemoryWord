package live.snowy.memoryword.android.data

import android.app.Application
import live.snowy.memoryword.android.model.Word

class MemoryWordRepository(application:Application) {
    private val wordDao = MemoryWordRoomDatabase.getDatabase(application)!!.wordDao()

    suspend fun insertSingleWord(word: Word) = wordDao.insertSingleWord(word)

    suspend fun insertMultipleWords(words: List<Word>) = wordDao.insertMultipleWords(words)

    fun getAllWordsLive() = wordDao.getAllWordsLiveData()

    fun getAllWords() = wordDao.getAllWords()

    fun getWordCount() = wordDao.getWordCount()

    fun getWordById(id: Long) = wordDao.getWordById(id)

    suspend fun updateSingleWord(word: Word) {
        wordDao.updateSingleWord(word)
    }

    suspend fun deleteSingleWord(word: Word) {
        wordDao.deleteSingleWord(word)
    }

    fun deleteAllWords() {
        wordDao.deleteAllWords()
    }
}