package fr.ab_dev.data.tracks.remote

import fr.ab_dev.data.model.TrackData

class TrackFakeRemoteDataSourceImpl() : TracksRemoteDataSource {
    override suspend fun getTracks(): List<TrackData> =
        listOf(
            TrackData(
                name = "Ales",
                country = "Fr",
                iconUrl = "https://www.pole-mecanique.fr/wp-content/uploads/2016/11/Pole-Mecanique-vueaerienne-vueweb-1030x773.jpg"
            ), TrackData(
                name = "Magny-Cours GP",
                country = "FRA",
                iconUrl = "https://www.lrs-formula.com/c/55-pdt_1920/circuit-magny-cours-gp.jpg"
            )
        )
}