package ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import model.TrackPresentation
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import trackday.feature.track_list.generated.resources.Res
import trackday.feature.track_list.generated.resources.no_track_to_display
import vm.TrackListViewModel

@Composable
fun TrackListView() {
    if (LocalInspectionMode.current) {
        Scaffold { padding ->
            Text("test", modifier = Modifier.padding(padding))
        }
    } else {
        TrackListViewWithViewModel()
    }
}

@Composable
fun TrackListViewWithViewModel(
    vm: TrackListViewModel = koinViewModel<TrackListViewModel>()
) {

    val state = vm.uiState.collectAsStateWithLifecycle()

    if (state.value.error != null) {
        NoTrackToDisplayView()
    } else {
        state.value.tracks.let { trackList ->
            TrackList(trackList)
        }
    }

}

@Composable
fun NoTrackToDisplayView() {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(paddingValues),
                text = stringResource(Res.string.no_track_to_display)
            )
        }
    }
}

@Composable
private fun TrackList(
    trackList: List<TrackPresentation>,
) {
    Scaffold { padding ->
        if (trackList.isNotEmpty()) {
            LazyColumn(modifier = Modifier.padding(padding)) {
                items(trackList) { track ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(32.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                modifier = Modifier.height(60.dp),
                                model = track.imgUrl,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = track.name,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun TrackListPreview() {
    TrackList(
        listOf(
            TrackPresentation(
                name = "Ales",
                imgUrl = ""
            )
        )
    )
}

@Preview
@Composable
fun NoTrackPreview() {
    NoTrackToDisplayView()
}