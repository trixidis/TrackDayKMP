package ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import model.TrackDetailPresentation
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import trackday.feature.track_detail.generated.resources.Res
import trackday.feature.track_detail.generated.resources.error_placeholder
import trackday.feature.track_detail.generated.resources.track_details_title
import vm.TrackDetailViewModel

@Composable
fun TrackDetailView(
    trackName: String,
    onNavigateBack: () -> Unit = {}
) {
    TrackDetailViewWithViewModel(
        trackName = trackName,
        onNavigateBack = onNavigateBack,
    )
}

@Composable
fun TrackDetailViewWithViewModel(
    trackName: String,
    onNavigateBack: () -> Unit,
    vm: TrackDetailViewModel = koinViewModel { parametersOf(trackName) }
) {
    val state = vm.uiState.collectAsStateWithLifecycle()

    @OptIn(ExperimentalMaterial3Api::class)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.track_details_title)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        when {
            state.value.isLoading -> {
                LoadingView(padding)
            }

            state.value.error != null -> {
                state.value.error?.message?.let {
                    ErrorView(padding, it)
                }
            }

            state.value.track != null -> {
                state.value.track?.let { track ->
                    TrackDetailContent(padding, track)
                }
            }
        }
    }
}

@Composable
fun LoadingView(padding: PaddingValues) {
    Box(
        modifier =
            Modifier
                .padding(padding)
                .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp)
        )
    }
}

@Composable
fun ErrorView(
    padding: PaddingValues,
    errorMessage: String
) {
    Box(
        modifier =
            Modifier
                .padding(padding)
                .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(Res.string.error_placeholder, errorMessage),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun TrackDetailContent(
    padding: PaddingValues,
    track: TrackDetailPresentation
) {
    Column(
        modifier =
            Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Track Image
        Card(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(250.dp)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = track.imgUrl,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }

        // Track Name
        Text(
            text = track.name,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        // Country
        Card(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Country",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = track.country,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
fun TrackDetailPreview() {
    TrackDetailContent(
        padding = PaddingValues(all = 16.dp),
        track =
            TrackDetailPresentation(
                name = "Silverstone Circuit",
                country = "United Kingdom",
                imgUrl = ""
            )
    )
}

@Preview
@Composable
fun TrackDetailLoadingPreview() {
    LoadingView(padding = PaddingValues(all = 16.dp))
}

@Preview
@Composable
fun TrackDetailErrorPreview() {
    ErrorView(padding = PaddingValues(all = 16.dp), errorMessage = "Failed to load track")
}
