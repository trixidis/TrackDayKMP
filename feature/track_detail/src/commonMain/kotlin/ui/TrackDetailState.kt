package ui

import model.TrackDetailPresentation

data class TrackDetailState(
    val track: TrackDetailPresentation? = null,
    val isLoading: Boolean = false,
    val error: Throwable? = null,
)