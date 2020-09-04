package com.yx.wanandroidkt.base

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.yx.wanandroidkt.R
import com.yx.wanandroidkt.ui.adapter.CommonArticleAdapter
import com.yx.wanandroidkt.viewmodel.CommonFragmentVM
import kotlinx.android.synthetic.main.fragment_common_article.*

/**
 *
 * 文章列表类 fragment 
 * Created by yx on 2020/9/4
 */
open class CommonArticleFragment: BaseFragment() {

    private val adapter by lazy {
        CommonArticleAdapter(R.layout.item_rv_article)
    }

    private val model: CommonFragmentVM by viewModels<CommonFragmentVM>()

    override fun getLayoutId(): Int {
        return  R.layout.fragment_common_article
    }

    override fun initView() {
    }


    override fun registerListener() {
    }

    override fun requestData() {

    }

    fun setToolbarVisibility(show: Boolean){
        toolbar.visibility = if (show) View.VISIBLE else View.GONE
    }



    fun initRecycleView(){
        rv_article.layoutManager = LinearLayoutManager(requireContext())
        rv_article.adapter = adapter.withLoadStateFooter(LoadMoreAdapter(object : LoadMoreAdapter.Listener{
            override fun retry(loadState: LoadState) {
                adapter.retry()
            }
        }))

        swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()
        }

        adapter.addLoadStateListener {
            when(it.refresh){
                is LoadState.Loading -> swipeRefreshLayout.isRefreshing = true
                else -> swipeRefreshLayout.isRefreshing = false
            }
        }

    }

    fun  fetchData(){

        model.getArticles().observe(this, Observer {
            lifecycleScope.launchWhenCreated{
                adapter.submitData(it)
            }
        })
    }
}