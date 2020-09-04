package com.yx.wanandroidkt.ui.fragment

import com.yx.wanandroidkt.base.CommonArticleFragment

/**
 *
 * 添加描述
 * Created by yx on 2020/9/4
 */
class AccountChildFragment: CommonArticleFragment() {

    companion object{
        fun getInstance() = AccountChildFragment()
    }
    override fun initView() {
        super.initView()
        setToolbarVisibility(false)
        initRecycleView()
    }

    override fun requestData() {
        super.requestData()
        fetchData()
    }

}