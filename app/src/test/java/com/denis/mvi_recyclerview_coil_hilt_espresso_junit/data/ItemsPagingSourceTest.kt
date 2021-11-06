package com.denis.mvi_recyclerview_coil_hilt_espresso_junit.data

import androidx.paging.PagingSource
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.testUtils.CoroutinesTestRule
import com.google.gson.JsonSyntaxException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

@ExperimentalCoroutinesApi
class ItemsPagingSourceTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val mockedRepository = mockk<RepositoryService>()
    private val subject = ItemsPagingSource(mockedRepository, coroutinesTestRule.testDispatcherProvider)

    @Test
    fun `when key is null should try to start first page loading`() = runBlocking {
        val loadParams = mockk<PagingSource.LoadParams<Int>>()
        coEvery { mockedRepository.loadItem(1) } returns null
        every {loadParams.key} returns null
        subject.load(loadParams)
        coVerify { mockedRepository.loadItem(1) }
    }

    @Test
    fun `when key is negative should try to start first page loading`() = runBlocking {
        val loadParams = mockk<PagingSource.LoadParams<Int>>()
        coEvery { mockedRepository.loadItem(1) } returns null
        every {loadParams.key} returns -1000
        subject.load(loadParams)
        coVerify { mockedRepository.loadItem(1) }
    }

    @Test
    fun `when key is minimal int value should try to start first page loading`() = runBlocking {
        val loadParams = mockk<PagingSource.LoadParams<Int>>()
        coEvery { mockedRepository.loadItem(1) } returns null
        every {loadParams.key} returns Int.MIN_VALUE
        subject.load(loadParams)
        coVerify { mockedRepository.loadItem(1) }
    }

    @Test
    fun `when key is maximal int value should try to start this page loading`() = runBlocking {
        val loadParams = mockk<PagingSource.LoadParams<Int>>()
        coEvery { mockedRepository.loadItem(Int.MAX_VALUE) } returns null
        every {loadParams.key} returns Int.MAX_VALUE
        subject.load(loadParams)
        coVerify { mockedRepository.loadItem(Int.MAX_VALUE) }
    }

    @Test
    fun `when repository returns null should return error object`() = runBlocking {
        val loadParams = mockk<PagingSource.LoadParams<Int>>()
        coEvery { mockedRepository.loadItem(1) } returns null
        every {loadParams.key} returns 1
        val result = subject.load(loadParams)
        assertTrue(result is PagingSource.LoadResult.Error)
        assertEquals("No more elements", (result as PagingSource.LoadResult.Error).throwable.message)
    }

    @Test
    fun `when repository throws JsonSyntaxException should return error object`() = runBlocking {
        val loadParams = mockk<PagingSource.LoadParams<Int>>()
        coEvery { mockedRepository.loadItem(1) } throws JsonSyntaxException("Uuuups!")
        every {loadParams.key} returns 1
        val result = subject.load(loadParams)
        assertTrue(result is PagingSource.LoadResult.Error)
        assertEquals("Result can not be parsed", (result as PagingSource.LoadResult.Error).throwable.message)
    }

    @Test
    fun `when repository throws IOException should return error object`() = runBlocking {
        val loadParams = mockk<PagingSource.LoadParams<Int>>()
        coEvery { mockedRepository.loadItem(1) } throws IOException("Uuuups!")
        every {loadParams.key} returns 1
        val result = subject.load(loadParams)
        assertTrue(result is PagingSource.LoadResult.Error)
        assertEquals("IO error", (result as PagingSource.LoadResult.Error).throwable.message)
    }

    @Test
    fun `when repository throws HttpException should return error object`() = runBlocking {
        val loadParams = mockk<PagingSource.LoadParams<Int>>()
        val response = mockk<Response<Unit>>()
        every { response.code() } returns 404
        every { response.message() } returns "Uuuups!"
        coEvery { mockedRepository.loadItem(1) } throws HttpException(response)
        every {loadParams.key} returns 1
        val result = subject.load(loadParams)
        assertTrue(result is PagingSource.LoadResult.Error)
        assertEquals("Network error", (result as PagingSource.LoadResult.Error).throwable.message)
    }

    @Test
    fun `when repository throws IllegalStateException should return error object`() = runBlocking {
        val loadParams = mockk<PagingSource.LoadParams<Int>>()
        coEvery { mockedRepository.loadItem(1) } throws IllegalStateException("Uuuups!")
        every {loadParams.key} returns 1
        val result = subject.load(loadParams)
        assertTrue(result is PagingSource.LoadResult.Error)
        assertEquals("No more elements", (result as PagingSource.LoadResult.Error).throwable.message)
    }

    @Test
    fun `when key is null should return object with null as prevKey and 2 as nextKey`() = runBlocking {
        val loadParams = mockk<PagingSource.LoadParams<Int>>()
        val mockedRepositoryData = mockk<RepositoryData>()
        coEvery { mockedRepository.loadItem(1) } returns mockedRepositoryData
        every {loadParams.key} returns null
        val result = subject.load(loadParams)
        assertTrue(result is PagingSource.LoadResult.Page)
        assertEquals(listOf(mockedRepositoryData), (result as PagingSource.LoadResult.Page).data)
        assertNull((result).prevKey)
        assertEquals(2, result.nextKey)
    }

    @Test
    fun `when key is negative should return object with null as prevKey and 2 as nextKey`() = runBlocking {
        val loadParams = mockk<PagingSource.LoadParams<Int>>()
        val mockedRepositoryData = mockk<RepositoryData>()
        coEvery { mockedRepository.loadItem(1) } returns mockedRepositoryData
        every {loadParams.key} returns Int.MIN_VALUE
        val result = subject.load(loadParams)
        assertTrue(result is PagingSource.LoadResult.Page)
        assertEquals(listOf(mockedRepositoryData), (result as PagingSource.LoadResult.Page).data)
        assertNull((result).prevKey)
        assertEquals(2, result.nextKey)
    }

    @Test
    fun `when key is 0 should return object with null as prevKey and 2 as nextKey`() = runBlocking {
        val loadParams = mockk<PagingSource.LoadParams<Int>>()
        val mockedRepositoryData = mockk<RepositoryData>()
        coEvery { mockedRepository.loadItem(1) } returns mockedRepositoryData
        every {loadParams.key} returns 0
        val result = subject.load(loadParams)
        assertTrue(result is PagingSource.LoadResult.Page)
        assertEquals(listOf(mockedRepositoryData), (result as PagingSource.LoadResult.Page).data)
        assertNull((result).prevKey)
        assertEquals(2, result.nextKey)
    }

    @Test
    fun `when key is valid should return object with prevKey and nextKey`() = runBlocking {
        val loadParams = mockk<PagingSource.LoadParams<Int>>()
        val mockedRepositoryData = mockk<RepositoryData>()
        coEvery { mockedRepository.loadItem(100) } returns mockedRepositoryData
        every {loadParams.key} returns 100
        val result = subject.load(loadParams)
        assertTrue(result is PagingSource.LoadResult.Page)
        assertEquals(listOf(mockedRepositoryData), (result as PagingSource.LoadResult.Page).data)
        assertEquals(99, (result).prevKey)
        assertEquals(101, result.nextKey)
    }

    @Test
    fun `when key is maximal positive int should return object with prevKey and null as nextKey`() = runBlocking {
        val loadParams = mockk<PagingSource.LoadParams<Int>>()
        val mockedRepositoryData = mockk<RepositoryData>()
        coEvery { mockedRepository.loadItem(Int.MAX_VALUE) } returns mockedRepositoryData
        every {loadParams.key} returns Int.MAX_VALUE
        val result = subject.load(loadParams)
        assertTrue(result is PagingSource.LoadResult.Page)
        assertEquals(listOf(mockedRepositoryData), (result as PagingSource.LoadResult.Page).data)
        assertEquals(Int.MAX_VALUE - 1, (result).prevKey)
        assertNull(result.nextKey)
    }
}