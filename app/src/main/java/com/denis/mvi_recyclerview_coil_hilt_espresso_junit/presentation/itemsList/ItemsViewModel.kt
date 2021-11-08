package com.denis.mvi_recyclerview_coil_hilt_espresso_junit.presentation.itemsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.domain.Repository
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.presentation.PresentationData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _presentationData = MutableLiveData<PagingData<PresentationData>>()
    val presentationData: LiveData<PagingData<PresentationData>>
        get() = _presentationData

    suspend fun startLoading() {
        return repository.loadItem(1).cachedIn(viewModelScope)
            .collectLatest {
                _presentationData.postValue(it)
            }
    }
}