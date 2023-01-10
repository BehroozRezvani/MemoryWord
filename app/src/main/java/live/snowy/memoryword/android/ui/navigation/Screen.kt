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
    object NotEnoughWords : Screen("NotEnoughWords", "")
    object ScoreScreen : Screen("ScoreScreen", "score")



    fun routePath() =
        if (argument.isEmpty())
            route
        else
            "${route}/{${argument}}"

    val basePath = "${route}/"
}
