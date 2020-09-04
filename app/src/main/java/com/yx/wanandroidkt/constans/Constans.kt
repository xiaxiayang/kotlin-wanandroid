package com.yx.wanandroidkt.constans

import androidx.recyclerview.widget.DiffUtil
import com.yx.wanandroidkt.viewmodel.bean.ArticleBeanItem

/**
 *
 * 添加描述
 * Created by yx on 2020/9/3
 */
object Constans {
    //图片圆角
    const val ROUND_CORNER = 10

    //paging adapter 差分处理
    val POST_COMPARATOR = object : DiffUtil.ItemCallback<ArticleBeanItem>(){
        override fun areItemsTheSame(
            oldItem: ArticleBeanItem,
            newItem: ArticleBeanItem
        ): Boolean  = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ArticleBeanItem,
            newItem: ArticleBeanItem
        ): Boolean  = oldItem == newItem

    }
}