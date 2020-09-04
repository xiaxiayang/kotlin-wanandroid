package com.yx.wanandroidkt.ui.adapter.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.yx.wanandroidkt.http.HttpClient
import com.yx.wanandroidkt.viewmodel.bean.ArticleBeanItem

/**
 *
 * 添加描述
 * Created by yx on 2020/9/3
 */
class HomeArticleRepository {
     fun getArticleList() = Pager(PagingConfig(pageSize = 20)){
         HomeArticleSource()
    }.flow
}

/**
 *
 * 数据仓库层  pagingSource
 * Created by yx on 2020/9/3
 */
class HomeArticleSource: PagingSource<Int, ArticleBeanItem>(){

    private val service = HttpClient.reqApi

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleBeanItem> {
        return try {
            val page = params.key?:0
            //获取数据

            var result = service.listArticle(page)
            LoadResult.Page(
                data = result?.data?.datas!!,
                prevKey = null,
                nextKey = if (page == result?.data?.pageCount) null else page+1
            )


        }catch (e:Exception){
            LoadResult.Error(e)
        }

    }
}