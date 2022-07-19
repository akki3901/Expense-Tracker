package com.webtomob.expensetracker.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.webtomob.expensetracker.room.ExpensesDatabase
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.time.OffsetDateTime
import java.time.ZoneOffset

abstract class BaseViewModel : ViewModel(), KoinComponent {
    protected val expensesDatabase: ExpensesDatabase by inject()
}

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>