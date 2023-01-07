package live.snowy.memoryword.android.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WordDao {
    @Insert
    fun insertSingleWord(word: Word)

    @Insert
    fun insertMultipleWords(words: List<Word>)

    @Update
    fun updateSingleWord(word: Word)

    @Delete
    fun deleteSingleWord(word: Word)

    @Query("SELECT * FROM words")
    fun getAllWords(): List<Word>

    @Query("SELECT * FROM words")
    fun getAllWordsLiveData(): LiveData<List<Word>>

    @Query("SELECT * FROM words WHERE id = :id")
    fun getWordById(id: Long): Word


}

























