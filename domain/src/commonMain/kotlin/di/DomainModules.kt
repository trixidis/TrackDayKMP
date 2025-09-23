package di

import org.koin.dsl.module
import use_case.GetTrackListUseCase
import use_case.GetTrackListUseCaseImpl

val domainModules = module {
    factory<GetTrackListUseCase> {
        GetTrackListUseCaseImpl(tracksRemoteDataSource = get())
    }
}