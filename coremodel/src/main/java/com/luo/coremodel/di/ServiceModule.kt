package com.luo.coremodel.di

import android.content.Context
import android.content.SharedPreferences
import com.luo.coremodel.BaseApplication
import com.luo.coremodel.api.ApiServices
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

/**
 *<pre>
 *  Created by jl_luo, 2019/9/18
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
const val SERVICE_MODULE_TAG = "serviceModule"
private const val DEFAULT_SP_TAG = "PrefsDefault"

val serviceModule = Kodein.Module(SERVICE_MODULE_TAG){

    bind<ApiServices>() with singleton {
        instance<Retrofit>().create(ApiServices::class.java)
    }

    bind<SharedPreferences>(DEFAULT_SP_TAG) with singleton {
         BaseApplication.INSTANCE.getSharedPreferences(DEFAULT_SP_TAG, Context.MODE_PRIVATE)
    }


}