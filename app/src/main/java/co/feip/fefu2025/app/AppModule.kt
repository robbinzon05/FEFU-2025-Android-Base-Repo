import co.feip.fefu2025.data.mock.DataProvider
import co.feip.fefu2025.data.repository.RepositoryCardRepositoryImpl
import co.feip.fefu2025.data.repository.RepositoryScreenRepositoryImpl
import co.feip.fefu2025.domain.repository.RepositoryCardRepository
import co.feip.fefu2025.domain.repository.RepositoryScreenRepository
import co.feip.fefu2025.domain.use_cases.GetAllRepositoriesUseCase
import co.feip.fefu2025.domain.use_cases.GetRepositoryScreenUseCase
import co.feip.fefu2025.domain.use_cases.GetStarredRepositoriesUseCase
import co.feip.fefu2025.presentation.screen.main.MainPageViewModel
import co.feip.fefu2025.presentation.screen.repo.RepositoryScreenViewModel
import co.feip.fefu2025.presentation.screen.starred.StarredRepositoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { DataProvider() }
    single<RepositoryCardRepository> { RepositoryCardRepositoryImpl(get()) }
    single<RepositoryScreenRepository> { RepositoryScreenRepositoryImpl(get()) }

    single { GetAllRepositoriesUseCase(get()) }
    single { GetStarredRepositoriesUseCase(get()) }
    single { GetRepositoryScreenUseCase(get()) }

    viewModel {
        MainPageViewModel(
            getStarredRepositoriesUseCase = get(),
            getAllRepositoriesUseCase = get()
        )
    }

    viewModel { (cardId: Int) ->
        RepositoryScreenViewModel(
            getRepositoryScreenUseCase = get(),
            cardId = cardId
        )
    }

    viewModel {
        StarredRepositoriesViewModel(
            getStarredRepositoriesUseCase = get()
        )
    }
}
