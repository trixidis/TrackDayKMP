package use_case

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import di.domainModules
import kotlinx.coroutines.test.runTest
import model.TrackDomain
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import repository.TrackRepository
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetTrackListUseCaseImplTest : KoinTest {

    private val useCase: GetTrackListUseCase by inject()
    private val mockRepository: TrackRepository = mock()

    @BeforeTest
    fun setUp() {
        startKoin {
            modules(
                domainModules,
                module {
                    single<TrackRepository> { mockRepository }
                }
            )
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

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
            everySuspend { mockRepository.getTracks() } returns expectedTracks

            val result = useCase()

            assertEquals(expectedTracks, result)
        }

    @Test
    fun `invoke returns empty list when repository has no tracks`() =
        runTest {
            everySuspend { mockRepository.getTracks() } returns emptyList()

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
            everySuspend { mockRepository.getTracks() } returns singleTrack

            val result = useCase()

            assertEquals(1, result.size)
            assertEquals("Monza", result.first().name)
            assertEquals("Italy", result.first().country)
        }
}
