package com.luo.coremodel.data.repository

import com.luo.baselibrary.base.ApiResponse
import com.luo.coremodel.api.ApiServices
import com.luo.coremodel.data.BaseRepository
import com.luo.coremodel.data.local.UserLocalDataSource
import com.luo.coremodel.data.remote.UserRemoteDataSource
import com.luo.coremodel.vo.ArticleList

/**
 *<pre>
 *  Created by jl_luo, 2019/9/19
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
open class CommonRepository(val apiServices: ApiServices
){

    suspend fun testFun():ApiResponse<ArticleList> {
        return apiServices.getHomeArticles(1)
    }


    companion object
}