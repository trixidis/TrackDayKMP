package di

import org.koin.dsl.module
import use_case.GetOrganisersUseCase
import use_case.GetOrganisersUseCaseImpl
import use_case.GetTrackListUseCase
import use_case.GetTrackListUseCaseImpl

val domainModules = module {
    factory<GetTrackListUseCase> {
        GetTrackListUseCaseImpl(trackDayRemoteDataSource = get())
    }

    factory<GetOrganisersUseCase> {
        GetOrganisersUseCaseImpl(trackDayRemoteDataSource = get())
    }
}