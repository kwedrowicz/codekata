package me.wedrowicz.codekata.bloomfilter

interface BloomFilter {
    fun add(word: String)
    fun contains(word: String): Boolean
}
