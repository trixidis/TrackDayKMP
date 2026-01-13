package model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
@Immutable
data class TrackPresentation(
    val name: String,
    val imgUrl: String
)
