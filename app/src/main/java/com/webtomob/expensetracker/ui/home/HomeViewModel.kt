package com.webtomob.expensetracker.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.webtomob.expensetracker.base.BaseViewModel
import com.webtomob.expensetracker.room.model.ExpensesData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : BaseViewModel() {
    // TODO: Implement the ViewModel

    fun getExpenses(): LiveData<List<ExpensesData.Expenses>> = expensesDatabase.expensesDao().getAll()

    fun deleteId(id: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                expensesDatabase.expensesDao().deleteById(id)
            }
        }
    }

}