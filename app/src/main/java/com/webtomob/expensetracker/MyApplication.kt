package com.webtomob.expensetracker

import android.app.Application
import androidx.room.Room
import com.webtomob.expensetracker.room.ExpensesDatabase
import com.webtomob.expensetracker.ui.add.AddExpenseViewModel
import com.webtomob.expensetracker.ui.home.HomeViewModel
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.scope.get
import org.koin.dsl.module
import timber.log.Timber

class MyApplication: Application() {

    private val expenseDatabase = module {
        single {
            Room.databaseBuilder(
                androidApplication(),
                ExpensesDatabase::class.java,
                "expense_database"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    private val addExpenseViewModel = module {
        viewModel {
            AddExpenseViewModel()
        }
    }

    private val homeExpenseViewModel = module {
        viewModel {
            HomeViewModel()
        }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                addExpenseViewModel,
                homeExpenseViewModel,
                expenseDatabase
            )
        }

        //if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        //}

        /*if (BuildConfig.BUILD_TYPE != "debug") {
            //disabling for all, other than debug varient
            Pandora.get().disableShakeSwitch()
        }*/
    }
}