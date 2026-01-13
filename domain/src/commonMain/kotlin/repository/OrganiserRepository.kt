package repository

import model.OrganiserDomain

interface OrganiserRepository {
    suspend fun getOrganisers(): List<OrganiserDomain>
}
