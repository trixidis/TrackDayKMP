package ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import model.TrackPresentation
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import trackday.feature.track_list.generated.resources.Res
import trackday.feature.track_list.generated.resources.no_track_to_display
import vm.TrackListViewModel

@Composable
fun TrackListView(padding: PaddingValues) {
    if (LocalInspectionMode.current) {
        TrackListPreview()
    } else {
        TrackListViewWithViewModel(padding = padding)
    }
}

@Composable
fun TrackListViewWithViewModel(
    padding: PaddingValues,
    vm: TrackListViewModel = koinViewModel<TrackListViewModel>()
) {

    val state = vm.uiState.collectAsStateWithLifecycle()

    if (state.value.error != null) {
        NoTrackToDisplayView(padding)
    } else {
        state.value.tracks.let { trackList ->
            TrackList(padding, trackList)
        }
    }

}

@Composable
fun NoTrackToDisplayView(padding: PaddingValues) {
    Box(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(padding),
            text = stringResource(Res.string.no_track_to_display)
        )
    }

}

@Composable
private fun TrackList(
    padding: PaddingValues,
    trackList: List<TrackPresentation>,
) {
    if (trackList.isNotEmpty()) {
        LazyVerticalGrid(
            modifier = Modifier.padding(padding),
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(all = 12.dp)
        ) {
            items(trackList) { track ->
                Card(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(vertical = 26.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth(),
                            model = track.imgUrl,
                            contentScale = ContentScale.Crop,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = track.name,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun TrackListPreview() {
    TrackList(
        PaddingValues(all = 1.dp),
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
    NoTrackToDisplayView(PaddingValues(all = 1.dp))
}