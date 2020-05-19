package me.wedrowicz.codekata.binarysearch.iterative

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class IterativeTest {
    @Test
    fun `should return -1 if target value not found`() {
        // given
        val value = 1
        val array = arrayOf(0,2,3,4,5)
        // when
        val position = binarySearch(value, array)
        // then
        assertEquals(-1, position)
    }


    @Test
    fun `should return -1 for empty array`() {
        // given
        val value = 1
        val array = emptyArray<Int>()
        // when
        val position = binarySearch(value, array)
        // then
        assertEquals(-1, position)
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    fun `should return value position in array`(value: Int, array: Array<Int>, expectedPosition: Int) {
        // when
        val position = binarySearch(value, array)
        // then
        assertEquals(expectedPosition, position)
    }

    companion object {
        @JvmStatic
        fun dataProvider(): Stream<Arguments> {
            return Stream.of(
                arguments(1, arrayOf(1, 2, 3, 4, 5), 0),
                arguments(5, arrayOf(1, 2, 3, 4, 5), 4)
            )
        }
    }
}
