package model

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {

    @Serializable
    data object OrganiserList : Route, NavKey

    @Serializable
    data object TrackList : Route, NavKey

    @Serializable
    data class TrackDetail(val trackName: String) : Route, NavKey

}