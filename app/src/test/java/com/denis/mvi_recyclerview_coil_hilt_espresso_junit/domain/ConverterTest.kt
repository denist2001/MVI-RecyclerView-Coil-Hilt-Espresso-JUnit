package com.denis.mvi_recyclerview_coil_hilt_espresso_junit.domain

import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.data.RepositoryData
import org.junit.Assert.*
import org.junit.Test

class ConverterTest {

    private val subject = Converter()

    @Test
    fun `when albumId is negative should create presentation object`() {
        val sourceValue = RepositoryData(
            albumId = -100,
            id = 1,
            title = "",
            url = "",
            thumbnailUrl = "",
        )
        val result = subject.convertFromRepositoryToPresentation(sourceValue)
        assertEquals(-100, result.albumId)
    }

    @Test
    fun `when albumId is minimal int value should create presentation object`() {
        val sourceValue = RepositoryData(
            albumId = Int.MIN_VALUE,
            id = 1,
            title = "",
            url = "",
            thumbnailUrl = "",
        )
        val result = subject.convertFromRepositoryToPresentation(sourceValue)
        assertEquals(Int.MIN_VALUE, result.albumId)
    }

    @Test
    fun `when albumId is positive int value should create presentation object`() {
        val sourceValue = RepositoryData(
            albumId = 1000,
            id = 1,
            title = "",
            url = "",
            thumbnailUrl = "",
        )
        val result = subject.convertFromRepositoryToPresentation(sourceValue)
        assertEquals(1000, result.albumId)
    }

    @Test
    fun `when albumId is maximal int value should create presentation object`() {
        val sourceValue = RepositoryData(
            albumId = Int.MAX_VALUE,
            id = 1,
            title = "",
            url = "",
            thumbnailUrl = "",
        )
        val result = subject.convertFromRepositoryToPresentation(sourceValue)
        assertEquals(Int.MAX_VALUE, result.albumId)
    }

    @Test
    fun `when id is negative should create presentation object`() {
        val sourceValue = RepositoryData(
            albumId = 1,
            id = -100,
            title = "",
            url = "",
            thumbnailUrl = "",
        )
        val result = subject.convertFromRepositoryToPresentation(sourceValue)
        assertEquals(-100, result.id)
    }

    @Test
    fun `when id is minimal int value should create presentation object`() {
        val sourceValue = RepositoryData(
            albumId = 1,
            id = Int.MIN_VALUE,
            title = "",
            url = "",
            thumbnailUrl = "",
        )
        val result = subject.convertFromRepositoryToPresentation(sourceValue)
        assertEquals(Int.MIN_VALUE, result.id)
    }

    @Test
    fun `when id is positive int value should create presentation object`() {
        val sourceValue = RepositoryData(
            albumId = 1,
            id = 1000,
            title = "",
            url = "",
            thumbnailUrl = "",
        )
        val result = subject.convertFromRepositoryToPresentation(sourceValue)
        assertEquals(1000, result.id)
    }

    @Test
    fun `when id is maximal int value should create presentation object`() {
        val sourceValue = RepositoryData(
            albumId = 1,
            id = Int.MAX_VALUE,
            title = "",
            url = "",
            thumbnailUrl = "",
        )
        val result = subject.convertFromRepositoryToPresentation(sourceValue)
        assertEquals(Int.MAX_VALUE, result.id)
    }

    @Test
    fun `when title contains special symbols should create presentation object`() {
        val sourceValue = RepositoryData(
            albumId = 1,
            id = 1,
            title = "±!@#$%ˆ&*()_+}{|:?><˜\"",
            url = "",
            thumbnailUrl = "",
        )
        val result = subject.convertFromRepositoryToPresentation(sourceValue)
        assertEquals("±!@#$%ˆ&*()_+}{|:?><˜\"", result.title)
    }

    @Test
    fun `when url contains special symbols should create presentation object`() {
        val sourceValue = RepositoryData(
            albumId = 1,
            id = 1,
            title = "",
            url = "±!@#$%ˆ&*()_+}{|:?><˜\"",
            thumbnailUrl = "",
        )
        val result = subject.convertFromRepositoryToPresentation(sourceValue)
        assertEquals("±!@#$%ˆ&*()_+}{|:?><˜\"", result.url)
    }

    @Test
    fun `when thumbnailUrl contains special symbols should create presentation object`() {
        val sourceValue = RepositoryData(
            albumId = 1,
            id = 1,
            title = "",
            url = "",
            thumbnailUrl = "±!@#$%ˆ&*()_+}{|:?><˜\"",
        )
        val result = subject.convertFromRepositoryToPresentation(sourceValue)
        assertEquals("±!@#$%ˆ&*()_+}{|:?><˜\"", result.thumbnailUrl)
    }
}