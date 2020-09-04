package com.yx.wanandroidkt.ui.fragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.yx.wanandroidkt.R
import com.yx.wanandroidkt.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_account.*

/**
 *
 * 添加描述
 * Created by yx on 2020/9/1
 */
class OfficialAccountsFragment: BaseFragment() {

    private  val NUM_PAGES = 5

    companion object{
        fun getInstance(): OfficialAccountsFragment = OfficialAccountsFragment()
    }

    override fun getLayoutId(): Int {
        return  R.layout.fragment_account
    }

    override fun initView() {
        setToolbarLeftImageVisibility(false)
        setTitle(resources.getString(R.string.menu_official_accounts))

        view_pager.adapter = ViewpagerAdapter(this)

        TabLayoutMediator(tabLayout,view_pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = "第${position+1}个"

            }).attach()



    }

    override fun registerListener() {
    }

    override fun requestData() {
    }


    inner class ViewpagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment){
        override fun getItemCount(): Int {
            return  NUM_PAGES
        }

        override fun createFragment(position: Int): Fragment {
            return  AccountChildFragment.getInstance()
        }

    }
}