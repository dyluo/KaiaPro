package com.luo.baselibrary.base


/**
 *<pre>
 *  Created by jl_luo, 2019/9/17
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
open class ApiResponse<out T>(val errorCode: Int, val errorMsg: String, val data: T){

//    val mStatus:Int = 0
//    val mCode:Int
//    val mMsg:String
//    val mT : T

    fun isSuccess():Boolean{
        return  errorCode != -1
    }

    fun isError():Boolean{
        return errorCode == -1
    }

    fun isFail():Boolean{
        return false
    }

}