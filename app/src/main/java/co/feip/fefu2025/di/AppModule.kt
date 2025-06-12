package co.feip.fefu2025.di

import co.feip.fefu2025.data.repository.ProjectRepositoryImpl
import co.feip.fefu2025.domain.repository.ProjectRepository
import co.feip.fefu2025.domain.use_cases.GetProjectsPagingUseCase
import co.feip.fefu2025.domain.use_cases.GetRepositoryScreenUseCase
import co.feip.fefu2025.domain.use_cases.GetStarredPagingUseCase
import co.feip.fefu2025.domain.use_cases.SearchRepositoriesUseCase
import co.feip.fefu2025.presentation.screen.main.MainPageViewModel
import co.feip.fefu2025.presentation.screen.repo.RepositoryScreenViewModel
import co.feip.fefu2025.presentation.screen.starred.StarredRepositoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    includes(networkModule)

    single<ProjectRepository> { ProjectRepositoryImpl(get()) }

    factory { GetProjectsPagingUseCase(get()) }
    factory { GetStarredPagingUseCase(get()) }
    factory { GetRepositoryScreenUseCase(get()) }
    factory { SearchRepositoriesUseCase(get()) }

    viewModel { MainPageViewModel(get(), get()) }
    viewModel { StarredRepositoriesViewModel(get()) }
    viewModel { (id: Int) -> RepositoryScreenViewModel(get(), id) }
}