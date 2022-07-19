package com.webtomob.expensetracker.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import timber.log.Timber

abstract class BaseFragment: Fragment() {
    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    fun safeNavigate(directions: NavDirections) {
        try {
            findNavController().navigate(directions)
        } catch (e: Exception) {
            Timber.e( "Exception is $e")
        }
    }

    fun safeNavigate(@IdRes target: Int) {
        try {
            findNavController().navigate(target)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    fun backNavigation(){
        try {
            findNavController().navigateUp()
        } catch (e: Exception){
            Timber.e( "Exception is $e")
        }
    }
}