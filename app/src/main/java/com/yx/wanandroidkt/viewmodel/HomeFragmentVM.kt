package com.yx.wanandroidkt.viewmodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yx.wanandroidkt.MyApplication
import com.yx.wanandroidkt.Test
import com.yx.wanandroidkt.api.ApiService
import com.yx.wanandroidkt.http.HttpClient
import com.yx.wanandroidkt.viewmodel.bean.ArticleBeanItem
import com.yx.wanandroidkt.viewmodel.bean.BaseData

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

    private val articles: MutableLiveData<BaseData<ArticleBeanItem>> by lazy {
       MutableLiveData<BaseData<ArticleBeanItem>>().also {
           getList()
       }
    }

   fun getArticleList(): LiveData<BaseData<ArticleBeanItem>>{
       return articles
   }

    private fun getList(){
        HttpClient.cmEnqueue(service.listArticle(0),object :HttpClient.IResultListener<BaseData<ArticleBeanItem>>{
            override fun onSuccess(data: BaseData<ArticleBeanItem>?) {
                articles.value = data
            }

            override fun onError(message: String?) {
                Toast.makeText(MyApplication.instance(),message,Toast.LENGTH_SHORT).show()
            }

        })
    }
}