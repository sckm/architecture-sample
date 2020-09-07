package com.github.sckm.flux.dispatcher

import com.github.sckm.flux.BuildConfig
import com.github.sckm.flux.MyEventBusIndex
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class Dispatcher {
    private val bus = EventBus.builder()
        .addIndex(MyEventBusIndex())
        .throwSubscriberException(BuildConfig.DEBUG)
        .build()

    fun dispatch(payload: Any?) {
        bus.post(payload)
    }

    fun register(observer: Any) {
        bus.register(observer)
    }

    fun unregister(observer: Any) {
        bus.unregister(observer)
    }

    @Deprecated("delete this method if other subscriber method exists")
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: Object) {
    }
}