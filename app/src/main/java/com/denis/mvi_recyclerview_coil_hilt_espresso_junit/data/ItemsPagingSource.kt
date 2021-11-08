package com.denis.mvi_recyclerview_coil_hilt_espresso_junit.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.DispatcherProvider
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.domain.Converter
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.presentation.PresentationData
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ItemsPagingSource @Inject constructor(
    private val networkService: RepositoryService,
    private val dispatcherProvider: DispatcherProvider,
    private val converter: Converter,
) : PagingSource<Int, PresentationData>() {

    override fun getRefreshKey(state: PagingState<Int, PresentationData>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PresentationData> {
        return withContext(dispatcherProvider.io()) {
            val pageNumber = params.key ?: 1
            val currKey = if (pageNumber < 1) 1 else pageNumber
            try {
                val repositoryData =
                    networkService.loadItem(currKey) ?: throw IllegalStateException()
                val prevKey = if (currKey > 1) currKey - 1 else null
                val nextKey = if (currKey < Int.MAX_VALUE) currKey + 1 else null
                transformResult(repositoryData, prevKey, nextKey)
            } catch (exception: JsonSyntaxException) {
                return@withContext LoadResult.Error(Throwable("Result can not be parsed"))
            } catch (exception: IOException) {
                return@withContext LoadResult.Error(Throwable("IO error"))
            } catch (exception: HttpException) {
                return@withContext LoadResult.Error(Throwable("Network error"))
            } catch (exception: IllegalStateException) {
                return@withContext LoadResult.Error(Throwable("No more elements"))
            }
        }
    }

    private fun transformResult(
        repositoryData: RepositoryData,
        prevKey: Int?,
        nextKey: Int?
    ): LoadResult<Int, PresentationData> {
        val presentationDate = converter.convertFromRepositoryToPresentation(repositoryData)
        return LoadResult.Page(
            data = listOf(presentationDate),
            prevKey = prevKey,
            nextKey = nextKey
        )
    }
}