package use_case

import kotlinx.coroutines.test.runTest
import model.OrganiserDomain
import repository.OrganiserRepository
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetOrganisersUseCaseImplTest {
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
            val fakeRepository = FakeOrganiserRepository(expectedOrganisers)
            val useCase = GetOrganisersUseCaseImpl(fakeRepository)

            val result = useCase()

            assertEquals(expectedOrganisers, result)
        }

    @Test
    fun `invoke returns empty list when repository has no organisers`() =
        runTest {
            val fakeRepository = FakeOrganiserRepository(emptyList())
            val useCase = GetOrganisersUseCaseImpl(fakeRepository)

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
            val fakeRepository = FakeOrganiserRepository(singleOrganiser)
            val useCase = GetOrganisersUseCaseImpl(fakeRepository)

            val result = useCase()

            assertEquals(1, result.size)
            assertEquals("Track Days", result.first().name)
            assertEquals("UK", result.first().country)
        }
}

private class FakeOrganiserRepository(
    private val organisers: List<OrganiserDomain>,
) : OrganiserRepository {
    override suspend fun getOrganisers(): List<OrganiserDomain> = organisers
}
