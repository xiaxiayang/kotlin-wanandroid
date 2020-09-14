package com.yx.wanandroidkt.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.yx.wanandroidkt.R
import com.yx.wanandroidkt.base.BaseFragment
import com.yx.wanandroidkt.viewmodel.AccountFragmentVM
import com.yx.wanandroidkt.viewmodel.bean.WxChapterBean
import kotlinx.android.synthetic.main.fragment_account.*

/**
 *
 * 添加描述
 * Created by yx on 2020/9/1
 */
class OfficialAccountsFragment: BaseFragment() {

    private val model: AccountFragmentVM by viewModels<AccountFragmentVM>()
    
    private var chapters: MutableList<WxChapterBean>? = mutableListOf()

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

    private fun  initViewPager(){
        view_pager.adapter = ViewpagerAdapter(this)

        TabLayoutMediator(tabLayout,view_pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = chapters!![position].name

            }).attach()
    }

    override fun requestData() {
        model.getChapters().observe(this, Observer {
            if ( !it.isNullOrEmpty()){
                chapters?.addAll(it)
                initViewPager()

            }

        })
    }


    inner class ViewpagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment){
        override fun getItemCount(): Int {
            return  chapters!!.size
        }

        override fun createFragment(position: Int): Fragment {
            Log.d("createFragment", "createFragment: $position")

            var bundle = Bundle()
            bundle.putInt("id",chapters!![position].id)
            return  AccountChildFragment.getInstance(bundle)
        }

    }
}