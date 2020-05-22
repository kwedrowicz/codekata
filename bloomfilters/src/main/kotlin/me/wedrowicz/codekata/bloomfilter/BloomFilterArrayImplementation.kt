package me.wedrowicz.codekata.bloomfilter

import java.math.BigInteger
import java.security.MessageDigest

class BloomFilterArrayImplementation(arraySize: Int, private val hashFunctions: Int): BloomFilter {
    private final val array: Array<Boolean> = Array(arraySize) { false }

    override fun add(word: String) {
        val md: MessageDigest = MessageDigest.getInstance("MD5")
        for (i in 1..hashFunctions) {
            md.update(word.toByteArray() + i.toByte())
            val position = BigInteger(md.digest()).mod(BigInteger.valueOf(array.size.toLong())).toInt()
            array[position] = true
        }
    }

    override fun contains(word: String): Boolean {
        val md: MessageDigest = MessageDigest.getInstance("MD5")
        return (1..hashFunctions)
            .map {
                md.update(word.toByteArray() + it.toByte())
                array[BigInteger(md.digest()).mod(BigInteger.valueOf(array.size.toLong())).toInt()]
            }
            .count { it } == hashFunctions
    }
}
