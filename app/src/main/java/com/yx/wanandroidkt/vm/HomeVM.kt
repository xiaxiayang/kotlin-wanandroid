package com.yx.wanandroidkt.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yx.wanandroidkt.Test
import com.yx.wanandroidkt.http.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *
 * 添加描述
 * Created by yx on 2020/8/31
 */
class HomeVM: ViewModel() {

    companion object {
        private const val TAG = "HomeVM"
    }

    private val users: MutableLiveData<Test> by lazy {
       MutableLiveData<Test>().also {
           getHomeData()
       }
    }

   fun getDatas(): LiveData<Test>{
       return users
   }

    private fun getHomeData(){
        RetrofitHelper.reqApi!!.listRepos("xiaxiayang").enqueue(object: Callback<Test>{
            override fun onFailure(call: Call<Test>, t: Throwable) {
                Log.d(TAG, "onFailure: ")
            }

            override fun onResponse(call: Call<Test>, response: Response<Test>) {
                Log.d(TAG, "onResponse: ")
                users.value = response.body()
            }

        })


    }
}