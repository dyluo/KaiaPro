package com.luo.coremodel.api.response

import com.luo.baselibrary.base.ApiResponse
import com.luo.coremodel.vo.PageInfo

/**
 *<pre>
 *  Created by jl_luo, 2019/9/17
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>r
 */
class PageResponse<T>(errorCode: Int,  errorMsg: String,  data: T, val pageInfo: PageInfo)
    : ApiResponse<T>(errorCode,errorMsg,data){

}