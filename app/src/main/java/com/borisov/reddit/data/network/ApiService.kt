package com.borisov.reddit.data.network

import com.borisov.reddit.domain.ApiResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Borisov Andrey on 07.07.2022
 **/
interface ApiService {
    @GET("top.json?limit=10")
    fun getPosts(@Query("after") after: String): Single<ApiResponse>
}