package com.github.sckm.flux.components.widget

abstract class Disposer : Disposable {
    fun addTo(group: Group) {
        group.add(this)
    }

    interface Group : Disposable {
        fun add(vararg disposables: Disposable)
        fun remove(vararg disposables: Disposable)
    }

    companion object {
        fun from(d: Disposable): Disposer {
            return object : Disposer() {
                override fun dispose() {
                    d.dispose()
                }
            }
        }
    }
}