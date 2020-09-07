package com.github.sckm.flux.components.widget

// TODO LiveDataを使うようになったので不要？ 必要ないなら削除
class CompositeDisposer : Disposer(),
    Disposer.Group {
    private val target = mutableSetOf<Disposable>()

    override fun add(vararg disposables: Disposable) {
        target.addAll(disposables)
    }

    override fun remove(vararg disposables: Disposable) {
        target.removeAll(disposables)
    }

    override fun dispose() {
        if (target.isNotEmpty()) {
            target.forEach(Disposable::dispose)
            target.clear()
        }
    }
}