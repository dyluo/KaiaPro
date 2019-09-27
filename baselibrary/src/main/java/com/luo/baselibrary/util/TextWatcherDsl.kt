package com.luo.baselibrary.util

import android.text.Editable
import android.text.TextWatcher

/**
 *<pre>
 *  Created by jl_luo, 2019/9/24
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
class TextWatcherDsl :TextWatcher{

    private var afterTextChanged:((s:Editable?)->Unit)? = null
    private var beforeTextChanged:((s: CharSequence?, start: Int, count: Int, after: Int) -> Unit)?=null
    private var onTextChanged:((s: CharSequence?, start: Int, before: Int, count: Int) -> Unit)?=null


    override fun afterTextChanged(p0: Editable?) {
        afterTextChanged?.invoke(p0)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        beforeTextChanged?.invoke(p0,p1,p2,p3)
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        onTextChanged?.invoke(p0,p1,p2,p3)
    }

    fun afterTextChanged(after:(s:Editable?)->Unit){
        afterTextChanged = after
    }

    fun beforeTextChanged(before: (s: CharSequence?, start: Int, count: Int, after: Int) -> Unit) {
        beforeTextChanged = before
    }

    fun onTextChanged(onChanged: (s: CharSequence?, start: Int, before: Int, count: Int) -> Unit) {
        onTextChanged = onChanged
    }


}