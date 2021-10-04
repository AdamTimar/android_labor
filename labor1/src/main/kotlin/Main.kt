import java.util.*

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    add(1,5);
    println();
    daysOfWeeksExercise();
    primesInRange(1,4);
    printEvenNumbers(listOf(1,5,8,2,5,7,11,15,14));

    //4. feladat

    ex4("hazi", ::encode)

    val intList = listOf(2,4,5);
    var daysOfWeeks = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");



    //6. feladat

    println()

    val doubles = intList.map{it*2};

    for(a in doubles){
        println(a)
    }

    println()

    val caps = daysOfWeeks.map{it.uppercase()};

    for(a in caps){
        println(a)
    }

    println()

    val firsts = daysOfWeeks.map{it.get(0)}

    for(a in firsts){
        println(a)
    }

    println();

    val lengths_d = daysOfWeeks.map{it.length}

    for(a in lengths_d){
        println(a)
    }

    println("\n+${lengths_d.average()}");

    //7. feladat

    println()
    daysOfWeeks = daysOfWeeks.toMutableList()

    daysOfWeeks = daysOfWeeks.filter{!it.contains('n')}
    println(daysOfWeeks)

    for((item, index) in daysOfWeeks.withIndex()){
        println("Item at $index is $item")
    }

    //8. feladat

    val randList = (1..10).map { (0..100).random() }

    println()

    randList.forEach{
        println(it)
    }

    println()

    randList.sortedWith(compareBy{it}).forEach{
        println(it)
    }

    println()
    println(randList.any{it%2==0})
    println()

    println(randList.all{it%2==0})
    println()

    var sum = 0

    randList.forEach{
        sum += it
    }

    println(sum/randList.size)

}



//1. feladat
fun add(a:Int, b:Int){
    println("$a + $b = ${a+b}");
}

//2. feladat
fun daysOfWeeksExercise() {
    val daysOfWeeks = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

    for (day in daysOfWeeks) {
        println(day);
    }

    println();

    val daysStartsWithT = daysOfWeeks.filter { it.get(0).compareTo('T') == 0 }

    for (day in daysStartsWithT) {
        println(day);
    }
    println();

    val daysWithE = daysOfWeeks.filter { it.contains('e') }

    for (day in daysWithE) {
        println(day);
    }
    println();

    val daysLengthSix = daysOfWeeks.filter { it.length == 6 }

    for (day in daysLengthSix) {
        println(day);
    }
    println();
}




//3. feladat
fun prime(a: Int): Boolean{
    if(a<2) return false
    for (i in 2..a/2) {
        if (a % i == 0) {
            return false
        }
    }
    return true
}

fun primesInRange(a:Int,b:Int){
    for (nb in a..b) {
        if(prime(nb))
            println(nb)
    }

    println();
}



//4. feladat

fun ex4(msg: String, func: (String) -> String): String {
    return func(msg)
}

fun encode(msg: String): String{
    return Base64.getEncoder().withoutPadding().encodeToString(msg.toByteArray());
}

fun decode(msg: String): String {
    return String(Base64.getDecoder().decode(msg))
}


//5. feladat

fun evenNumbers(list: List<Int>)= list.filter{ it%2 == 0}

fun printEvenNumbers(list: List<Int>){
    val l = list
    for(a in evenNumbers(list)) {
        println(a);
    }
}




