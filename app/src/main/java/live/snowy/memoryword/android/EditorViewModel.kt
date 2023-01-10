package live.snowy.memoryword.android

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class EditorViewModel(application: Application): AndroidViewModel(application) {
    private var doneOnboarding: File = File(application.filesDir, "doneOnboarding.txt")

    fun doneOnboardingExists(): Boolean {
        return doneOnboarding.exists()
    }

    fun setDoneOnboarding() {
        viewModelScope.launch(Dispatchers.IO) {
            doneOnboarding.createNewFile()
        }
    }

    fun deleteDoneOnboarding() {
        viewModelScope.launch(Dispatchers.IO) {
            doneOnboarding.delete()
        }
    }

    //Read file line by line
    fun readFile(): List<String> {
        return doneOnboarding.readLines()
    }
}