package com.yx.wanandroidkt.ui.adapter

import android.text.TextUtils
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import com.yx.wanandroidkt.R
import com.yx.wanandroidkt.base.BaseRvAdapter
import com.yx.wanandroidkt.base.BaseViewHolder
import com.yx.wanandroidkt.loader.ImageLoader
import com.yx.wanandroidkt.viewmodel.bean.ArticleBeanItem
import com.yx.wanandroidkt.viewmodel.bean.BannerBean
import kotlinx.android.synthetic.main.item_rv_article.view.*
import kotlinx.android.synthetic.main.layout_banner_header.view.*


/***
 * 通用adapter
 */
class CommonAdapter(layout: Int,private val items: List<ArticleBeanItem>
): BaseRvAdapter<ArticleBeanItem>(layout,items){
    override fun onBind(holder: BaseViewHolder, position: Int) {
        val view = holder.itemView
        val item  = items[position]

        val showPic = !TextUtils.isEmpty(item.envelopePic)

        view.tv_item_description.visibility = if (TextUtils.isEmpty(item.desc)) View.GONE else View.VISIBLE
        view.iv_item_pic.visibility = if (showPic) View.VISIBLE else View.GONE
        view.tv_item_new.visibility = if (item.fresh) View.VISIBLE else View.GONE
        view.tv_item_top.visibility = if (item.isTop) View.VISIBLE else View.GONE

        view.tv_item_name.text = if (TextUtils.isEmpty(item.author)) item.shareUser else item.author
        view.tv_item_title.text = item.title
        view.tv_item_time.text = item.niceDate
        view.tv_item_description.text = item.desc
        view.tv_item_label.text = "${item.superChapterName}·${item.chapterName}"

        view.iv_item_collect.setImageResource(if (item.collect) R.mipmap.ic_collect else R.mipmap.ic_uncollect)
        view.tv_item_title.maxLines = if (showPic) 1 else 3

        if (showPic){
            ImageLoader.loadImageRound(holder.itemView.context,item.envelopePic,view.iv_item_pic)
        }
        if (item.tags.isNotEmpty()){
            view.tv_item_tag.text = item.tags[0].name
            view.tv_item_tag.visibility = View.VISIBLE
        }
    }

}

/**
 * 首页广告轮播图adapter
 */

class BannerAdapter(private val context: LifecycleOwner,  layout: Int,
                    private val items: List<List<BannerBean>>
): BaseRvAdapter<List<BannerBean>>(layout,items){

    override fun onBind(holder: BaseViewHolder, position: Int) {

        val banner = holder.itemView.banner_home
        banner.adapter = ImageBannerAdapter(items[0])
        banner.addBannerLifecycleObserver(context).indicator = CircleIndicator(holder.itemView.context)


    }

   inner class ImageBannerAdapter(items: List<BannerBean>) : BannerImageAdapter<BannerBean>(items){
        override fun onBindView(
            holder: BannerImageHolder?,
            data: BannerBean?,
            position: Int,
            size: Int
        ) {
            ImageLoader.loadImageNoPlace(holder?.itemView!!.context,data!!.imagePath,holder.imageView)
        }

    }
}






