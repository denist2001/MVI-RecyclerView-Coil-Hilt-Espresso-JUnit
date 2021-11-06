package com.denis.mvi_recyclerview_coil_hilt_espresso_junit.data

import retrofit2.http.GET
import retrofit2.http.Path

interface RepositoryService {
    @GET("{number}")
    suspend fun loadItem(
        @Path("number") number: Int
    ): RepositoryData?
}