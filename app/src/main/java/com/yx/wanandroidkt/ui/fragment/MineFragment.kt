package com.yx.wanandroidkt.ui.fragment

import com.yx.wanandroidkt.R
import com.yx.wanandroidkt.base.BaseFragment

/**
 *
 * 添加描述
 * Created by yx on 2020/9/1
 */
class MineFragment: BaseFragment() {

    companion object{
        fun getInstance(): MineFragment = MineFragment()
    }

    override fun getLayoutId(): Int {
        return  R.layout.fragment_mine
    }

    override fun initView() {
        setToolbarLeftImageVisibility(false)
        setTitle(resources.getString(R.string.menu_mine))
    }

    override fun registerListener() {
    }

    override fun requestData() {
    }
}