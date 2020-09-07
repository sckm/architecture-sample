package com.github.sckm.flux.events

import com.github.sckm.flux.models.LoadState

data class EpisodeLoadStateChangedEvent(
    val state: LoadState
)