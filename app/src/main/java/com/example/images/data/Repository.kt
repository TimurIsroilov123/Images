package com.example.images.data

import retrofit2.http.GET

object Repository {
    suspend fun loadImages(): List<String> {
        return RetrofitModule().imagesApi.getImages()
    }
}

interface Api {
    @GET("task-m-001/list.php")
    suspend fun getImages(): List<String>
}

const val BASE_URL = "http://dev-tasks.alef.im/"