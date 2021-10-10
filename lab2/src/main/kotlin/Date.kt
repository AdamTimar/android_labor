import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


data class Date(val calendar: Calendar = Calendar.getInstance(),
                var day: Int = calendar.get(Calendar.DAY_OF_MONTH),
                var month: Int = calendar.get(Calendar.MONTH),
                var year: Int = calendar.get(Calendar.YEAR)) : Comparable<Date>
{
    constructor(day: Int,month: Int,year: Int) : this(Calendar.getInstance(),day,month,year) //ez a konstruktor nem a jelenlegi datumot allitja be, hanem az altalunk kivantat

    override fun toString(): String {
        return "${this.day} ${this.month} ${this.year}"
    }

    override fun compareTo(other: Date): Int {
        if(this.year == other.year){
            if(this.month == other.month){
                if(this.day == other.day){
                    return 0
                }
                else{
                   return other.day - this.day
                }
            }
            else{
                return other.month - this.month
            }
        }
        else{
            return other.year - this.year
        }
    }
}

fun Date.isLeapYear() : Boolean{
    this.calendar.set(Calendar.YEAR, year);
    return this.calendar.getActualMaximum(Calendar.DAY_OF_YEAR)>365
}

fun Date.isValidDate() : Boolean{
    val dateFormatStr = "dd-MM-yyyy"
    try {
        val df: DateFormat = SimpleDateFormat(dateFormatStr)
        df.isLenient = false;
        df.parse("${this.day}-${this.month}-${this.year}")
        return true
    }
    catch (e: ParseException) {
        return false;
    }
}

