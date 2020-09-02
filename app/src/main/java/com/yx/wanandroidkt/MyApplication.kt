package com.yx.wanandroidkt

import android.app.Application
import kotlin.properties.Delegates

/**
 *
 * 添加描述
 * Created by yx on 2020/9/2
 */
class MyApplication: Application() {
    companion object{
        private var instance: MyApplication by Delegates.notNull()
        fun instance() = instance
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}