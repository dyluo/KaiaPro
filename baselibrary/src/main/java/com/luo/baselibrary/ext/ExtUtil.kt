package com.luo.baselibrary.ext

import android.text.TextWatcher
import android.widget.EditText
import com.luo.baselibrary.util.TextWatcherDsl

/**
 *<pre>
 *  Created by jl_luo, 2019/9/24
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */

/**
 * 使用：
 * editText.onTextChange {
 *   afterTextChanged { s ->
 *
 *       }
 *   }
 */
inline fun EditText.onTextchange(textWatcher:TextWatcherDsl.()->Unit):TextWatcher{
    val watcher = TextWatcherDsl().apply(textWatcher)
    addTextChangedListener(watcher)
    return watcher
}