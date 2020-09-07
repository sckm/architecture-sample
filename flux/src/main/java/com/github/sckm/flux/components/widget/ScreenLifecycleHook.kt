package com.github.sckm.flux.components.widget

class ScreenLifecycleHook : LifecycleHook {
    private var state = NONE

    private val onCreateHooks: MutableSet<Runnable> = mutableSetOf()
    private val onDestroyHooks: MutableSet<Runnable> = mutableSetOf()

    override fun onCreate(r: Runnable): Disposer {
        onCreateHooks.add(r)
        if (state == CREATE) {
            r.run()
        }
        return Disposer.from(
            object : Disposable {
                override fun dispose() {
                    onCreateHooks.remove(r)
                }
            })
    }

    override fun onDestroy(r: Runnable): Disposer {
        onDestroyHooks.add(r)
        if (state == DESTROY) {
            r.run()
        }
        return Disposer.from(
            object : Disposable {
                override fun dispose() {
                    onDestroyHooks.remove(r)
                }
            })
    }

    fun dispatchOnCreate() {
        if (state == CREATE) {
            return
        }
        state = CREATE
        onCreateHooks.forEach(Runnable::run)
    }

    fun dispatchOnDestroy() {
        if (state == DESTROY) {
            return
        }
        state = DESTROY
        onDestroyHooks.forEach(Runnable::run)
    }

    companion object {
        private const val NONE = 0
        private const val CREATE = 1
        private const val DESTROY = 2
    }
}