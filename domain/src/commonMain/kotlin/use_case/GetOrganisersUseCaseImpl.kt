package use_case

import model.OrganiserDomain
import repository.OrganiserRepository

class GetOrganisersUseCaseImpl(
    private val organiserRepository: OrganiserRepository
) : GetOrganisersUseCase {
    override suspend fun invoke(): List<OrganiserDomain> = organiserRepository.getOrganisers()
}
