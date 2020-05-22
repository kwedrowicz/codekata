package me.wedrowicz.codekata.bloomfilter

import java.math.BigInteger
import java.math.BigInteger.ONE
import java.math.BigInteger.ZERO
import java.security.MessageDigest

class BloomFilterBitOperatorImplementation(private val size: Int, private val hashFunctions: Int): BloomFilter {
    private var bitmap = ZERO

    override fun add(word: String) {
        val md: MessageDigest = MessageDigest.getInstance("MD5")
        for (i in 1..hashFunctions) {
            md.update(word.toByteArray() + i.toByte())
            val position = BigInteger(md.digest()).mod(BigInteger.valueOf(size.toLong())).toInt()
             bitmap = bitmap or (ONE shl position)
        }
    }

    override fun contains(word: String): Boolean {
        val md: MessageDigest = MessageDigest.getInstance("MD5")
        return (1..hashFunctions)
            .map {
                md.update(word.toByteArray() + it.toByte())
                BigInteger(md.digest()).mod(BigInteger.valueOf(size.toLong())).toInt()
            }
            .count { bitmap and (ONE shl it) > ZERO } == hashFunctions
    }
}
