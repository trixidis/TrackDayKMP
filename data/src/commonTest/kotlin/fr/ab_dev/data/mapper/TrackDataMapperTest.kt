package fr.ab_dev.data.mapper

import fr.ab_dev.data.model.TrackData
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TrackDataMapperTest {
    @Test
    fun `toDomain maps TrackData to TrackDomain correctly`() {
        val trackData =
            TrackData(
                name = "Spa-Francorchamps",
                country = "Belgium",
                iconUrl = "https://example.com/spa.png",
            )

        val result = trackData.toDomain()

        assertEquals("Spa-Francorchamps", result.name)
        assertEquals("Belgium", result.country)
        assertEquals("https://example.com/spa.png", result.imgUrl)
    }

    @Test
    fun `toDomain maps iconUrl to imgUrl`() {
        val trackData =
            TrackData(
                name = "Test Track",
                country = "France",
                iconUrl = "https://cdn.example.com/track-icon.jpg",
            )

        val result = trackData.toDomain()

        assertEquals("https://cdn.example.com/track-icon.jpg", result.imgUrl)
    }

    @Test
    fun `toDomain on list maps all items correctly`() {
        val trackDataList =
            listOf(
                TrackData(name = "Monza", country = "Italy", iconUrl = "https://example.com/monza.png"),
                TrackData(name = "Silverstone", country = "UK", iconUrl = "https://example.com/silverstone.png"),
                TrackData(name = "Le Mans", country = "France", iconUrl = "https://example.com/lemans.png"),
            )

        val result = trackDataList.toDomain()

        assertEquals(3, result.size)
        assertEquals("Monza", result[0].name)
        assertEquals("Italy", result[0].country)
        assertEquals("Silverstone", result[1].name)
        assertEquals("UK", result[1].country)
        assertEquals("Le Mans", result[2].name)
        assertEquals("France", result[2].country)
    }

    @Test
    fun `toDomain on empty list returns empty list`() {
        val emptyList = emptyList<TrackData>()

        val result = emptyList.toDomain()

        assertTrue(result.isEmpty())
    }

    @Test
    fun `toDomain preserves order of items`() {
        val trackDataList =
            listOf(
                TrackData(name = "Track A", country = "Country A", iconUrl = "url-a"),
                TrackData(name = "Track B", country = "Country B", iconUrl = "url-b"),
                TrackData(name = "Track C", country = "Country C", iconUrl = "url-c"),
            )

        val result = trackDataList.toDomain()

        assertEquals("Track A", result[0].name)
        assertEquals("Track B", result[1].name)
        assertEquals("Track C", result[2].name)
    }
}
