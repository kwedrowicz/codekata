package me.wedrowicz.codekata.conflictingobjectives

abstract class ExtendibleMatcher<T>(private val elements: List<T>) {
    private val elementsSet = elements.toSet()

    fun findCompoundElements(elementsSize: Int): List<T> {
        val results = mutableListOf<T>()

        if(elementsSize > elements.size) {
            return emptyList()
        }
        var elementsIndexes: List<Int>? = (0 until elementsSize).toList()

        while(elementsIndexes != null) {
            elementsIndexes.sumToElement()?.let { results.add(it) }
            elementsIndexes = elementsIndexes.nextPermutation()
        }

        return results.distinct()
    }

    private fun List<Int>.nextPermutation(): List<Int>? {
        val copy = this.toMutableList()
        for(i in this.size-1 downTo 0) {
            if(this[i] < elements.size-1) {
                copy[i]++
                if(copy.distinct().size < this.size) {
                    return copy.nextPermutation()
                }
                if(copy.sorted() != copy) {
                    return copy.nextPermutation()
                }

                return copy
            } else {
                copy[i] = 0
            }
        }

        return null
    }

    private fun List<Int>.sumToElement(): T? {
        val parts = this.map { elements[it] }

        val element = parts.reduce(::reduceElements)
//        val element =  when(parts[0]) {
//            is Int -> (parts as List<Int>).reduce { a, b -> a + b } as T
//            is String -> (parts as List<String>).reduce { a, b -> a + b} as T
//            is ExtendibleClass -> (parts as List<ExtendibleClass>).reduce { a, b -> a.plus(b) } as T
//            else -> throw RuntimeException("Not supported type")
//        }

        return if(elementsSet.contains(element)) element else null
    }

    protected abstract fun reduceElements(a: T, b: T): T
}

class ExtendibleStringMatcher(private val elements: List<String>): ExtendibleMatcher<String>(elements) {
    override fun reduceElements(a: String, b: String): String = a + b
    fun findCompoundWords() = findCompoundElements(2)
}

class ExtendibleIntMatcher(private val elements: List<Int>): ExtendibleMatcher<Int>(elements) {
    override fun reduceElements(a: Int, b: Int): Int = a + b
}

class ExtendibleClassMatcher<T: PlusInterface<T>>(private val elements: List<PlusInterface<T>>): ExtendibleMatcher<PlusInterface<T>>(elements) {
    override fun reduceElements(a: PlusInterface<T>, b: PlusInterface<T>): PlusInterface<T> = a.plus(b)
}

data class PlusClass(override val v: Int): PlusInterface<Int> {
    override fun plus(other: PlusInterface<Int>): PlusInterface<Int> = PlusClass(v + other.v)
}


interface PlusInterface<T> {
    val v: T
    fun plus(other: PlusInterface<T>): PlusInterface<T>
}
