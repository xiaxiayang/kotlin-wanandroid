package com.yx.wanandroidkt.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yx.wanandroidkt.Test
import com.yx.wanandroidkt.api.ApiService
import com.yx.wanandroidkt.http.HttpClient

/**
 *
 * 添加描述
 * Created by yx on 2020/8/31
 */
class HomeVM: ViewModel() {

    companion object {
        private const val TAG = "HomeVM"
    }

    private var service :ApiService = HttpClient.reqApi

    private val users: MutableLiveData<Test> by lazy {
       MutableLiveData<Test>().also {
           getHomeData()
       }
    }

   fun getDatas(): LiveData<Test>{
       return users
   }

    private fun getHomeData(){
        HttpClient.enqueue(service.listRepos("xiaxiayang"),
            object : HttpClient.IResultListener<Test>{
            override fun onSuccess(data: Test?) {
                users.value = data
            }

            override fun onError(message: String?) {
                var test = Test()
                test.avatar_url = message
                users.value = test
            }

        })

    }
}