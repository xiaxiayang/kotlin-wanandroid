package com.yx.wanandroidkt.ui.fragment

import com.yx.wanandroidkt.R
import com.yx.wanandroidkt.base.BaseFragment

/**
 *
 * 添加描述
 * Created by yx on 2020/9/1
 */
class OfficialAccountsFragment: BaseFragment() {

    companion object{
        fun getInstance(): OfficialAccountsFragment = OfficialAccountsFragment()
    }

    override fun getLayoutId(): Int {
        return  R.layout.fragment_account
    }

    override fun initView() {
        setToolbarLeftImageVisibility(false)
        setTitle(resources.getString(R.string.menu_official_accounts))
    }

    override fun registerListener() {
    }

    override fun requestData() {
    }
}