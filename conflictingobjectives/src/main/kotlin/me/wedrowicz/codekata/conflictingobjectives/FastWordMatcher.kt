package me.wedrowicz.codekata.conflictingobjectives

class FastWordMatcher(private val words: List<String>) : WordMatcher {
    private val wordsSet = words.toSet()

    override fun findCompoundWords(): List<String> {
        val results = mutableListOf<String>()
        for (word in words) {
            for (i in word.indices) {
                if(wordsSet.contains(word.substring(0 until i)) && wordsSet.contains(word.substring(i until word.length))) {
                    results.add(word)
                    continue
                }
            }
        }

        return results
    }
}
