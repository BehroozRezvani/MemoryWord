package live.snowy.memoryword.android.ui.navigation

sealed class Screen(
    val route:String,
    val argument: String
){
    object OnBoarding: Screen("onBoarding", "")
    object LanguageSelection: Screen("languageSelection", "")
    object WordList : Screen("WordList", "")
    object ChoosePractice : Screen("Practice", "")
    object MultipleChoicePractice : Screen("MultipleChoiceTest", "")
    object FlashCardPractice : Screen("FlashCardTest", "")
    object AddEditWord : Screen("AddEditWord", "")
    //object FlashCard : Screen("FlashCard", "")
    //object MultipleChoice : Screen("MultipleChoice", "")



    val basePath = "${route}/"
}
