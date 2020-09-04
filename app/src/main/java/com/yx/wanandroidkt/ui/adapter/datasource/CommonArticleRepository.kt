package com.yx.wanandroidkt.ui.adapter.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.yx.wanandroidkt.http.HttpClient
import com.yx.wanandroidkt.viewmodel.bean.ArticleBeanItem

/**
 *
 * 广场 -- paging 数据处理
 * Created by yx on 2020/9/4
 */
class CommonArticleRepository {

    fun getArticleList() = Pager(PagingConfig(pageSize = 20)){
        CommonDataSource()
    }.flow

}

class CommonDataSource: PagingSource<Int,ArticleBeanItem>(){

    private val  service = HttpClient.reqApi

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleBeanItem> {

        return try {
            var page = params.key?:0
            val result = service.listUserArticle(page)
            LoadResult.Page(
                data = result.data.datas,
                prevKey = null,
                nextKey = if (page == result?.data?.pageCount) null else page+1
            )

        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}