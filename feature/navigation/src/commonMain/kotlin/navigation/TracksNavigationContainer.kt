package navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import model.Route
import ui.TrackDetailView
import ui.TrackListView

@Composable
fun TracksNavigationContainer(
    paddingValues: PaddingValues,
) {

    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(Route.TrackDetail::class, Route.TrackDetail.serializer())
                    subclass(Route.TrackList::class, Route.TrackList.serializer())

                }
            }
        },
        Route.TrackList
    )

    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator ()
        ),
        entryProvider = { key ->
            when (key) {
                is Route.TrackList -> {
                    NavEntry(key) {
                        TrackListView(
                            padding = paddingValues,
                            onNavigateToDetail = { trackName ->
                                backStack.add(Route.TrackDetail(trackName))
                            }
                        )
                    }
                }

                is Route.TrackDetail -> {
                    NavEntry(key) {
                        TrackDetailView(
                            trackName = key.trackName,
                            onNavigateBack = {
                                backStack.remove(backStack.last())
                            }
                        )
                    }
                }

                else -> throw IllegalArgumentException("Unknown key: $key")
            }
        }
    )
}