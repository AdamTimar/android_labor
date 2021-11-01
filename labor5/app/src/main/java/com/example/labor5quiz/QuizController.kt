

class QuizController(var quizList : List<Quiz>) {

    fun randomizeQuestions() {
        (quizList as MutableList<Quiz>).shuffle()
        for (i in (0..quizList.size - 1)) {
            (quizList[i].answers as MutableList<String>).shuffle()
        }
    }
}