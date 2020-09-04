package com.yx.wanandroidkt.api

import com.yx.wanandroidkt.viewmodel.bean.ArticleBeanItem
import com.yx.wanandroidkt.viewmodel.bean.BannerBean
import com.yx.wanandroidkt.viewmodel.bean.BaseData
import com.yx.wanandroidkt.viewmodel.bean.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 *
 * 添加描述
 * Created by yx on 2020/8/31
 */
interface ApiService {
    /**
     * 首页文章列表https://www.wanandroid.com/article/list/0/json
     * 方法：GET
     * 参数：页码，拼接在连接中，从0开始。
     */
    @GET("article/list/{pageNumber}/json")
    suspend fun listArticle(@Path("pageNumber") number: Int?):BaseResponse<BaseData<ArticleBeanItem>>

    /**
     *  置顶文章 https://www.wanandroid.com/article/top/json
     */
    @GET("article/top/json")
    fun listArticleTop():Call<BaseResponse<List<ArticleBeanItem>>>

    /**
     *  首页banner https://www.wanandroid.com/banner/json
     */
    @GET("banner/json")
    fun getBanner():Call<BaseResponse<List<BannerBean>>>
}