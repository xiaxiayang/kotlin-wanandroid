package com.yx.wanandroidkt.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 *
 * 添加描述
 * Created by yx on 2020/8/31
 */
abstract class BaseActivity: AppCompatActivity() {

    abstract fun getLayoutId():Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
        setToolbarLeftClick()
        registerListener()
        requestData()
    }

    /**
     * 初始化 view
     */
    abstract fun initView()

    /**
     * 请求数据
     */
    abstract fun requestData()

    /**
     * 注册点击等事件监听
     */
    abstract fun registerListener()


    fun setToolbarLeftImageVisibility(show: Boolean){
        if (tv_title !=null){
            iv_back.visibility = if (show) View.VISIBLE else View.GONE
        }
    }

   private fun setToolbarLeftClick(){
        if (layout_toolbar != null && iv_back.isShown){
            layout_toolbar.setOnClickListener {
                finish()
            }
        }
    }



}