package com.webtomob.expensetracker.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

class ExpensesData {
    @Entity (tableName = "expenses")
    data class Expenses(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "category") val category: String,
        @ColumnInfo(name = "price") val price: String,
        @ColumnInfo(name = "date") val date: String

    )
}