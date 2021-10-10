import java.io.File
import java.io.InputStream
import java.util.*


object TreeSetDictionary : IDictionary {

    class StringComparator : Comparator<Any> {
        override fun compare(o1: Any, o2: Any): Int {
            val e1: String = o1 as String
            val e2: String = o2 as String
            return e1.compareTo(e2)
        }
    }
    val words  = TreeSet<String>(StringComparator())

    init {
        val inputStream: InputStream = File(IDictionary.inputFileName).inputStream()
        val lineList = mutableListOf<String>()
        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }
        lineList.forEach{words.add(it)}
    }

    override fun add(s: String): Boolean {
        return words.add(s)
    }

    override fun find(s: String): Boolean {
        return words.contains(s)
    }

    override fun size(): Int {
        return words.count()
    }
}