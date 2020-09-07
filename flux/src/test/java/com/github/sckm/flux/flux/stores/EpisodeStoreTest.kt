package com.github.sckm.flux.stores

import com.github.sckm.flux.components.widget.LifecycleHook
import com.github.sckm.flux.components.widget.ScreenLifecycleHook
import com.github.sckm.flux.dispatcher.Dispatcher
import com.github.sckm.flux.events.EpisodeLoadStateChangedEvent
import com.github.sckm.flux.models.LoadState
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class EpisodeStoreTest {

    lateinit var store: EpisodeStore

    @Before
    fun setUp() {
        val dispatcher = Dispatcher()
        val hook = mock<LifecycleHook>()
        store = EpisodeStore(dispatcher, hook)
    }

    @Test
    fun receiveLoadStateChanged() {
        store.on(EpisodeLoadStateChangedEvent(LoadState.LOADING))

        assertThat(store.loadStateLiveData.value).isEqualTo(LoadState.LOADING)
    }
}

// TODO Eventを受け取れているかのテストは別のstoreを作って書く
@RunWith(RobolectricTestRunner::class)
class EpisodeStoreDispatchTest {

    @Test
    fun receiveEvent_whenLifecycleOnCreated() {
        val dispatcher = Dispatcher()
        val hook = ScreenLifecycleHook()
        val store = spy(EpisodeStore(dispatcher, hook))

        hook.dispatchOnCreate()
        dispatcher.dispatch(EpisodeLoadStateChangedEvent(LoadState.LOADING))

        assertThat(store.loadStateLiveData.value).isEqualTo(LoadState.LOADING)
    }

    @Test
    fun notReceiveEvent_whenLifecycleOnDestroyed() {
        val dispatcher = Dispatcher()
        val hook = ScreenLifecycleHook()
        val store = spy(EpisodeStore(dispatcher, hook))

        hook.dispatchOnCreate()
        hook.dispatchOnDestroy()
        dispatcher.dispatch(EpisodeLoadStateChangedEvent(LoadState.LOADING))

        assertThat(store.loadStateLiveData.value).isEqualTo(LoadState.INITIALIZED)
    }

    @Test
    fun notReceiveEvent_whenLifecycleInitialState() {
        val dispatcher = Dispatcher()
        val hook = ScreenLifecycleHook()
        val store = spy(EpisodeStore(dispatcher, hook))

        dispatcher.dispatch(EpisodeLoadStateChangedEvent(LoadState.LOADING))

        assertThat(store.loadStateLiveData.value).isEqualTo(LoadState.INITIALIZED)
    }
}