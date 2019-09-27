package com.luo.commonbusiness.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import org.kodein.di.Copy
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.android.retainedKodein
import org.kodein.di.generic.kcontext

/**
 *<pre>
 *  Created by jl_luo, 2019/9/19
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
abstract class BaseActivity :AppCompatActivity(), KodeinAware,CoroutineScope by MainScope() {
    protected val parentKodein by closestKodein()

    override val kodeinContext = kcontext<AppCompatActivity>(this)

    override val kodein: Kodein by retainedKodein {
        extend(parentKodein, copy = Copy.All)
        //add import
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
    }

    abstract fun getLayoutResId(): Int


    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}