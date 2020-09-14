package com.yx.wanandroidkt.viewmodel

import android.content.ContentValues
import androidx.lifecycle.MutableLiveData
import com.yx.wanandroidkt.base.BaseViewModel
import com.yx.wanandroidkt.constans.ApiConst
import com.yx.wanandroidkt.http.HttpClient
import com.yx.wanandroidkt.viewmodel.bean.ArticleBeanItem
import com.yx.wanandroidkt.viewmodel.bean.BaseData
import com.yx.wanandroidkt.viewmodel.bean.BaseResponse

/**
 *
 * 添加描述
 * Created by yx on 2020/9/4
 */
class CommonFragmentVM: BaseViewModel() {

    private var currentPage = 0

    private val service = HttpClient.reqApi

    val articleResponse = MutableLiveData<BaseResponse<BaseData<ArticleBeanItem>>>()


    fun getArticleList(isRefresh: Boolean,contentValues: ContentValues){

        if (contentValues == null  ||  (contentValues.getAsString(ApiConst.API_CODE) == null)){
            throw Exception("please check if you support the api code  ")
        }

        if (isRefresh) currentPage =0 else currentPage++

        var response:BaseResponse<BaseData<ArticleBeanItem>>? = null

        launchOnUI(
            block = {
                response = when(contentValues[ApiConst.API_CODE]){
                    ApiConst.API_USER_ARTICLE_LIST -> {//广场
                        service.listUserArticle(currentPage)
                    }
                    ApiConst.API_WX_ARTICLE_LIST -> { //公众号
                        val id = contentValues.getAsInteger("id")
                        service.listWxArticle(id,currentPage)
                    }
                    else -> {
                        service.listArticle(currentPage)
                    }
                }
            },
            after = {
                articleResponse.value = response
            }
        )


    }

}