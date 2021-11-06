package com.denis.mvi_recyclerview_coil_hilt_espresso_junit.domain

import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.presentation.PresentationData
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.data.RepositoryData
import javax.inject.Inject

class Converter @Inject constructor() {
    fun convertFromRepositoryToPresentation(repositoryData: RepositoryData): PresentationData {
        return PresentationData(
            albumId = repositoryData.albumId,
            id = repositoryData.id,
            title = repositoryData.title,
            url = repositoryData.url,
            thumbnailUrl = repositoryData.thumbnailUrl,
        )
    }
}