package com.denis.mvi_recyclerview_coil_hilt_espresso_junit.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.DispatcherProvider
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.domain.Repository
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.presentation.PresentationData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val itemsPagingSource: ItemsPagingSource,
): Repository {
    override suspend fun loadItem(number: Int?): Flow<PagingData<PresentationData>> {
        return withContext(dispatcherProvider.io()) {
            return@withContext Pager(
                config = PagingConfig(
                    pageSize = 10,
                    enablePlaceholders = true,
                    prefetchDistance = 10,
                    initialLoadSize = 10
                ),
                pagingSourceFactory = { itemsPagingSource }
            ).flow
        }
    }
}