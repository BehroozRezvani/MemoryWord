package live.snowy.memoryword.android.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Word(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val word: String,
    val partOfSpeech: PartsOfSpeech,
    //val definition: String,
    val translation: String,
    //val example: String,
    //val synonyms: String,
    //val antonyms: String,
    //val note: String,
    //val isFavorite: Boolean,
    val isLearned: Boolean,
    //val isIgnored: Boolean,
    //val isHidden: Boolean,
    //val isArchived: Boolean,
    //val isDeleted: Boolean,
    //val createdAt: String,
    //val updatedAt: String,
    //val deletedAt: String,
)
