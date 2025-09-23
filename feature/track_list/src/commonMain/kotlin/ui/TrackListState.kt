package ui

import model.TrackPresentation

data class TrackListState(
    val tracks: List<TrackPresentation>,
    val error: Throwable? = null,
)