package com.luo.coremodel.data.contract

import com.luo.coremodel.data.ILocalDataSource

/**
 *<pre>
 *  Created by jl_luo, 2019/9/18
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
interface UserDataSource : ILocalDataSource{
    fun saveUser()
}