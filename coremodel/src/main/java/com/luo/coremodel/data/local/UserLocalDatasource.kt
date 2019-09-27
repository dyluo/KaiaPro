package com.luo.coremodel.data.local

import android.content.SharedPreferences
import com.luo.coremodel.data.contract.UserDataSource

/**
 *<pre>
 *  Created by jl_luo, 2019/9/18
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
open class UserLocalDataSource(val prefs: SharedPreferences):UserDataSource{

    override fun saveUser(){

    }
}