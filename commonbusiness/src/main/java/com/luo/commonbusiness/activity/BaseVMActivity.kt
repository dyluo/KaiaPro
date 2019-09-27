package com.luo.commonbusiness.activity

import android.os.Bundle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.ViewModelProviders
import com.luo.commonbusiness.base.BaseViewModel

/**
 *<pre>
 *  Created by jl_luo, 2019/9/20
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
abstract class BaseVMActivity <VM:BaseViewModel>:BaseActivity(),LifecycleObserver{
    lateinit var mViewModel:VM
    lateinit var factory: Factory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVM()
        startObserve()
    }

    open fun providerVMClass():Class<VM>? = null
    open fun providerViewModelFactory() : Factory?=null

    open fun startObserve() {

    }

    private fun initVM() {
        providerVMClass()?.let {
            mViewModel = ViewModelProviders.of(this,providerViewModelFactory()).get(it)
            mViewModel.let(lifecycle::addObserver)
        }
    }

    open fun onError(e:Throwable) {}

    override fun onDestroy() {
        mViewModel.let {
            lifecycle.removeObserver(it)
        }
        super.onDestroy()

    }


}