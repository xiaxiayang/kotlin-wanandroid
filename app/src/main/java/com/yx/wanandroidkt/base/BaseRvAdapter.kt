package com.yx.wanandroidkt.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 *
 * 添加描述
 * Created by yx on 2020/9/2
 */
abstract class BaseRvAdapter<T>(layoutResId:Int,items:List<T>): RecyclerView.Adapter<BaseViewHolder>() {

    private var items = items
    private val layoutResId = layoutResId

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return  BaseViewHolder(LayoutInflater.from(parent.context).inflate(layoutResId,parent,false))
    }

    override fun getItemCount(): Int = if (items == null ) 0 else items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        onBind(holder,position)
    }

    abstract fun onBind(holder: BaseViewHolder, position: Int)
}