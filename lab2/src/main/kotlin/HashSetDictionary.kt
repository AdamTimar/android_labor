import java.io.File
import java.io.InputStream

object HashSetDictionary : IDictionary {

    val words  = HashSet<String>()

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
        if (words.contains(s))
            return true
        return false
    }

    override fun size(): Int {
        return words.count()
    }

    fun asd(): Int{
        return 0
    }
}