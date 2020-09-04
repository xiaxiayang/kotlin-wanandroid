package com.yx.wanandroidkt.loader

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.yx.wanandroidkt.R
import com.yx.wanandroidkt.constans.Constans

/**
 *
 * 添加描述
 * Created by yx on 2020/9/3
 */
object ImageLoader {
    fun loadImage(context: Context,url:String,image:ImageView){
        Glide.with(context)
            .load(url)
            .centerCrop()
            .placeholder(R.mipmap.ic_loading)
            .error(R.mipmap.ic_loading_fail)
            .into(image);
    }

    fun loadImageNoPlace(context: Context,url:String,image:ImageView){
        Glide.with(context)
            .load(url)
            .centerCrop()
            .into(image);
    }

    fun loadImageRound(context: Context,url:String,image:ImageView){

        Glide.with(context)
            .load(url)
            .placeholder(R.mipmap.ic_loading)
            .error(R.mipmap.ic_loading_fail)
            .transform(CenterCrop(),RoundedCorners(Constans.ROUND_CORNER))
            .into(image);
    }
}