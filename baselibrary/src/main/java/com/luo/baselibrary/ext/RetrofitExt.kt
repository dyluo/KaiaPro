package com.luo.baselibrary.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luo.baselibrary.base.ApiResponse
import com.luo.baselibrary.base.ErrorResponse
import com.luo.baselibrary.util.RetrofitCoroutineDsl
import kotlinx.coroutines.*
import retrofit2.Response

/**
 *<pre>
 *  Created by jl_luo, 2019/9/24
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */


/**
 *
 * retrofit call类型
 */
 fun  ViewModel.retrofit(
    dsl: RetrofitCoroutineDsl<ApiResponse<Any>>.() -> Unit){
    viewModelScope.launch(Dispatchers.Main) {
        val retrofitCoroutineDsl = RetrofitCoroutineDsl<ApiResponse<Any>>()
        retrofitCoroutineDsl.dsl()
         retrofitCoroutineDsl.api?.let {  it -> {
            val work = async(Dispatchers.IO) {
                try {
                    it. execute()
                }catch (e:CancellationException){
//                    retrofitCoroutineDsl.onFailed?.invoke(ErrorResponse.adapt(e))
                    null
                }catch (e:Throwable){
                    retrofitCoroutineDsl.onFailed?.invoke(ErrorResponse.adapt(e))
                    null
                }
            }

            work.invokeOnCompletion { _ ->
                if(work.isCancelled){
                    it.cancel()
                    retrofitCoroutineDsl.clean()
                }
            }
             var response:Response<ApiResponse<Any>>?=null
             this.launch {
                 response = work.await()
             }


             retrofitCoroutineDsl.onComplete?.invoke()
             response?.body()?.let {
                 if (it.isSuccess()) {
                     retrofitCoroutineDsl.onSuccess?.invoke(it)
                 } else {
                     // 处理 HTTP code
                     val response1 =  ApiResponse(100,"","")
                     retrofitCoroutineDsl.onFailed?.invoke(ErrorResponse.adapt(response1))
                 }
             }

         }

        }
    }
}