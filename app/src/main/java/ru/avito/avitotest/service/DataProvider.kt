package ru.avito.avitotest.service

import java.util.*

object DataProvider {

    private const val INITIAL_DATA_SIZE = 15

    private var numbers: MutableList<Int> = mutableListOf()
    private var removedNumbersPool: Queue<Int> = LinkedList()
    private var firstInit = true

    fun getNumbers() = numbers

    fun refreshData(): List<Int> {
        if (firstInit) {
            numbers = MutableList(INITIAL_DATA_SIZE) { i -> i + 1 }
            firstInit = false
        } else {
            val newElementIndex = (0..numbers.size).random()
            val newElement: Int? = when {
                removedNumbersPool.isEmpty() -> numbers.size + 1
                else -> removedNumbersPool.poll()
            }

            if (newElement != null) {
                numbers.add(newElementIndex, newElement)
            }
        }
        return numbers
    }

    fun removeNumber(number: Int): Boolean {
        val removed = numbers.remove(number)
        if (removed) {
            removedNumbersPool.add(number)
        }
        return removed
    }
}