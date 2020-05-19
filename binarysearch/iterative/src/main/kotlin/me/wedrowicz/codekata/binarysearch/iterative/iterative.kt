package me.wedrowicz.codekata.binarysearch.iterative

fun binarySearch(value: Int, array: Array<Int>): Int {
    var leftPointer = 0
    var rightPointer = array.size - 1

    while(leftPointer <= rightPointer) {
        val middlePointer = (leftPointer+rightPointer)/2

        if(array[middlePointer] == value) {
            return middlePointer
        }

        if(array[middlePointer] < value) {
            leftPointer = middlePointer + 1
        } else {
            rightPointer = middlePointer - 1
        }
    }

    return -1
}
