package com.yx.wanandroidkt.api

import com.yx.wanandroidkt.viewmodel.bean.*
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
    suspend fun listArticleTop():BaseResponse<List<ArticleBeanItem>>

    /**
     *  首页banner https://www.wanandroid.com/banner/json
     */
    @GET("banner/json")
    suspend fun getBanner():BaseResponse<List<BannerBean>>

    /**
     * 广场
     * https://wanandroid.com/user_article/list/页码/json
     */
    @GET("user_article/list/{pageNumber}/json")
    suspend fun listUserArticle(@Path("pageNumber") number: Int?):BaseResponse<BaseData<ArticleBeanItem>>

    /**
     *  获取公众号列表 https://wanandroid.com/wxarticle/chapters/json
     */
    @GET("wxarticle/chapters/json")
    fun getWxChapters():Call<BaseResponse<List<WxChapterBean>>>

    /**
     * 查看某个公众号历史数据 https://wanandroid.com/wxarticle/list/408/1/json
     */
    @GET("wxarticle/list/{id}/{pageNumber}/json")
    suspend fun listWxArticle(@Path("id") id:Int,@Path("pageNumber") number: Int?):BaseResponse<BaseData<ArticleBeanItem>>

}