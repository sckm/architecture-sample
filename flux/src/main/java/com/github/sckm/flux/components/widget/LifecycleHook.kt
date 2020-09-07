package com.github.sckm.flux.components.widget

interface LifecycleHook {
    fun onCreate(r: Runnable): Disposer

    fun onDestroy(r: Runnable): Disposer
}
