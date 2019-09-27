package com.luo.coremodel.vo

import java.io.Serializable

/**
 *<pre>
 *  Created by jl_luo, 2019/9/17
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
data class ArticleList( val offset: Int,
                        val size: Int,
                        val total: Int,
                        val pageCount: Int,
                        val curPage: Int,
                        val over: Boolean,
                        val datas: List<Article>):Serializable