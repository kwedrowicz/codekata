package me.wedrowicz.codekata.binarysearch.recursivewithsubarray

import io.kotest.core.spec.style.StringSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.list
import io.kotest.property.forAll


class BinarySearchTest : StringSpec({

    "should return -1 if value is not in array" {
        forAll<List<Int>> { list ->
            // given
            val value: Int
            val array: Array<Int>
            when (list.size) {
                0 -> {
                    value = 0
                    array = emptyArray()
                }
                else -> {
                    value = list[0]
                    array = list.subList(1, list.size).distinct().toTypedArray().sortedArray()
                }
            }

            // when/then
            binarySearch(value, array) == -1
        }
    }

    "should return position when value is in array" {
        forAll(Arb.list(Arb.int(0..100), 1..100)) { list ->
            // given
            val value = list[0]
            val array = list.distinct().toTypedArray().sortedArray()

            // when/then
            binarySearch(value, array) != -1
        }
    }
})
