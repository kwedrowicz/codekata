package me.wedrowicz.codekata.bloomfilter

import java.math.BigInteger
import java.security.MessageDigest
import java.util.BitSet


class BloomFilterBitSetImplementation(size: Int, private val hashFunctions: Int): BloomFilter {
    private val bitset = BitSet(size)

    override fun add(word: String) {
        val md: MessageDigest = MessageDigest.getInstance("MD5")
        for (i in 1..hashFunctions) {
            md.update(word.toByteArray() + i.toByte())
            val position = BigInteger(md.digest()).mod(BigInteger.valueOf(bitset.size().toLong())).toInt()
            bitset.set(position)
        }
    }

    override fun contains(word: String): Boolean {
        val md: MessageDigest = MessageDigest.getInstance("MD5")
        return (1..hashFunctions)
            .map {
                md.update(word.toByteArray() + it.toByte())
                bitset.get(BigInteger(md.digest()).mod(BigInteger.valueOf(bitset.size().toLong())).toInt())
            }
            .count { it } == hashFunctions
    }


}
