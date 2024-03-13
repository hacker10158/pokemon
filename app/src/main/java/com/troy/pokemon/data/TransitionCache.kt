package com.troy.pokemon.data

import android.view.View
import java.lang.ref.WeakReference

object TransitionCache {
    private var cache: WeakReference<View>? = null

    fun addTransitionView(view: View) {
        cache = WeakReference(view)
    }

    fun getTransitionView(): View? = cache?.get()
}