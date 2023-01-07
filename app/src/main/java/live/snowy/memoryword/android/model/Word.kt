package live.snowy.memoryword.android.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val word: String,
    val partsOfSpeech: PartsOfSpeech,
    val translation: String,
    //val isLearned: Boolean,
    //val definition: String,
    //val example: String,
    //val synonyms: String,
    //val antonyms: String,
    //val note: String,
    //val isFavorite: Boolean,
    //val isIgnored: Boolean,
    //val isHidden: Boolean,
    //val isArchived: Boolean,
    //val isDeleted: Boolean,
    //val createdAt: String,
    //val updatedAt: String,
    //val deletedAt: String,
)
