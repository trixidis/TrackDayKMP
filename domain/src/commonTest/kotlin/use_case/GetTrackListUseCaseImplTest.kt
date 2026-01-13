package use_case

import kotlinx.coroutines.test.runTest
import model.TrackDomain
import repository.TrackRepository
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetTrackListUseCaseImplTest {

    @Test
    fun `invoke returns list of tracks from repository`() =
        runTest {
            val expectedTracks =
                listOf(
                    TrackDomain(
                        name = "Spa-Francorchamps",
                        country = "Belgium",
                        imgUrl = "https://example.com/spa.png"
                    ),
                    TrackDomain(
                        name = "Nurburgring",
                        country = "Germany",
                        imgUrl = "https://example.com/nurburgring.png"
                    ),
                )
            val fakeRepository = FakeTrackRepository(expectedTracks)
            val useCase = GetTrackListUseCaseImpl(fakeRepository)

            val result = useCase()

            assertEquals(expectedTracks, result)
        }

    @Test
    fun `invoke returns empty list when repository has no tracks`() =
        runTest {
            val fakeRepository = FakeTrackRepository(emptyList())
            val useCase = GetTrackListUseCaseImpl(fakeRepository)

            val result = useCase()

            assertTrue(result.isEmpty())
        }

    @Test
    fun `invoke returns single track correctly`() =
        runTest {
            val singleTrack =
                listOf(
                    TrackDomain(name = "Monza", country = "Italy", imgUrl = "https://example.com/monza.png"),
                )
            val fakeRepository = FakeTrackRepository(singleTrack)
            val useCase = GetTrackListUseCaseImpl(fakeRepository)

            val result = useCase()

            assertEquals(1, result.size)
            assertEquals("Monza", result.first().name)
            assertEquals("Italy", result.first().country)
        }
}

private class FakeTrackRepository(
    private val tracks: List<TrackDomain>,
) : TrackRepository {
    override suspend fun getTracks(): List<TrackDomain> = tracks
}
