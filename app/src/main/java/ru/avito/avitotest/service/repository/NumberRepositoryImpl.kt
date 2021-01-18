package ru.avito.avitotest.service.repository

import androidx.lifecycle.MutableLiveData
import ru.avito.avitotest.service.DataProvider
import ru.avito.avitotest.service.model.GridNumber
import java.util.concurrent.Executors

interface NumberRepository {
    fun refreshData()
    fun removeNumber(model: GridNumber)
}

class NumberRepositoryImpl : NumberRepository {

    private val executor = Executors.newSingleThreadExecutor()
    private val numberListLiveData: MutableLiveData<List<GridNumber>> = MutableLiveData()

    fun getNumbers() = numberListLiveData

    override fun refreshData() {
        executor.execute {
            while (true) {
                val numbers = DataProvider.refreshData()
                if (numbers.isNotEmpty()) {
                    numberListLiveData.postValue(numbers.map { GridNumber(it.toString()) })
                }
                Thread.sleep(5000)
            }
        }
    }

    override fun removeNumber(model: GridNumber) {
        val result = DataProvider.removeNumber(model.value.toInt())
        if (result) {
            val newNumbers = DataProvider.getNumbers()
            numberListLiveData.value = newNumbers.map { GridNumber(it.toString()) }
        }
    }

    companion object {

        private var INSTANCE: NumberRepositoryImpl? = null

        fun getInstance(): NumberRepositoryImpl =
                INSTANCE ?: NumberRepositoryImpl().also { INSTANCE = it }

    }
}