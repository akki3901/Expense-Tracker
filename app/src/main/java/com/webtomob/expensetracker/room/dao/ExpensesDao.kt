package com.webtomob.expensetracker.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.webtomob.expensetracker.room.model.ExpensesData

@Dao
interface ExpensesDao {
    @Query("SELECT * FROM expenses")
    fun getAll(): LiveData<List<ExpensesData.Expenses>>

    @Insert
    suspend fun insertAll(expenses: ExpensesData.Expenses)

    @Query("DELETE FROM expenses WHERE id = :id")
    suspend fun deleteById(id: Int)
}