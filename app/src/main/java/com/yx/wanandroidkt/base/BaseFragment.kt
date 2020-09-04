package com.yx.wanandroidkt.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 *
 * 添加描述
 * Created by yx on 2020/9/1
 */
abstract class BaseFragment: Fragment() {

    abstract fun getLayoutId(): Int
    private var mView:View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = layoutInflater.inflate(getLayoutId(),null)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setToolbarLeftClick()
        registerListener()
        requestData()
    }

    abstract fun initView()
    abstract fun registerListener()
    abstract fun requestData()

    fun setToolbarLeftImageVisibility(show: Boolean){
        if (tv_title !=null){
            iv_back.visibility = if (show) View.VISIBLE else View.GONE
        }
    }

    private fun setToolbarLeftClick(){
        if (layout_toolbar != null){
            layout_toolbar.setOnClickListener {
               activity?.finish()
            }
        }
    }

    fun setTitle(title: String){
        if (tv_title !=null){
            tv_title.text = title
        }

    }
}