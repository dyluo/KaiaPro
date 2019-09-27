package com.luo.coremodel.api

import com.luo.baselibrary.base.ApiResponse
import com.luo.coremodel.vo.ArticleList
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *<pre>
 *  Created by jl_luo, 2019/9/17
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
interface ApiServices {
    @GET("/article/list/{page}/json")
    suspend fun getHomeArticles(@Path("page") page: Int): ApiResponse<ArticleList>
}