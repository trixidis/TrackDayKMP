package fr.ab_dev.data.mapper

import fr.ab_dev.data.model.Organiser
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class OrganiserDataMapperTest {
    @Test
    fun `toDomain maps Organiser to OrganiserDomain correctly`() {
        val organiser =
            createOrganiser(
                title = "RSR Spa",
                country = "Belgium",
                img = "https://example.com/rsr.png",
            )

        val result = organiser.toDomain()

        assertEquals("RSR Spa", result.name)
        assertEquals("Belgium", result.country)
        assertEquals("https://example.com/rsr.png", result.imgUrl)
    }

    @Test
    fun `toDomain maps title to name`() {
        val organiser = createOrganiser(title = "Track Days UK")

        val result = organiser.toDomain()

        assertEquals("Track Days UK", result.name)
    }

    @Test
    fun `toDomain maps img to imgUrl`() {
        val organiser = createOrganiser(img = "https://cdn.example.com/organiser.jpg")

        val result = organiser.toDomain()

        assertEquals("https://cdn.example.com/organiser.jpg", result.imgUrl)
    }

    @Test
    fun `toDomain on list maps all items correctly`() {
        val organiserList =
            listOf(
                createOrganiser(title = "Org 1", country = "France", img = "img1.png"),
                createOrganiser(title = "Org 2", country = "Germany", img = "img2.png"),
                createOrganiser(title = "Org 3", country = "Spain", img = "img3.png"),
            )

        val result = organiserList.toDomain()

        assertEquals(3, result.size)
        assertEquals("Org 1", result[0].name)
        assertEquals("France", result[0].country)
        assertEquals("Org 2", result[1].name)
        assertEquals("Germany", result[1].country)
        assertEquals("Org 3", result[2].name)
        assertEquals("Spain", result[2].country)
    }

    @Test
    fun `toDomain on empty list returns empty list`() {
        val emptyList = emptyList<Organiser>()

        val result = emptyList.toDomain()

        assertTrue(result.isEmpty())
    }

    @Test
    fun `toDomain preserves order of items`() {
        val organiserList =
            listOf(
                createOrganiser(title = "Alpha Org"),
                createOrganiser(title = "Beta Org"),
                createOrganiser(title = "Gamma Org"),
            )

        val result = organiserList.toDomain()

        assertEquals("Alpha Org", result[0].name)
        assertEquals("Beta Org", result[1].name)
        assertEquals("Gamma Org", result[2].name)
    }

    @Test
    fun `toDomain ignores extra Organiser fields`() {
        val organiser =
            Organiser(
                id = "org-123",
                color = "#FF0000",
                img = "test.png",
                title = "Test Org",
                country = "France",
                country2 = "Monaco",
                trackDayNB = 42,
                url = "https://example.com",
                likeIt = true,
                followIt = false,
            )

        val result = organiser.toDomain()

        assertEquals("Test Org", result.name)
        assertEquals("France", result.country)
        assertEquals("test.png", result.imgUrl)
    }

    private fun createOrganiser(
        id: String = "default-id",
        color: String = "#000000",
        img: String = "default.png",
        title: String = "Default Title",
        country: String = "Default Country",
        country2: String = "",
        trackDayNB: Int = 0,
        url: String = "",
        likeIt: Boolean = false,
        followIt: Boolean = false,
    ) = Organiser(
        id = id,
        color = color,
        img = img,
        title = title,
        country = country,
        country2 = country2,
        trackDayNB = trackDayNB,
        url = url,
        likeIt = likeIt,
        followIt = followIt,
    )
}
