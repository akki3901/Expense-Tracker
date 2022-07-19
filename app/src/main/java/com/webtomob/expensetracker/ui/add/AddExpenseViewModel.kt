package com.webtomob.expensetracker.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.webtomob.expensetracker.base.BaseViewModel
import com.webtomob.expensetracker.room.ExpensesDatabase
import com.webtomob.expensetracker.room.model.ExpensesData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class AddExpenseViewModel : BaseViewModel() {
    // TODO: Implement the ViewModel
    fun insertExpenses(title: String, cat: String, price: String, date: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val expense = ExpensesData.Expenses(
                    id = 0,
                    title = title,
                    category = cat,
                    price = price,
                    date = date
                )
                try {
                    expensesDatabase.expensesDao().insertAll(expense)
                }catch (ex: Exception){
                    Timber.v("Exception is $ex")
                }
            }
        }
    }

//    fun getExpenses(): Int{
//        var size: Int = 10
//        viewModelScope.launch {
//            withContext(Dispatchers.IO){
//                size = expensesDatabase.expensesDao().getAll().size
//            }
//        }
//        return size
//    }
}