package com.webtomob.expensetracker.util

import android.os.Handler
import android.os.Looper
import android.view.View

/**
 * A [click listener][View.OnClickListener] that debounces multiple clicks posted in the
 * same frame. A click on one button disables all buttons for that frame.
 *
 * Added support for minimal time before the next click [tolerance] and KTX [setDebounceOnClickListener],
 * setting the [tolerance] to 0 reverts back to the single frame behavior
 *
 * Forked and improved on from [Butterknife's DebouncingOnClickListener](https://github.com/JakeWharton/butterknife/blob/master/butterknife-runtime/src/main/java/butterknife/internal/DebouncingOnClickListener.java)
 */
class DebounceOnClickListener(
    private val action: ((View) -> Unit),
    private val tolerance: Long
) : View.OnClickListener {
    override fun onClick(v: View) {
        if (enabled) {
            enabled = false
            /**
             * Post to the main looper directly rather than going through the view.
             * Ensure that ENABLE_AGAIN will be executed, avoid static field [enabled]
             * staying in false state.
             */
            MAIN.postDelayed(ENABLE_AGAIN, tolerance)
            action(v)
        }
    }

    companion object {
        private var enabled = true
        private val ENABLE_AGAIN =
            Runnable { enabled = true }
        private val MAIN: Handler = Handler(Looper.getMainLooper())
    }
}

fun View.setDebounceOnClickListener(tolerance: Long = 500, action: (View) -> Unit) =
    setOnClickListener(DebounceOnClickListener(action, tolerance))