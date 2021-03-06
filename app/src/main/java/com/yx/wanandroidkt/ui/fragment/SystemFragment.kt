package com.yx.wanandroidkt.ui.fragment

import com.yx.wanandroidkt.R
import com.yx.wanandroidkt.base.BaseFragment

/**
 *
 * 添加描述
 * Created by yx on 2020/9/1
 */
class SystemFragment: BaseFragment() {

    companion object{
        fun getInstance(): SystemFragment = SystemFragment()
    }

    override fun getLayoutId(): Int {
        return  R.layout.fragment_system
    }

    override fun initView() {
        setToolbarLeftImageVisibility(false)
        setTitle(resources.getString(R.string.menu_system))
    }

    override fun registerListener() {
    }

    override fun requestData() {
    }
}