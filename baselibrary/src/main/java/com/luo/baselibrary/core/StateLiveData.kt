package com.luo.baselibrary.core

import androidx.lifecycle.MutableLiveData

/**
 *<pre>
 *  Created by jl_luo, 2019/9/24
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
class StateLiveData <T> : MutableLiveData<T>(){
    enum class State{
        Idle,Loading,Success,Error
    }
    private val state = MutableLiveData<State>()
    init {

        clearState()
    }

    private fun clearState() {
        state.postValue(State.Idle)
    }

    fun postValueAndSuccess(value: T) {
        super.postValue(value)
        postSuccess()
    }

    fun postLoading() {
        state.postValue(State.Loading)
    }

    fun postSuccess() {
        state.postValue(State.Success)
    }

    fun postError() {
        state.postValue(State.Error)
    }

    fun changeState(s: State) {
        state.postValue(s)
    }

}