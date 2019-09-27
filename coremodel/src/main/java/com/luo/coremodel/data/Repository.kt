package com.luo.coremodel.data

import com.luo.baselibrary.base.ApiResponse

/**
 *<pre>
 *  Created by jl_luo, 2019/9/18
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
open class BaseRepository<R:IRemoteDataSource,L:ILocalDataSource>(
    val remoteDataSource:R,val localDataSource:L
):IRepository{
    suspend fun <T : Any> apiCall(call: suspend () -> ApiResponse<T>): ApiResponse<T> {
        return call.invoke()
    }

}

open class BaseRepositoryLocal<T:ILocalDataSource>(
    val localDatasource:T
):IRepository

open class BaseRepositoryRemote<T:IRemoteDataSource>(
    val remoteDatasource:T
):IRepository


open class BaseRepositoryNothing():IRepository