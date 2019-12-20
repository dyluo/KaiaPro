package com.luo.commonbusiness.base

/**
 *<pre>
 *  Created by jl_luo, 2019/12/19
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
class ViewModelAction(val code:Int=-1,val any:Any?){
    constructor(code:Int):this(code,null)
    companion object {
        val SHOW_LOADING = 1 //loading

        val DISMISS_LOADING = 2 //loading

        val SHOW_TOAST = 3  //toast

        val ADD_DISPOSABLE = 4  //rxjava添加disposable

        val SHOW_ERROR_TOAST = 5  //处理异常的tag

    }


}