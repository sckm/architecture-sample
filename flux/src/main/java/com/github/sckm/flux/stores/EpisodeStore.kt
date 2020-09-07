package com.github.sckm.flux.stores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.sckm.flux.components.widget.LifecycleHook
import com.github.sckm.flux.dispatcher.Dispatcher
import com.github.sckm.flux.events.EpisodeLoadStateChangedEvent
import com.github.sckm.flux.models.LoadState
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class EpisodeStore(
    dispatcher: Dispatcher,
    hook: LifecycleHook
) {
    private val mutableLoadState = MutableLiveData<LoadState>()
    val loadStateLiveData: LiveData<LoadState>
        get() = mutableLoadState

    init {
        // TODO 宣言時に初期化できるようにする
        mutableLoadState.value = LoadState.INITIALIZED

        hook.onCreate(Runnable { dispatcher.register(this) })
        hook.onDestroy(Runnable { dispatcher.unregister(this) })
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun on(event: EpisodeLoadStateChangedEvent) {
        mutableLoadState.value = event.state
    }
}