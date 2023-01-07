package live.snowy.memoryword.android.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import live.snowy.memoryword.android.model.PartsOfSpeech
import live.snowy.memoryword.android.model.Word
import live.snowy.memoryword.android.model.WordDao


@Database(entities = [Word::class], version = 1)
abstract class MemoryWordRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object{
        private var instance: MemoryWordRoomDatabase? = null
        private val coroutineScope = CoroutineScope(Dispatchers.IO)

        @Synchronized
        fun getDatabase(context: Context): MemoryWordRoomDatabase? {
            if (instance == null) {
                instance =
                    Room.databaseBuilder<MemoryWordRoomDatabase>(
                        context.applicationContext,
                        MemoryWordRoomDatabase::class.java,
                        "memory_word_database"
                    )
                        .allowMainThreadQueries()
                        .addCallback(roomDatabaseCallback(context))
                        .build()
            }
            return instance
        }

        private fun roomDatabaseCallback(context: Context): Callback {
            return object : Callback(){
                override fun onCreate(db: SupportSQLiteDatabase){
                    super.onCreate(db)

                    coroutineScope.launch {
                        populateDatabase(context, getDatabase(context)!!)
                    }
                }
            }
        }

        private suspend fun populateDatabase(context: Context, instance: MemoryWordRoomDatabase) {
            val hello = Word(
                0,
                "Hello",
                PartsOfSpeech.INTERJECTION,
                "Bonjour",
            )
            val world = Word(
                0,
                "World",
                PartsOfSpeech.NOUN,
                "Monde",
            )
            val howAreYou = Word(
                0,
                "How are you?",
                PartsOfSpeech.PHRASE,
                "Comment allez-vous?",
            )
            val iAmFine = Word(
                0,
                "I am fine",
                PartsOfSpeech.PHRASE,
                "Je vais bien",
            )
            val iAmGood = Word(
                0,
                "I am good",
                PartsOfSpeech.PHRASE,
                "Je vais bien",
            )
            val flower = Word(
                0,
                "Flower",
                PartsOfSpeech.NOUN,
                "Fleur",
            )
            val tree = Word(
                0,
                "Tree",
                PartsOfSpeech.NOUN,
                "Arbre",
            )

            val wordlist = mutableListOf(
                hello,
                world,
                howAreYou,
                iAmFine,
                iAmGood,
                flower,
                tree
            )

            val wordDao = instance.wordDao()
            wordDao.insertMultipleWords(wordlist)
        }
    }

}