fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")

    val answers = listOf("1","2","5","10")

    val quiz1 = Quiz("Hany labad van?",answers)
    val quiz2 = Quiz("Hany fejed van?",answers)
    val quiz3 = Quiz("Hany ujjad van?",answers)

    val quizes = listOf(quiz1,quiz2,quiz3)

    val quizController = QuizController(quizes)

    quizController.doQuiz()

}