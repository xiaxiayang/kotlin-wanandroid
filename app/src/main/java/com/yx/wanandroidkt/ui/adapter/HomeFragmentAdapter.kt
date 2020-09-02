package com.yx.wanandroidkt.ui.adapter

import android.content.Context
import android.text.TextUtils
import android.view.View
import com.bumptech.glide.Glide
import com.yx.wanandroidkt.R
import com.yx.wanandroidkt.base.BaseRvAdapter
import com.yx.wanandroidkt.base.BaseViewHolder
import com.yx.wanandroidkt.viewmodel.bean.ArticleBeanItem
import kotlinx.android.synthetic.main.item_rv_home.view.*

/**
 *
 * 添加描述
 * Created by yx on 2020/9/2
 */
class HomeFragmentAdapter(context: Context,layoutResId:Int,dataList:List<ArticleBeanItem>):
    BaseRvAdapter<ArticleBeanItem>(context,layoutResId,dataList){

    private var dataList = dataList
    private val context = context

    override fun onBind(holder: BaseViewHolder, position: Int) {

        val item = dataList[position]
        val view = holder.itemView
        val showPic = !TextUtils.isEmpty(item.envelopePic)

        view.tv_item_description.visibility = if (TextUtils.isEmpty(item.desc)) View.GONE else View.VISIBLE
        view.iv_item_pic.visibility = if (showPic) View.VISIBLE else View.GONE

        view.tv_item_name.text = if (TextUtils.isEmpty(item.author)) item.shareUser else item.author
        view.tv_item_title.text = item.title
        view.tv_item_time.text = item.niceDate
        view.tv_item_description.text = item.desc
        view.tv_item_label.text = "${item.superChapterName}·${item.chapterName}"

        view.iv_item_collect.setImageResource(if (item.collect) R.mipmap.ic_collect else R.mipmap.ic_uncollect)
        view.tv_item_title.maxLines = if (showPic) 1 else 3

        if (showPic){
            Glide.with(context)
                .load(item.envelopePic)
                .centerCrop()
                .placeholder(R.mipmap.ic_loading)
                .into(view.iv_item_pic);
        }
        

    }
}