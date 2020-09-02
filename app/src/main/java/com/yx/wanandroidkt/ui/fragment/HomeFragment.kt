package com.yx.wanandroidkt.ui.fragment

import android.icu.lang.UCharacter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yx.wanandroidkt.R
import com.yx.wanandroidkt.base.BaseFragment
import com.yx.wanandroidkt.ui.adapter.HomeFragmentAdapter
import com.yx.wanandroidkt.viewmodel.HomeFragmentVM
import com.yx.wanandroidkt.viewmodel.bean.ArticleBeanItem
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *
 * 添加描述
 * Created by yx on 2020/9/1
 */
class HomeFragment: BaseFragment() {
    companion object{
        fun getInstance(): HomeFragment = HomeFragment()
    }

    private var adapter: HomeFragmentAdapter? = null
    private var dataList = mutableListOf<ArticleBeanItem>()

    override fun getLayoutId(): Int {
        return  R.layout.fragment_home
    }

    override fun initView() {
        setToolbarLeftImageVisibility(false)
        setTitle(resources.getString(R.string.menu_home))

        adapter = HomeFragmentAdapter(requireContext(),R.layout.item_rv_home,dataList!!)
        rv_home.layoutManager = LinearLayoutManager(requireContext())
        rv_home.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
        rv_home.adapter = adapter
    }

    override fun registerListener() {

    }

    override fun requestData() {
        val model: HomeFragmentVM by viewModels<HomeFragmentVM>()
        model.getArticleList().observe(this, Observer {
            dataList.addAll(it.datas)
            adapter?.notifyDataSetChanged()
        })

    }

}