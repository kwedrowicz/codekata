package me.wedrowicz.codekata.conflictingobjectives

class ReadableWordMatcher(private val words: List<String>): WordMatcher {
    override fun findCompoundWords(): List<String> {
        val results = mutableListOf<String>()
        for (word in words) {
            if (word.isCompoundWord()) {
                results.add(word)
            }
        }

        return results
    }

    private fun String.isCompoundWord(): Boolean {
        for (prefix in words) {
            for (suffix in words) {
                if (prefix + suffix == this) {
                    return true
                }
            }
        }

        return false
    }
}
