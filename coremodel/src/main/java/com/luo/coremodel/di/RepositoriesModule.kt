package com.luo.coremodel.di

import com.luo.coremodel.data.local.UserLocalDataSource
import com.luo.coremodel.data.remote.UserRemoteDataSource
import com.luo.coremodel.data.repository.UserRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 *<pre>
 *  Created by jl_luo, 2019/9/19
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
private const val GLOBAL_REPOS_MODULE_TAG = "PrefsModule"

val globalRepositoryModule = Kodein.Module(GLOBAL_REPOS_MODULE_TAG){


    bind<UserRemoteDataSource>() with singleton {
        UserRemoteDataSource(instance())
    }

    bind<UserLocalDataSource>() with singleton {
        UserLocalDataSource(instance())
    }

    bind<UserRepository>() with singleton {
        UserRepository(instance(),instance())
    }
}