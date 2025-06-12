package co.feip.fefu2025.presentation.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import co.feip.fefu2025.domain.use_cases.GetProjectsPagingUseCase
import co.feip.fefu2025.domain.use_cases.GetStarredPagingUseCase

class MainPageViewModel(
    getAll: GetProjectsPagingUseCase,
    getStars: GetStarredPagingUseCase
) : ViewModel() {

    val allProjects = getAll().cachedIn(viewModelScope)
    val starred = getStars().cachedIn(viewModelScope)
}
