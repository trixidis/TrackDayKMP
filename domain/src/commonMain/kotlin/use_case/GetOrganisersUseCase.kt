package use_case

import model.OrganiserDomain

interface GetOrganisersUseCase {
    suspend operator fun invoke(): List<OrganiserDomain>
}
