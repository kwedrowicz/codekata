package me.wedrowicz.codekata.binarysearch.recursive

fun binarySearch(value: Int, array: Array<Int>): Int {
    return binarySearchInternal(value, array, 0, array.size - 1)
}

fun binarySearchInternal(value: Int, array: Array<Int>, leftPointer: Int, rightPointer: Int): Int {
    if(leftPointer > rightPointer) return -1
    val middlePointer = (leftPointer + rightPointer) / 2
    if(value == array[middlePointer]) return middlePointer
    if(value < array[middlePointer]) {
        return binarySearchInternal(value, array, leftPointer, middlePointer - 1)
    }
    return binarySearchInternal(value, array, middlePointer + 1, rightPointer)
}
