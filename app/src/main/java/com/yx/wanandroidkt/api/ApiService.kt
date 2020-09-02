package com.yx.wanandroidkt.api

import com.yx.wanandroidkt.Test
import com.yx.wanandroidkt.viewmodel.bean.ArticleBeanItem
import com.yx.wanandroidkt.viewmodel.bean.BaseResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 *
 * 添加描述
 * Created by yx on 2020/8/31
 */
interface ApiService {
    @GET("article/list/{pageNumber}/json")
    fun listArticle(@Path("pageNumber") number: Int?):Call<BaseResult<ArticleBeanItem>>
}