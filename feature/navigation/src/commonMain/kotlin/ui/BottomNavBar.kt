package ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import model.BottomNavBarFeature
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import trackday.feature.navigation.generated.resources.Res
import vm.BottomNavBarViewModel

@Composable
fun BottomNavBar(modifier: Modifier = Modifier) {
    if (LocalInspectionMode.current) {
        BottomNavBar(
            modifier = modifier,
            isTrackSelected = true,
            isOrganisersSelected = false,
            onTrackSelected = {},
            onOrganisersSelected = {},
        )
    } else {
        BottomNavBarWithVm(modifier = modifier)
    }
}

@Composable
private fun BottomNavBarWithVm(
    modifier: Modifier = Modifier,
    vm: BottomNavBarViewModel = koinViewModel<BottomNavBarViewModel>()
) {
    val state by vm.uiState.collectAsStateWithLifecycle()

    val actionHandler =
        object : ActionsHandler {
            override fun onTrackSelected() {
                vm.onTrackSelected()
            }

            override fun onOrganisersSelected() {
                vm.onOrganisersSelected()
            }
        }

    val isTrackSelected by remember {
        derivedStateOf {
            state.featureSelected == BottomNavBarFeature.Tracks
        }
    }

    val isOrganisersSelected by remember {
        derivedStateOf {
            state.featureSelected == BottomNavBarFeature.Organisers
        }
    }

    BottomNavBar(
        modifier = modifier,
        isTrackSelected = isTrackSelected,
        isOrganisersSelected = isOrganisersSelected,
        onTrackSelected = actionHandler::onTrackSelected,
        onOrganisersSelected = actionHandler::onOrganisersSelected,
    )
}

@Composable
private fun BottomNavBar(
    modifier: Modifier = Modifier,
    isTrackSelected: Boolean,
    isOrganisersSelected: Boolean,
    onTrackSelected: () -> Unit,
    onOrganisersSelected: () -> Unit,
) {
    NavigationBar(modifier = modifier) {
        NavigationBarItem(
            selected = isTrackSelected,
            onClick = onTrackSelected,
            icon = {
                AsyncImage(
                    model = Res.getUri("drawable/tracks.svg"),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        )
        NavigationBarItem(
            selected = isOrganisersSelected,
            onClick = onOrganisersSelected,
            icon = {
                AsyncImage(
                    model = Res.getUri("drawable/organisers.svg"),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        )
    }
}

private interface ActionsHandler {
    fun onTrackSelected()

    fun onOrganisersSelected()
}

@Preview
@Composable
fun BottomNavBarPreview() {
    BottomNavBar()
}
