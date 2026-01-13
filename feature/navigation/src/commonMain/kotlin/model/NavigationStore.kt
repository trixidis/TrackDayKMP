package model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NavigationStore {
    private val _feature = MutableStateFlow<BottomNavBarFeature>(BottomNavBarFeature.Tracks)
    val feature = _feature.asStateFlow()

    fun select(feature: BottomNavBarFeature) {
        _feature.value = feature
    }
}
