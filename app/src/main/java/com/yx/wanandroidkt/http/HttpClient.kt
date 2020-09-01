package com.yx.wanandroidkt.http

import com.google.gson.Gson
import com.orhanobut.logger.Logger
import com.yx.wanandroidkt.BuildConfig
import com.yx.wanandroidkt.api.ApiService
import com.yx.wanandroidkt.constans.ApiConst
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

/**
 *
 * retrofit 封装类
 * Created by yx on 2020/8/31
 */
object HttpClient {

    private const val TAG = "RetrofitClient"

    private var retrofit: Retrofit? = null
    private var interceptor: Interceptor? = null
    private var networkInterceptor: Interceptor? = null

    interface IResultListener<T>{
        fun onSuccess(data: T?)
        fun onError(message:String?)
    }



    private fun getRetrofit(): Retrofit?{
        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(ApiConst.BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return  retrofit
    }

    val reqApi: ApiService by lazy {
        return@lazy getRetrofit()!!.create(ApiService::class.java)
    }


    fun <T> enqueue(call: Call<T>,listener:IResultListener<T>){

        call.enqueue(object :retrofit2.Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                Logger.d(TAG,t.message)
                listener.onError(t.message)
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                Logger.d(TAG,response.body())
                when {
                    response.code() != 200 ->  listener.onError("${response.code()}")

                    response.body() == null -> listener.onError("结果为空")

                    else -> listener.onSuccess(response.body())
                }
            }

        })

    }


    private fun getOkHttpClient(): OkHttpClient?{

        var builder = OkHttpClient.Builder()

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG){
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }else{
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        builder.addInterceptor(httpLoggingInterceptor)

        if (interceptor !=null){
            builder.addInterceptor(interceptor)
        }
        if (networkInterceptor != null){
            builder.addNetworkInterceptor(networkInterceptor)
        }

        builder.connectTimeout(ApiConst.CONNECT_TIME_OUT_SECONDS,TimeUnit.SECONDS)
            .readTimeout(ApiConst.READ_TIME_OUT_SECONDS,TimeUnit.SECONDS)
            .writeTimeout(ApiConst.WHITE_TIME_OUT_SECONDS,TimeUnit.SECONDS)

        return  builder.build()

    }
}