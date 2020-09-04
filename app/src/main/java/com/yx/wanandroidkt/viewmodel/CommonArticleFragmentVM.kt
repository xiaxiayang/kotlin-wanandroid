package com.yx.wanandroidkt.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yx.wanandroidkt.ui.adapter.datasource.CommonArticleRepository

/**
 *
 * 添加描述
 * Created by yx on 2020/9/4
 */
class CommonFragmentVM: ViewModel() {

    private val respository by lazy {
        CommonArticleRepository()
    }

    fun getArticles() = respository.getArticleList().asLiveData()
}