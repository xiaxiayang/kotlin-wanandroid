package com.yx.wanandroidkt.ui.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.yx.wanandroidkt.R
import com.yx.wanandroidkt.base.BaseFragment
import com.yx.wanandroidkt.ui.adapter.BannerAdapter
import com.yx.wanandroidkt.ui.adapter.CommonAdapter
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

    private val commonAdapter by lazy {
        CommonAdapter(R.layout.item_rv_article,articleList)
    }

    private val bannerAdapter by lazy {
        BannerAdapter(this,R.layout.layout_banner_header,bannerList)
    }

    private var bannerList  = mutableListOf<List<BannerBean>>()
    private var articleList  = mutableListOf<ArticleBeanItem>()

    /**
     * 是否是刷新
     */
    private var isRefresh = true

    override fun getLayoutId(): Int {
        return  R.layout.fragment_common_article
    }

    override fun initView() {
        setToolbarLeftImageVisibility(false)
        setTitle(resources.getString(R.string.menu_home))

        rv_article.layoutManager = LinearLayoutManager(requireContext())
        rv_article.adapter =  ConcatAdapter(bannerAdapter,commonAdapter)

        swipeRefreshLayout.setRefreshHeader(ClassicsHeader(requireContext()))
        swipeRefreshLayout.setRefreshFooter(ClassicsFooter(requireContext()))
        swipeRefreshLayout.setOnRefreshListener {
            isRefresh = true
            model.refreshArticles()
        }
        swipeRefreshLayout.setOnLoadMoreListener {
            isRefresh = false
            model.loadMoreArticles()
        }

    }

    override fun registerListener() {

    }

    override fun requestData() {

        model.bannerResponse.observe(this, Observer {
            if (isSuccess(it)){
                bannerList.add(it.data)
                bannerAdapter.notifyDataSetChanged()
            }else{
                showToast(it.errorMsg)
            }
        })

        model.articleResponse.observe(this, Observer {
            if (isRefresh){
                articleList.clear()
                swipeRefreshLayout.finishRefresh()
            }else{
                swipeRefreshLayout.finishLoadMore()
            }
            articleList.addAll(it)
            commonAdapter.notifyDataSetChanged()
        })

        model.getBanners()

        model.refreshArticles()

    }


}