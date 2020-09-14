package com.yx.wanandroidkt.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *
 * 添加描述
 * Created by yx on 2020/9/11
 */
open class BaseViewModel: ViewModel() {

    fun <T> launchOnUI(block: suspend CoroutineScope.() ->T,after:()->Unit) {
        viewModelScope.launch(Dispatchers.Main){
            withContext(Dispatchers.IO){
                block()
            }
            after()
        }
    }
}