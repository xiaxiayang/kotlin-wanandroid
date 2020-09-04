package com.yx.wanandroidkt.ui.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.yx.wanandroidkt.R
import com.yx.wanandroidkt.base.BaseFragment
import com.yx.wanandroidkt.base.LoadMoreAdapter
import com.yx.wanandroidkt.ui.adapter.CommonArticleAdapter
import com.yx.wanandroidkt.ui.adapter.HeaderAdapter
import com.yx.wanandroidkt.ui.adapter.TopAdapter
import com.yx.wanandroidkt.viewmodel.HomeFragmentVM
import com.yx.wanandroidkt.viewmodel.bean.ArticleBeanItem
import com.yx.wanandroidkt.viewmodel.bean.BannerBean
import kotlinx.android.synthetic.main.fragment_common_article.*

/**
 *
 * 添加描述
 * Created by yx on 2020/9/1
 */
class HomeFragment: BaseFragment() {
    companion object{
        fun getInstance(): HomeFragment = HomeFragment()
    }

    private val model: HomeFragmentVM by viewModels<HomeFragmentVM>()


    /**
     * 文章列表的总的adapter，paging分页处理
     */
    private val adapter by lazy {
        CommonArticleAdapter(R.layout.item_rv_article)
    }

    /**
     * 头部 banner 轮播图
     */
    private val bannerAdapter by lazy {
        HeaderAdapter(this, R.layout.layout_banner_header, bannerList)
    }

    /**
     *置顶文章 adapter
     */
    private val topAdapter by lazy {
        TopAdapter( R.layout.item_rv_article, topList)
    }

    /**
     * 文章列表adapter
     */
    private val articleAdapter by lazy {
        adapter.withLoadStateFooter(LoadMoreAdapter(object : LoadMoreAdapter.Listener{
            override fun retry(loadState: LoadState) {
                adapter.retry()
            }
        }))
    }

    private var bannerList  = mutableListOf<List<BannerBean>>()
    private var topList  = mutableListOf<ArticleBeanItem>()

    override fun getLayoutId(): Int {
        return  R.layout.fragment_common_article
    }

    override fun initView() {
        setToolbarLeftImageVisibility(false)
        setTitle(resources.getString(R.string.menu_home))

        rv_article.layoutManager = LinearLayoutManager(requireContext())
        rv_article.adapter =  ConcatAdapter(bannerAdapter,topAdapter,articleAdapter)

        swipeRefreshLayout.setOnRefreshListener {
            requestHeader()
            adapter.refresh()

        }

        adapter.addLoadStateListener {
            when(it.refresh){
                is LoadState.Loading -> swipeRefreshLayout.isRefreshing = true
                else -> swipeRefreshLayout.isRefreshing = false
            }
        }

    }

    override fun registerListener() {

    }

    private fun  requestHeader(){
        model.getBannerList().observe(this, Observer {
            if ( !it.isNullOrEmpty()){
                bannerList?.clear()
                bannerList?.add(it)
                bannerAdapter?.notifyDataSetChanged()
            }

        })
        model.geTopArticle().observe(this, Observer {
            if ( !it.isNullOrEmpty()){
                topList?.clear()
                topList?.addAll(it)
                topAdapter?.notifyDataSetChanged()
            }

        })
    }

    override fun requestData() {

        requestHeader()

        model.getArticleList().observe(this, Observer {
            lifecycleScope.launchWhenCreated {
                adapter.submitData(it)
            }
        })

    }


}