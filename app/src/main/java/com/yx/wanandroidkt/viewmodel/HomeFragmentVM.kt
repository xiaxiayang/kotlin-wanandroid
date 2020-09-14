package com.yx.wanandroidkt.viewmodel

import androidx.lifecycle.MutableLiveData
import com.yx.wanandroidkt.api.ApiService
import com.yx.wanandroidkt.base.BaseViewModel
import com.yx.wanandroidkt.http.HttpClient
import com.yx.wanandroidkt.viewmodel.bean.ArticleBeanItem
import com.yx.wanandroidkt.viewmodel.bean.BannerBean
import com.yx.wanandroidkt.viewmodel.bean.BaseData
import com.yx.wanandroidkt.viewmodel.bean.BaseResponse
import kotlinx.coroutines.async

/**
 *
 * 添加描述
 * Created by yx on 2020/8/31
 */
class HomeFragmentVM: BaseViewModel() {

    companion object {
        private const val TAG = "HomeFragmentVM"
    }

    private var service :ApiService = HttpClient.reqApi

    /**
     * 请求第几页
     */
    private var currentPage = 0


    val bannerResponse: MutableLiveData<BaseResponse<List<BannerBean>>> = MutableLiveData()

    val articleResponse:MutableLiveData<MutableList<ArticleBeanItem>> = MutableLiveData()

    /**
     * 获取顶部轮播图
     */
    fun getBanners() {
        var response: BaseResponse<List<BannerBean>> ? = null
        launchOnUI(
            block = {
                response = service.getBanner()
            },
            after = {
                bannerResponse.value = response
            }
        )
    }

    /**
     * 刷新
     */
    fun refreshArticles() {
        currentPage = 0
        var articleList: BaseResponse<BaseData<ArticleBeanItem>>? = null
        var topList: BaseResponse<List<ArticleBeanItem>>? = null
        launchOnUI(block = {
            val topDeferred = async { service.listArticleTop() }
            val articleDeff = async { service.listArticle(currentPage) }
            topList = topDeferred.await().apply {
                this.data.forEach {
                    it.isTop = true
                }
            }
            articleList = articleDeff.await()
        },
            after = {
                articleResponse.value = mutableListOf<ArticleBeanItem>().apply {
                    addAll(topList!!.data)
                    addAll(articleList!!.data.datas)
                }
        })

    }

    fun loadMoreArticles(){
        currentPage++
        var articleList: BaseResponse<BaseData<ArticleBeanItem>>? = null
        launchOnUI(
            block = {
                articleList = service.listArticle(currentPage)
            },
            after = {
                articleResponse.value = mutableListOf<ArticleBeanItem>().apply {
                    addAll(articleList!!.data.datas)
                }
            }
        )


    }

}