package use_case

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import di.domainModules
import kotlinx.coroutines.test.runTest
import model.OrganiserDomain
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import repository.OrganiserRepository
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetOrganisersUseCaseImplTest : KoinTest {

    private val useCase: GetOrganisersUseCase by inject()
    private val mockRepository: OrganiserRepository = mock()

    @BeforeTest
    fun setUp() {
        startKoin {
            modules(
                domainModules,
                module {
                    single<OrganiserRepository> { mockRepository }
                }
            )
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `invoke returns list of organisers from repository`() =
        runTest {
            val expectedOrganisers =
                listOf(
                    OrganiserDomain(name = "RSR Spa", country = "Belgium", imgUrl = "https://example.com/rsr.png"),
                    OrganiserDomain(
                        name = "Sport Auto",
                        country = "France",
                        imgUrl = "https://example.com/sportauto.png"
                    ),
                )
            everySuspend { mockRepository.getOrganisers() } returns expectedOrganisers

            val result = useCase()

            assertEquals(expectedOrganisers, result)
        }

    @Test
    fun `invoke returns empty list when repository has no organisers`() =
        runTest {
            everySuspend { mockRepository.getOrganisers() } returns emptyList()

            val result = useCase()

            assertTrue(result.isEmpty())
        }

    @Test
    fun `invoke returns single organiser correctly`() =
        runTest {
            val singleOrganiser =
                listOf(
                    OrganiserDomain(name = "Track Days", country = "UK", imgUrl = "https://example.com/trackdays.png"),
                )
            everySuspend { mockRepository.getOrganisers() } returns singleOrganiser

            val result = useCase()

            assertEquals(1, result.size)
            assertEquals("Track Days", result.first().name)
            assertEquals("UK", result.first().country)
        }
}
