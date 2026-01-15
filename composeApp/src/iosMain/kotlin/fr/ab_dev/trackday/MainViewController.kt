package fr.ab_dev.trackday

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() =
    ComposeUIViewController(
        configure = {
            initKoin(useFakeData = BuildKonfig.USE_FAKE_DATA)
        }
    ) { App() }
