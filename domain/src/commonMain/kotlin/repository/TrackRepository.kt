package repository

import model.TrackDomain

interface TrackRepository {
    suspend fun getTracks(): List<TrackDomain>
}
