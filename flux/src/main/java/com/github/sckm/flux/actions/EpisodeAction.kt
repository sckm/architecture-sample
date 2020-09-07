package com.github.sckm.flux.actions

import com.github.sckm.flux.dispatcher.Dispatcher
import com.github.sckm.flux.events.EpisodeLoadStateChangedEvent
import com.github.sckm.flux.models.LoadState

class EpisodeAction(
    private val dispatcher: Dispatcher
) {
    fun load() {
        dispatcher.dispatch(EpisodeLoadStateChangedEvent(LoadState.LOADING))
        dispatcher.dispatch(EpisodeLoadStateChangedEvent(LoadState.FINISHED))
    }
}
