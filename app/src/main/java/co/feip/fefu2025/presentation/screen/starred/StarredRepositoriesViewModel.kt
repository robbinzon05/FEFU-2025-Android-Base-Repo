package co.feip.fefu2025.presentation.screen.starred

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import co.feip.fefu2025.domain.use_cases.GetStarredPagingUseCase

class StarredRepositoriesViewModel(
    getStars: GetStarredPagingUseCase
) : ViewModel() {
    val items = getStars().cachedIn(viewModelScope)
}
