package com.github.sckm.flux.actions

import com.github.sckm.flux.dispatcher.Dispatcher
import com.github.sckm.flux.events.EpisodeLoadStateChangedEvent
import com.github.sckm.flux.models.LoadState
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class EpisodeActionTest {
    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Captor
    private lateinit var eventCaptor: ArgumentCaptor<Any>

    @Mock
    private lateinit var mockDispatcher: Dispatcher

    lateinit var action: EpisodeAction

    @Before
    fun setUp() {
        action = EpisodeAction(mockDispatcher)
    }

    @Test
    fun load() {
        action.load()

        verify(mockDispatcher, times(2)).dispatch(eventCaptor.capture())
        val (loading, finished) = eventCaptor.allValues

        assertThat(loading).isInstanceOf(EpisodeLoadStateChangedEvent::class.java)
        loading as EpisodeLoadStateChangedEvent
        assertThat(loading.state).isEqualTo(LoadState.LOADING)

        assertThat(finished).isInstanceOf(EpisodeLoadStateChangedEvent::class.java)
        finished as EpisodeLoadStateChangedEvent
        assertThat(finished.state).isEqualTo(LoadState.FINISHED)
    }
}
