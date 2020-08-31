package com.yx.wanandroidkt.api

import com.yx.wanandroidkt.Test
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 *
 * 添加描述
 * Created by yx on 2020/8/31
 */
interface ApiService {
    @GET("users/{user}")
    fun listRepos(@Path("user") user: String?):Call<Test>
}