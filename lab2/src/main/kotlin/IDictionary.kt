interface IDictionary {
    fun add(s:String) : Boolean
    fun find(s:String): Boolean
    fun size(): Int

    companion object{
        val name: String = "Dictionary"
        val inputFileName: String = "bemenet.txt"

    }

    enum class DictionaryType {
        ARRAY_LIST,
        TREE_SET,
        HASH_SET
    }

}