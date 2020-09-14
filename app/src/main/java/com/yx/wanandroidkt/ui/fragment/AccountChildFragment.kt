package com.yx.wanandroidkt.ui.fragment

import android.content.ContentValues
import android.os.Bundle
import com.yx.wanandroidkt.base.CommonArticleFragment
import com.yx.wanandroidkt.constans.ApiConst

/**
 *
 * 添加描述
 * Created by yx on 2020/9/4
 */
class AccountChildFragment: CommonArticleFragment() {


    private val contentValues = ContentValues()

    companion object{
        fun getInstance(args:Bundle): AccountChildFragment  {
            val fragment = AccountChildFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun initView() {
        super.initView()
        setToolbarVisibility(false)

        val id = arguments?.getInt("id")
        contentValues.put(ApiConst.API_CODE,ApiConst.API_WX_ARTICLE_LIST)
        contentValues.put("id",id)

        initRecycleView(contentValues)
    }

    override fun requestData() {
        super.requestData()
        fetchData(contentValues)
    }

}