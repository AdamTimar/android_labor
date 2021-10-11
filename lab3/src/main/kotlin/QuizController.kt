import java.util.*

class QuizController(var quizList : List<Quiz>) {

    fun randomizeQuestions(){
        (quizList as MutableList<Quiz>).shuffle()
    }

    fun printQuiz(quiz: Quiz) {
        println(quiz.text)
        for (i in quiz.answers.indices) {
            println("${'a' + i}.) ${quiz.answers[i]}")
        }
        println()
    }


    fun doQuiz(){
        randomizeQuestions()
        val quiestionsCt = (1..quizList.size).random()

        var goods = 0;

        for(i in (0..quiestionsCt-1)){
            (quizList[i].answers as MutableList<Quiz>).shuffle()
            printQuiz(quizList[i])

            print("Your answer: ")
            val stringInput = readLine()!!
            val answer = ('a'..'d').random()

            if(stringInput.length==1){
                if(answer == stringInput[0]){
                    goods++;
                }
            }

            println("helyes valasz: ${answer}\n")


        }

        println()
        println("helyes valaszok szama: ${goods}, helytelen valaszok: ${quiestionsCt-goods}")


    }
}