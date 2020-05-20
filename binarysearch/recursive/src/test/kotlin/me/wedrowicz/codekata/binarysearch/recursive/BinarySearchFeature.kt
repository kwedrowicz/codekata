package me.wedrowicz.codekata.binarysearch.recursive

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object BinarySearchFeature: Spek({
    Feature("binary search") {

        Scenario("should return -1 if target value not found") {
            val value = 1
            val array = arrayOf(0,2,3,4,5)
            var position = 0

            When("perform search") {
                position = binarySearch(value, array)
            }

            Then("should return -1") {
                assertThat(position, equalTo(-1))
            }
        }

        Scenario("should return -1 for empty array") {
            val value = 1
            val array = emptyArray<Int>()
            var position = 0

            When("perform search") {
                position = binarySearch(value, array)
            }

            Then("should return -1") {
                assertThat(position, equalTo(-1))
            }
        }

        Scenario("should return value position in array") {
            val testCases = listOf(
                TestCase(1, arrayOf(1, 2, 3, 4, 5), 0),
                TestCase(5, arrayOf(1, 2, 3, 4, 5), 4)
            )
            var position = 0

            testCases.forEach {
                When("perform search") {
                    position = binarySearch(it.value, it.array)
                }

                Then("should return position") {
                    assertThat(position, equalTo(it.expectedPosition))
                }
            }
        }
    }
})

internal data class TestCase(val value: Int, val array: Array<Int>, val expectedPosition: Int)
