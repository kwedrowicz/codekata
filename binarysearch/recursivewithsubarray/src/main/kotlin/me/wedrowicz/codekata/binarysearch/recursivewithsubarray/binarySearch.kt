package me.wedrowicz.codekata.binarysearch.recursivewithsubarray

fun binarySearch(value: Int, array: Array<Int>, startingPosition: Int = 0): Int {
    if(array.isEmpty()) {
        return -1
    }

    val middlePoint = array.size / 2
    if(value == array[middlePoint]) {
        return middlePoint + startingPosition
    }
    return if(value < array[middlePoint]) {
        binarySearch(
            value,
            if(0 <= middlePoint-1) array.sliceArray(0 until middlePoint) else emptyArray(),
            startingPosition
        )
    } else {
        binarySearch(
            value,
            if(middlePoint+1 <= array.size-1) array.sliceArray(middlePoint+1 until array.size) else emptyArray(),
            startingPosition + middlePoint
        )
    }
}
