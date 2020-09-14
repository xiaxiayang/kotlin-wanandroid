package com.yx.wanandroidkt.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yx.wanandroidkt.api.ApiService
import com.yx.wanandroidkt.http.HttpClient
import com.yx.wanandroidkt.viewmodel.bean.BaseResponse
import com.yx.wanandroidkt.viewmodel.bean.WxChapterBean

/**
 *
 * 添加描述
 * Created by yx on 2020/8/31
 */
class AccountFragmentVM: ViewModel() {

    companion object {
        private const val TAG = "AccountFragmentVM"
    }

    private var service :ApiService = HttpClient.reqApi

    private val chapters: MutableLiveData<List<WxChapterBean>> by lazy {
        MutableLiveData<List<WxChapterBean>>().also {
            requestChapters()
        }
    }

    fun getChapters(): LiveData<List<WxChapterBean>>{
        return chapters
    }
    private fun requestChapters(){
        HttpClient.cmEnqueue(service.getWxChapters(),
            object : HttpClient.IResultListener<BaseResponse<List<WxChapterBean>>>{
                override fun onSuccess(response: BaseResponse<List<WxChapterBean>>?) {
                    if (!response?.data.isNullOrEmpty()){
                        chapters.value = response?.data
                    }
                }

                override fun onError(message: String?) {
                    Log.d(TAG, "onError: getBanner$message ")
                }

            })

    }

}