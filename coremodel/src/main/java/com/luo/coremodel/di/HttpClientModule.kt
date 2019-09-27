package com.luo.coremodel.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.luo.coremodel.BuildConfig
import com.luo.coremodel.api.ApiConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 *<pre>
 *  Created by jl_luo, 2019/9/18
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
private const val HTTP_CLIENT_MODULE_TAG = "httpClientModule"
const val HTTP_CLIENT_MODULE_INTERCEPTOR_LOG_TAG = "http_client_module_interceptor_log_tag"
const val HTTP_CLIENT_MODULE_INTERCEPTOR_AUTH_TAG = "http_client_module_interceptor_auth_tag"

const val TIME_OUT_SECONDS = 10

val httpClientModule = Kodein.Module(HTTP_CLIENT_MODULE_TAG){

    bind<Gson>() with singleton {  GsonBuilder().create() }

    bind<Retrofit.Builder>() with provider { Retrofit.Builder() }

    bind<OkHttpClient.Builder>() with provider { OkHttpClient.Builder() }


//    bind<Interceptor>(HTTP_CLIENT_MODULE_INTERCEPTOR_AUTH_TAG) with singleton {
//        BasicAuthInterceptor(mUserInfoRepository = instance())
//    }

    bind<OkHttpClient>() with singleton {
        instance<OkHttpClient.Builder>()
            .connectTimeout(
                TIME_OUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .readTimeout(
                TIME_OUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = when(BuildConfig.DEBUG){
                        true -> HttpLoggingInterceptor.Level.BODY
                        false -> HttpLoggingInterceptor.Level.BASIC
                    }
                }
            )
            .build()
    }

    bind<Retrofit>() with singleton {
        instance<Retrofit.Builder>()
            .baseUrl(ApiConfig.baseURL)
            .client(instance()) // 委托给了 bind<OkHttpClient>() function
//            .addCallAdapterFactory(Corou)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}