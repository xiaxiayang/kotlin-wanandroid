package com.yx.wanandroidkt.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yx.wanandroidkt.R
import kotlinx.android.synthetic.main.layout_state_load.view.*

/**
 *
 * 使用 paging 组件的 rv 的 adapter 基类
 * diffCallback : 处理paging差分处理数据 ,由子类实现判断逻辑
 * Created by yx on 2020/9/4
 */
abstract class BasePagerAdapter<T : Any>(layoutResId: Int,diffCallback: DiffUtil.ItemCallback<T>)
    : PagingDataAdapter<T, BaseViewHolder>(diffCallback) {

    private  val layoutId = layoutResId

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        onBind(holder,position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return  BaseViewHolder(LayoutInflater.from(parent.context).inflate(layoutId,parent,false))
    }

    abstract fun onBind(holder: BaseViewHolder, position: Int)


}

/**
 * 加载更多布局
 * retryListener 点击重试的回调
 */
class LoadMoreAdapter(retryListener:Listener): LoadStateAdapter<BaseViewHolder>(){

    private var listener = retryListener

    interface Listener{
        fun  retry(loadState: LoadState)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, loadState: LoadState) {

        holder.itemView.setOnClickListener(View.OnClickListener {
            if (loadState is LoadState.Error){
                listener.retry(loadState)
            }

        })

        when(loadState){
            is LoadState.Loading->{
                holder.itemView.tv_state_load.visibility = View.GONE
                holder.itemView.progressbar.visibility = View.VISIBLE
            }
            is LoadState.Error->{
                holder.itemView.tv_state_load.text = holder.itemView.context.getString(R.string.footer_error)
                holder.itemView.progressbar.visibility = View.GONE
                holder.itemView.tv_state_load.visibility = View.VISIBLE
            }
            is LoadState.NotLoading ->{
                holder.itemView.tv_state_load.text = holder.itemView.context.getString(R.string.footer_done)
                holder.itemView.progressbar.visibility = View.GONE
                holder.itemView.tv_state_load.visibility = View.VISIBLE
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): BaseViewHolder {
        return  BaseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_state_load,parent,false))

    }

}