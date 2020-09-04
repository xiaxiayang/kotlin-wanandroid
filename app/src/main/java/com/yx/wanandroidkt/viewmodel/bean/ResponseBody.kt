package com.yx.wanandroidkt.viewmodel.bean

/**
 *
 * 通用返回结果
 * Created by yx on 2020/9/2
 */

data class BaseResponse<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
)

data class BaseData<T>(
    val curPage: Int,
    var datas: MutableList<T>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)
