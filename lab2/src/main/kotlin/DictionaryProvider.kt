import com.sun.source.tree.Tree

object DictionaryProvider {
    fun createDictionary(dictionaryType: DictionaryType): IDictionary =
        when (dictionaryType) {
            DictionaryType.ARRAY_LIST -> ListDictionary
            DictionaryType.TREE_SET -> TreeSetDictionary
            DictionaryType.HASH_SET -> HashSetDictionary
            else -> throw Exception("Unknown type")
        }
}
