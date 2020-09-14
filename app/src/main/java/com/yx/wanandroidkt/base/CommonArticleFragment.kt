package com.yx.wanandroidkt.base

import android.content.ContentValues
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.yx.wanandroidkt.R
import com.yx.wanandroidkt.ui.adapter.CommonAdapter
import com.yx.wanandroidkt.viewmodel.CommonFragmentVM
import com.yx.wanandroidkt.viewmodel.bean.ArticleBeanItem
import kotlinx.android.synthetic.main.fragment_common_article.*

/**
 *
 * 文章列表类 fragment 
 * Created by yx on 2020/9/4
 */
open class CommonArticleFragment: BaseFragment() {


    private val adapter by lazy {
        CommonAdapter(R.layout.item_rv_article,articleList)
    }

    /**
     * 数据源
     */
    private var articleList  = mutableListOf<ArticleBeanItem>()

    private var isRefresh = true

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



    fun initRecycleView(contentValues: ContentValues){
        rv_article.layoutManager = LinearLayoutManager(requireContext())
        rv_article.adapter = adapter

        swipeRefreshLayout.setRefreshHeader(ClassicsHeader(requireContext()))
        swipeRefreshLayout.setRefreshFooter(ClassicsFooter(requireContext()))

        swipeRefreshLayout.setOnRefreshListener {
            isRefresh = true
            model.getArticleList(true,contentValues)
        }

        swipeRefreshLayout.setOnLoadMoreListener {
            isRefresh = false
            model.getArticleList(false,contentValues)

        }


    }

    fun  fetchData(contentValues: ContentValues){

        model.articleResponse.observe(this, Observer {
            if (isRefresh){
                swipeRefreshLayout.finishRefresh()
                articleList.clear()
            }else{
                swipeRefreshLayout.finishLoadMore()
            }
            articleList.addAll(it.data.datas)
            adapter.notifyDataSetChanged()

        })

        model.getArticleList(isRefresh,contentValues)


    }


}