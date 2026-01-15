package fr.ab_dev.data.remote

import fr.ab_dev.data.model.Organiser
import fr.ab_dev.data.model.TrackData

class FakeTrackDayRemoteDataSource : TrackDayRemoteDataSource {
    override suspend fun getTracks(): List<TrackData> =
        listOf(
            TrackData(
                name = "Circuit de Spa-Francorchamps",
                country = "Belgium",
                iconUrl = "https://example.com/spa.png",
            ),
            TrackData(
                name = "Nürburgring Nordschleife",
                country = "Germany",
                iconUrl = "https://example.com/nurburgring.png",
            ),
            TrackData(
                name = "Circuit Paul Ricard",
                country = "France",
                iconUrl = "https://example.com/paulricard.png",
            ),
            TrackData(
                name = "Silverstone Circuit",
                country = "United Kingdom",
                iconUrl = "https://example.com/silverstone.png",
            ),
            TrackData(
                name = "Autodromo Nazionale Monza",
                country = "Italy",
                iconUrl = "https://example.com/monza.png",
            ),
        )

    override suspend fun getOrganisers(): List<Organiser> =
        listOf(
            Organiser(
                id = "1",
                color = "#FF5733",
                img = "https://example.com/org1.png",
                title = "RSR Spa",
                country = "Belgium",
                country2 = "BE",
                trackDayNB = 15,
                url = "https://rsrspa.com",
                likeIt = true,
                followIt = false,
            ),
            Organiser(
                id = "2",
                color = "#33FF57",
                img = "https://example.com/org2.png",
                title = "GP Days",
                country = "Germany",
                country2 = "DE",
                trackDayNB = 25,
                url = "https://gpdays.com",
                likeIt = false,
                followIt = true,
            ),
            Organiser(
                id = "3",
                color = "#3357FF",
                img = "https://example.com/org3.png",
                title = "Track Addict",
                country = "France",
                country2 = "FR",
                trackDayNB = 10,
                url = "https://trackaddict.fr",
                likeIt = true,
                followIt = true,
            ),
        )
}
