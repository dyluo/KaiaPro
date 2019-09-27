package com.luo.commonbusiness.base

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.luo.coremodel.BaseApplication
import com.luo.baselibrary.base.ApiResponse
import com.luo.baselibrary.base.KaiaError
import kotlinx.coroutines.*

/**
 *<pre>
 *  Created by jl_luo, 2019/9/19
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
open class BaseViewModel : AndroidViewModel(BaseApplication.INSTANCE),LifecycleObserver{
//    private val mErrorResult: MutableLiveData<Throwable> = MutableLiveData()

    private fun launchOnUI(block:suspend CoroutineScope.() -> Unit){
        viewModelScope.launch { block() }
    }

    fun launch(tryBlock: suspend CoroutineScope.() -> Unit) {
        launchOnUI {
            tryCatch(tryBlock, {}, {}, false, displayCommon = true)
        }
    }

    fun launch(tryBlock: suspend CoroutineScope.() -> Unit,
               catchBlock: suspend CoroutineScope.(Throwable) -> Unit,
               displayCommon:Boolean = true) {
        launchOnUI {
            tryCatch(tryBlock, catchBlock, {}, true,displayCommon)
        }
    }

    private suspend fun tryCatch(
        tryBlock: suspend CoroutineScope.() -> Unit,
        catchBlock: suspend CoroutineScope.(Throwable) -> Unit,
        finallyBlock: suspend CoroutineScope.() -> Unit,
        handleCancellationExceptionManually: Boolean = false,displayCommon: Boolean) {
        coroutineScope {
            try {
                tryBlock()

            }catch (e: CancellationException){
                Log.e("executeRequest", "job cancelled")
            }
            catch (e: Throwable) {
                if(handleCancellationExceptionManually){

//                    mErrorResult.value = e
                    commonErrorCatch(e,displayCommon)
                    catchBlock(e)
                }else{
                    Log.e( BaseViewModel::class.java.simpleName,e.localizedMessage?:"")
                }
            } finally {
                finallyBlock()
            }
        }
    }

    /**
     * catch error处理，一般网络错误等，
     */
    private suspend fun commonErrorCatch(e: Throwable,displayCommon: Boolean) {
        //调用Error类进行封装 ErrorResponse
        if(displayCommon) {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    getApplication(), KaiaError.ERROR_NET,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }


    /**
     * api  结果数据处理
     */
    suspend fun executeResponse(response: ApiResponse<Any>, successBlock: suspend CoroutineScope.() -> Unit,
                                errorBlock: suspend CoroutineScope.() -> Unit) {
        this.executeResponse(response, successBlock, errorBlock,errorBlock)
    }

    suspend fun executeResponse(response: ApiResponse<Any>, successBlock: suspend CoroutineScope.() -> Unit,
                                errorBlock: suspend CoroutineScope.() -> Unit,
                                failBlock: suspend CoroutineScope.() -> Unit) {
        coroutineScope {
            when {
                response.isSuccess() -> successBlock()
                response.isError() -> errorBlock()
                else -> failBlock()
            }
        }
    }

    suspend fun <T> launchOnIO(block: suspend CoroutineScope.() -> Unit){
        withContext(Dispatchers.IO){
            block
        }
    }






//    fun launchOnUITryCatch(tryBlock: suspend CoroutineScope.() -> Unit,
//                           catchBlock: suspend CoroutineScope.(Throwable) -> Unit,
//                           finallyBlock: suspend CoroutineScope.() -> Unit,
//                           handleCancellationExceptionManually: Boolean
//    ) {
//        launchOnUI {
//            tryCatch(tryBlock, catchBlock, finallyBlock, handleCancellationExceptionManually)
//        }
//    }
//
//    fun launchOnUITryCatch(tryBlock: suspend CoroutineScope.() -> Unit,
//                           handleCancellationExceptionManually: Boolean = false
//    ) {
//        launchOnUI {
//            tryCatch(tryBlock, {}, {}, handleCancellationExceptionManually)
//        }
//    }


    class SingleLiveData<T> : MutableLiveData<T>() {
        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
            super.observe(owner, Observer {
                if(it != null){
                    observer.onChanged(it)
                    postValue(null)
                }
            })
        }
    }


}