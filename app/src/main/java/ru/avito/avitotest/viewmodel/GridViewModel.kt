package ru.avito.avitotest.viewmodel

import androidx.lifecycle.ViewModel
import ru.avito.avitotest.service.model.GridNumber
import ru.avito.avitotest.service.repository.NumberRepositoryImpl


class GridViewModel : ViewModel() {

    private val numberRepo  = NumberRepositoryImpl.getInstance()
    val numbersLiveData = numberRepo.getNumbers()

    fun refreshData() = numberRepo.refreshData()

    fun removeNumber(model: GridNumber) {
        numberRepo.removeNumber(model)
    }
}