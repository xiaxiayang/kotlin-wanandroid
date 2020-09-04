package com.yx.wanandroidkt.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yx.wanandroidkt.api.ApiService
import com.yx.wanandroidkt.http.HttpClient
import com.yx.wanandroidkt.ui.adapter.datasource.HomeArticleRepository
import com.yx.wanandroidkt.viewmodel.bean.ArticleBeanItem
import com.yx.wanandroidkt.viewmodel.bean.BannerBean
import com.yx.wanandroidkt.viewmodel.bean.BaseResponse

/**
 *
 * 添加描述
 * Created by yx on 2020/8/31
 */
class HomeFragmentVM: ViewModel() {

    companion object {
        private const val TAG = "HomeFragmentVM"
    }

    private var service :ApiService = HttpClient.reqApi

    private val banners: MutableLiveData<List<BannerBean>> by lazy {
        MutableLiveData<List<BannerBean>>().also {
            requestBanner()
        }
    }

    private val topArticles: MutableLiveData<List<ArticleBeanItem>> by lazy {
        MutableLiveData<List<ArticleBeanItem>>().also {
            requestTopArticle()
        }
    }

    private val respository: HomeArticleRepository  by lazy {
        HomeArticleRepository()
    }


    fun getArticleList() = respository.getArticleList().asLiveData()

    fun getBannerList(): LiveData<List<BannerBean>>{
        return banners
    }
    fun geTopArticle(): LiveData<List<ArticleBeanItem>>{
        return topArticles
    }
    private fun requestBanner(){
        HttpClient.cmEnqueue(service.getBanner(),
            object : HttpClient.IResultListener<BaseResponse<List<BannerBean>>>{
                override fun onSuccess(response: BaseResponse<List<BannerBean>>?) {
                    if (!response?.data.isNullOrEmpty()){
                        banners.value = response?.data
                    }
                }

                override fun onError(message: String?) {
                    Log.d(TAG, "onError: getBanner$message ")
                }

            })

    }
    private fun requestTopArticle(){
        HttpClient.cmEnqueue(service.listArticleTop(),
            object : HttpClient.IResultListener<BaseResponse<List<ArticleBeanItem>>>{
                override fun onSuccess(response: BaseResponse<List<ArticleBeanItem>>?) {
                    if (!response?.data.isNullOrEmpty()){
                        for (item in response!!.data){
                            item.isTop = true
                        }
                        topArticles.value = response?.data
                    }
                }

                override fun onError(message: String?) {
                    Log.d(TAG, "onError: getBanner$message ")
                }

            })

    }
}