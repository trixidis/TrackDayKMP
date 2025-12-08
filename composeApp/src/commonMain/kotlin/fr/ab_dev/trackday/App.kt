package fr.ab_dev.trackday

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import model.BottomNavBarFeature
import model.NavigationStore
import org.koin.compose.koinInject
import ui.BottomNavBar
import ui.TrackListView

@Composable
fun App() {
    val navStore = koinInject<NavigationStore>()
    val featureSelected by navStore.feature.collectAsStateWithLifecycle()


    MaterialTheme {
        Scaffold(
            bottomBar =
                {
                    BottomNavBar()
                }) {
            when (featureSelected) {
                BottomNavBarFeature.Organisers -> Box {} //TODO : Add organisers feature here
                BottomNavBarFeature.Tracks -> TrackListView()
            }

        }
    }
}