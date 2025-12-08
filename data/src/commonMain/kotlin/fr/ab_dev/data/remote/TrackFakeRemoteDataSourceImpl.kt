package fr.ab_dev.data.remote

import fr.ab_dev.data.model.Organiser
import fr.ab_dev.data.model.TrackData

class TrackFakeRemoteDataSourceImpl() : TrackDayRemoteDataSource {
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

    override suspend fun getOrganisers(): List<Organiser> =
        listOf(
            Organiser(

                id = "11",
                color = "d29c54",
                img = "img/4/2021/12/27220640.jpg",
                title = "Team Performance 55",
                country = "FR",
                country2 = "france",
                trackDayNB = 2,
                url = "https://www.calendrier-piste.fr/organisateur/11-Team-Performance-55",
                likeIt = false,
                followIt = false,
            )
        )
}