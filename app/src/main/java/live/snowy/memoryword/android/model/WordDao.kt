package live.snowy.memoryword.android.model

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

    @Query("SELECT * FROM words_table")
    fun getAllWords(): List<Word>

}