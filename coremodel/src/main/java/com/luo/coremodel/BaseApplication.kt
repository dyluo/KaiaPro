package com.luo.coremodel

import android.app.Application
import android.content.Context
import com.luo.coremodel.di.globalRepositoryModule
import com.luo.coremodel.di.httpClientModule
import com.luo.coremodel.di.serviceModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

/**
 *<pre>
 *  Created by jl_luo, 2019/9/18
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
open class BaseApplication : Application(),KodeinAware{
    override val kodein: Kodein = Kodein.lazy {
        bind<Context>()with singleton { this@BaseApplication }
        import(androidModule(this@BaseApplication))
        import(androidXModule(this@BaseApplication))
        import(serviceModule)
        import(httpClientModule)
        import(globalRepositoryModule)
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: BaseApplication
    }
}