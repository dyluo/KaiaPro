package com.luo.baselibrary.core

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 *<pre>
 *  Created by jl_luo, 2019/9/26
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
class SingleLiveEvent <T> : MutableLiveData<T>(){

    private val pending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if(hasActiveObservers()){

        }


        super.observe(owner, Observer<T>{ t->
            if(pending.compareAndSet(true,false)){
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    override fun setValue(value: T) {
        pending.set(true)
        super.setValue(value)
    }



}