package com.luo.coremodel.data.remote

import com.luo.baselibrary.base.ApiResponse
import com.luo.coremodel.api.ApiServices
import com.luo.coremodel.data.IRemoteDataSource
import com.luo.coremodel.vo.ArticleList

/**
 * <pre>
 * Created by jl_luo, 2019/9/19
 * luo.junliang5811@Gmail.com
 * desc:
</pre> *
 */
open class UserRemoteDataSource(val apiServices: ApiServices):IRemoteDataSource{

    suspend fun loginTest(): ApiResponse<ArticleList> {
        return this.apiServices.getHomeArticles(1)
    }
}
