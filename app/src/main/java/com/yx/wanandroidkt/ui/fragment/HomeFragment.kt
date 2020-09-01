package com.yx.wanandroidkt.ui.fragment

import com.yx.wanandroidkt.R
import com.yx.wanandroidkt.base.BaseFragment

/**
 *
 * 添加描述
 * Created by yx on 2020/9/1
 */
class HomeFragment: BaseFragment() {
    companion object{
        fun getInstance(): HomeFragment = HomeFragment()
    }

    override fun getLayoutId(): Int {
        return  R.layout.fragment_home
    }

    override fun initView() {
        setToolbarLeftImageVisibility(false)
        setTitle(resources.getString(R.string.menu_home))
    }

    override fun registerListener() {
    }

    override fun requestData() {
    }

}