package com.denis.mvi_recyclerview_coil_hilt_espresso_junit.domain

import androidx.paging.PagingData
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.presentation.PresentationData
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun loadItem(number: Int?): Flow<PagingData<PresentationData>>
}