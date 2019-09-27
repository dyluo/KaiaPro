package com.luo.baselibrary.base

import com.luo.baselibrary.base.ApiResponse as ApiResponse

/**
 *<pre>
 *  Created by jl_luo, 2019/9/23
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
class KaiaError {
    companion object{
        const val ERROR_SERVER:String = "服务器异常"
        const val ERROR_NET:String = "网络异常，请稍后在试"
    }



}

enum class ErrorType{
    NETWORK_ERROR, //网络出错
    SERVICE_ERROR, //服务器访问异常
    RESPONSE_ERROR //请求返回值异常

}

data class ErrorResponse(val errorType: ErrorType,
                         val errorTag:String,
                         val errorCode:String?,
                         val message:String?){

    companion object{
        fun adapt(throwable:Throwable):ErrorResponse{

            return ErrorResponse(ErrorType.NETWORK_ERROR,"","",throwable.localizedMessage)
        }

        fun adapt(apiResponse: ApiResponse<Any>):ErrorResponse{
            return ErrorResponse(ErrorType.RESPONSE_ERROR,"","",apiResponse.errorMsg)
        }
    }


}