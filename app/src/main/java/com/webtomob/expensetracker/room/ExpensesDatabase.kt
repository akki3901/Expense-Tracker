package com.webtomob.expensetracker.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.webtomob.expensetracker.room.dao.ExpensesDao
import com.webtomob.expensetracker.room.model.ExpensesData

@Database(entities = [ExpensesData.Expenses::class], version = 3)
abstract class ExpensesDatabase : RoomDatabase() {
    abstract fun expensesDao(): ExpensesDao
}
