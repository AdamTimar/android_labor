import java.util.*


fun main(){


    //feladat 1

    println("exercise1")

    var dict : IDictionary = DictionaryProvider.createDictionary(DictionaryType.ARRAY_LIST)

    println("Number of words (array_list): ${dict.size()}")

    println(dict.add("a"))

    println("Number of words after add to arraylist: ${dict.size()}\n")

    dict = DictionaryProvider.createDictionary(DictionaryType.HASH_SET)
    println("Number of words (hashset): ${dict.size()}")

    println(dict.add("a"))

    println("Number of words after add to hashset: ${dict.size()}\n")

    dict = DictionaryProvider.createDictionary(DictionaryType.TREE_SET)
    println("Number of words (treeset): ${dict.size()}")

    println(dict.add("a"))

    println("Number of words after add to treeset: ${dict.size()}\n")

    var word: String?
    while(true){
        print("What to find? ")
        word = readLine()
        if( word.equals("quit")){
            break
        }
        println("Result: ${word?.let { dict.find(it) }}")
    }

    //feladat2

    println("\nexercise2\n")

    val name = "Adam Mate Timar"
    name.monogram()

    val strList = listOf("pear","apple","raspberry","lemon")

    println(strList.joinBySeparator("#"))

    println(strList.longestString())



    //feladat 3
    println("\nexercise3\n")

    val date = Date()

    println("Leap year test")
    println(date)

    println(date.isLeapYear())
    println()

    val date2 = Date(16,12,2012)

    println(date2)

    println(date2.isLeapYear())
    println()

    println("Valid date test")
    val date3 = Date(32,12,2012)
    println(date3)
    println(date3.isValidDate())
    println()

    date3.month = 2
    date3.day = 30
    println(date3)
    println(date3.isValidDate())
    println()

    date3.day = 29
    println(date3)
    println(date3.isValidDate())
    println()

    date3.month = 13
    date3.day = 27
    println(date3)
    println(date3.isValidDate())

    val validDates  = mutableListOf<Date>()
    var i = 0
    var day : Int
    var month : Int
    var year: Int
    var date4: Date
    println("\ninvalid dates after random gen")


    do{
        day = (1..50).random()
        month = (1..20).random()
        year = (1..2500).random()
        date4 = Date(day,month,year)

        if(date4.isValidDate()){
            validDates.add(date4)
            i++
        }

        else{
            println(date4)
        }
    }while(i<10)

    println("\nvalid dates:")

    validDates.forEach{ println(it)}
    println()

    validDates.sort()

    println("\nafter sort (natural ordering):")

    validDates.forEach{ println(it)}
    println()

    validDates.reverse()
    println("\nafter reverse:")
    validDates.forEach{ println(it)}
    println()

    Collections.sort(validDates, Comparator<Any?> { s1, s2 ->
        return@Comparator (s2 as Date).day - (s1 as Date).day
    })
    println("\nafter sort (custom ordering - sort by day):")
    validDates.forEach{ println(it)}
    println()

}

fun String.monogram(){

    val splits = this.split(" ")
    val result = splits.map{it.get(0)}
    println(result.joinToString(separator = ""))
}

fun List<String>.joinBySeparator(separatorP: CharSequence) : String{

    if(this.count()==0){
        throw Exception("Empty list")
    }

    val result = this.joinToString (separator = separatorP)
    return result
}

fun List<String>.longestString() : String{
    if(this.count()==0){
        throw Exception("Empty list")
    }
    val lengthsStringMap = this.map{it.length to it}
    var maximum = lengthsStringMap.get(0).first
    var str = lengthsStringMap.get(0).second
    lengthsStringMap.forEach{if(it.first>maximum) {
            maximum = it.first
            str = it.second
        }
    }

    return str;

}


