package com.yx.wanandroidkt.constans

/**
 *
 * 添加描述
 * Created by yx on 2020/8/31
 */
class ApiConst {
    companion object{
        const val  BASE_URL = "https://www.wanandroid.com/"
        const val CONNECT_TIME_OUT_SECONDS = 20L
        const val READ_TIME_OUT_SECONDS = 20L
        const val WHITE_TIME_OUT_SECONDS = 20L


        //todo 用于复用页面时判断调用哪个api方法的，目前未想到优化方法，后期优化
        const val API_CODE = "apiCode"
        const val API_USER_ARTICLE_LIST = "user_article/list" //广场
        const val API_WX_ARTICLE_LIST = "wxarticle/list" //公众号

    }
}