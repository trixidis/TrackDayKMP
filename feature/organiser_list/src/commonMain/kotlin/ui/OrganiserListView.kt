package ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import model.OrganiserUi
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import trackday.feature.organiser_list.generated.resources.Res
import trackday.feature.organiser_list.generated.resources.no_organiser_to_display
import vm.OrganiserListViewModel


@Composable
fun OrganiserListView(padding: PaddingValues) {
    if (LocalInspectionMode.current) {
        OrganiserListPreview()
    } else {
        OrganiserListViewWithViewModel(padding = padding)
    }
}

@Composable
fun OrganiserListViewWithViewModel(
    padding: PaddingValues,
    vm: OrganiserListViewModel = koinViewModel<OrganiserListViewModel>()
) {

    val state by vm.uiState.collectAsStateWithLifecycle()

    if (state.error != null) {
        NoOrganiserToDisplayView(padding)
    } else {
        state.organisers.let { organiserList ->
            OrganiserList(padding, organiserList)
        }
    }

}

@Composable
fun NoOrganiserToDisplayView(padding: PaddingValues) {
    Box(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(padding),
            text = stringResource(Res.string.no_organiser_to_display)
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OrganiserList(
    padding: PaddingValues,
    organiserList: List<OrganiserUi>,
) {

    if (organiserList.isNotEmpty()) {

        Column {
            LazyVerticalGrid(
                modifier = Modifier.padding(padding),
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(all = 12.dp)
            ) {
                items(organiserList) { organiser ->
                    Card(
                        modifier = Modifier
                            .aspectRatio(0.75f)
                            .padding(horizontal = 12.dp)
                            .padding(vertical = 26.dp)
                    ) {
                        Column(
                            modifier =Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                model = organiser.imgUrl,
                                contentScale = ContentScale.Crop,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = organiser.name,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun OrganiserListPreview() {
    OrganiserList(
        PaddingValues(all = 1.dp),
        listOf(
            OrganiserUi(
                name = "MGB",
                imgUrl = ""
            )
        )
    )
}

@Preview
@Composable
fun NoOrganiserPreview() {
    NoOrganiserToDisplayView(PaddingValues(all = 1.dp))
}