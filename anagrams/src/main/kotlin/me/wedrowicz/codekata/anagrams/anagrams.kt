package me.wedrowicz.codekata.anagrams

fun anagrams(words: List<String>): List<List<String>> {
    val map = hashMapOf<String, MutableList<String>>()
    for (word in words) {
        val sortedWord = sortCharacters(word)
        if(!map.containsKey(sortedWord)) {
            map[sortedWord] = mutableListOf()
        }
        map[sortedWord]!!.add(word)
    }

    return map.values.toList()
}

private fun sortCharacters(word: String) = word.toCharArray().sorted().joinToString("")
