package com.yx.wanandroidkt.ui.fragment

import com.yx.wanandroidkt.R
import com.yx.wanandroidkt.base.CommonArticleFragment

/**
 *
 * 添加描述
 * Created by yx on 2020/9/1
 */
class SquareFragment: CommonArticleFragment() {

    companion object{
        fun getInstance(): SquareFragment = SquareFragment()
    }

    override fun initView() {
        setToolbarLeftImageVisibility(false)
        setTitle(resources.getString(R.string.menu_square))
        initRecycleView()
    }

    override fun registerListener() {
    }

    override fun requestData() {
        fetchData()
    }
}