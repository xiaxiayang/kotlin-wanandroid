package com.yx.wanandroidkt.ui.fragment

import android.content.ContentValues
import com.yx.wanandroidkt.R
import com.yx.wanandroidkt.base.CommonArticleFragment
import com.yx.wanandroidkt.constans.ApiConst

/**
 *
 * 添加描述
 * Created by yx on 2020/9/1
 */
class SquareFragment: CommonArticleFragment() {

    companion object{
        fun getInstance(): SquareFragment = SquareFragment()
    }

    private val contentValues = ContentValues()

    override fun initView() {
        setToolbarLeftImageVisibility(false)
        setTitle(resources.getString(R.string.menu_square))

        contentValues.put(ApiConst.API_CODE,ApiConst.API_USER_ARTICLE_LIST)

        initRecycleView(contentValues)
    }

    override fun registerListener() {
    }

    override fun requestData() {

        fetchData(contentValues)
    }
}