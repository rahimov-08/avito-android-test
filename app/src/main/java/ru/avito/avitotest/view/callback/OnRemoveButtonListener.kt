package ru.avito.avitotest.view.callback

import ru.avito.avitotest.service.model.GridNumber

interface OnRemoveButtonListener {
    fun removeButtonClicked(model:GridNumber)
}