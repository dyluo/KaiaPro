package com.luo.kaiabase

import com.luo.coremodel.BaseApplication
import com.luo.coremodel.api.ApiConfig

/**
 *<pre>
 *  Created by jl_luo, 2019/9/18
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
class KaiaApplication : BaseApplication(){
    init {
        ApiConfig.env = BuildConfig.env
    }

//    override val kodein: Kodein = Kodein.lazy {
//        super.kodein
//
//    }

    override fun onCreate() {
        super.onCreate()

    }
}