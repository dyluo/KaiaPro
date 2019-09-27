package com.luo.baselibrary.util

import com.luo.baselibrary.base.ApiResponse
import com.luo.baselibrary.base.ErrorResponse
import retrofit2.Call

/**
 *<pre>
 *  Created by jl_luo, 2019/9/24
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
class RetrofitCoroutineDsl<T>{



    var api:(Call<ApiResponse<Any>>)?=null
    internal var onSuccess: ((T?) -> Unit)? = null
        private set
    internal var onComplete: (() -> Unit)? = null
        private set
    internal var onFailed: ((error: ErrorResponse) -> Unit)? = null
        private set
    internal var onError: ((error: ErrorResponse) -> Unit)? = null
        private set


    internal fun clean() {
        onSuccess = null
        onComplete = null
        onFailed = null
        onError = null
    }

    fun onSuccess(block: (T?) -> Unit) {
        this.onSuccess = block
    }

    fun onComplete(block: () -> Unit) {
        this.onComplete = block
    }

    fun onFailed(block: (error: ErrorResponse) -> Unit) {
        this.onFailed = block
    }

    fun onError(block: (error: ErrorResponse) -> Unit) {
        this.onFailed = block
    }
}