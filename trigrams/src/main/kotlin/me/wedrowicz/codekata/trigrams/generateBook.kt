package me.wedrowicz.codekata.trigrams

fun generateBook(vararg sources: String): String {
    val words = sources.flatMap { it.parseWords() }
    val trigrams = mutableMapOf<String, MutableList<String>>()
    for (i in 0..words.size - 3) {
        val key = "${words[i]};${words[i + 1]}"
        trigrams.putIfAbsent(key, mutableListOf())
        trigrams[key]?.add(words[i + 2])
    }

    val bookSize = 500
    val book = mutableListOf(words.random(), words.random())
    for (i in 2..bookSize) {
        val key = "${book[i-2]};${book[i-1]}"
        val candidate = (trigrams[key] ?: listOf(".")).random()
        if(book.last() == "." && candidate == ".") {
            book.add(words.random())
        } else {
            book.add(candidate)
        }
    }
    return book
        .joinToString(separator = " ")
        .replace(" ,", ",")
        .replace(" .", ".")
}

private fun String.parseWords(): List<String> = this.split(' ', '\n', '\r')
    .filter { it.isNotBlank() }
    .flatMap { if (listOf(',', '.').contains(it.last())) listOf(it.substring(0 until it.length - 1), it[it.length - 1].toString()) else listOf(it) }

fun main() {
    val text = {}.javaClass.getResource("/the-adventure-of-sherlock-holmes-trimmed.txt").readText()
    println(generateBook(text))
}
