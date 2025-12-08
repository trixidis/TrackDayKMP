package model

data class BottomNavBarState(
    val featureSelected: BottomNavBarFeature
)

sealed class BottomNavBarFeature {
    data object Tracks : BottomNavBarFeature()
    data object Organisers : BottomNavBarFeature()
}