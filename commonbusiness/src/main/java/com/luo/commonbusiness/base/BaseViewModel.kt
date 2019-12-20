package com.luo.commonbusiness.base

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.luo.coremodel.BaseApplication
import com.luo.baselibrary.base.ApiResponse
import com.luo.baselibrary.base.ErrorResponse
import com.luo.baselibrary.base.KaiaError
import com.luo.coremodel.api.ApiServices
import com.luo.coremodel.di.serviceModule
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 *<pre>
 *  Created by jl_luo, 2019/9/19
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
open class BaseViewModel : AndroidViewModel(BaseApplication.INSTANCE),LifecycleObserver{
    internal val mActionLiveData: MutableLiveData<ViewModelAction> = MutableLiveData()

   fun <T> executeRequest(request: suspend () -> T?,
                          onSuccess: (T) -> Unit = {},
                          onFail: (ErrorResponse) -> Unit = {}){
       viewModelScope.launch {
           try {

               Log.e("TT", "---------$onFail")
               val res: T? = withContext(Dispatchers.IO) { request() }
               res?.let {
                   onSuccess(it)
               }

           } catch (e: CancellationException){
               Log.e("executeRequest", "job cancelled")
           } catch (e: Exception) {
               e.printStackTrace()
               onFail(ErrorResponse.adapt(e))
           }
       }
//       return null
   }

    fun launch(tryBlock: suspend CoroutineScope.() -> Unit) {
        launchOnUI {
            tryCatch(tryBlock, null, null)
        }
    }

    fun launch(tryBlock: suspend CoroutineScope.() -> Unit,
               catchBlock: (suspend CoroutineScope.(Throwable) -> Unit)?,
               finallyBlock: (suspend CoroutineScope.() -> Unit)?) {
        launchOnUI {
            tryCatch(tryBlock, catchBlock, finallyBlock)
        }
    }

    private fun launchOnUI(block:suspend CoroutineScope.() -> Unit){
        viewModelScope.launch { block() }
    }

    private suspend fun tryCatch(
        tryBlock: suspend CoroutineScope.() -> Unit,
        catchBlock: (suspend CoroutineScope.(Throwable) -> Unit)?,
        finallyBlock: (suspend CoroutineScope.() -> Unit)?) {
        coroutineScope {
            try {
                tryBlock()

            }catch (e: CancellationException){
                Log.e("executeRequest", "job cancelled")
            }
            catch (e: Throwable) {
                if(catchBlock !=null){
                    catchBlock(e)
                }else{
                    commonErrorCatch(e)
                }
//                if(handleCancellationExceptionManually){
////                    mErrorResult.value = e
//                    commonErrorCatch(e,displayCommon)
//                    catchBlock(e)
//                }else{
//                    Log.e( BaseViewModel::class.java.simpleName,e.localizedMessage?:"")
//                }
            } finally {
                finallyBlock?.let { it() }
            }
        }
    }

    /**
     * catch error处理，一般网络错误等，
     */
    private suspend fun commonErrorCatch(e: Throwable) {
        //调用Error类进行封装 ErrorResponse
        e.printStackTrace()
        withContext(Dispatchers.Main) {
            Toast.makeText(
                getApplication(), KaiaError.ERROR_NET,
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    /**
     * api  结果数据处理
     */
    suspend fun executeResponse(response: ApiResponse<Any>,
                                successBlock: () -> Unit,
                                errorBlock: () -> Unit) {
        this.executeResponse(response, successBlock, errorBlock,errorBlock)
    }

    suspend fun executeResponse(response: ApiResponse<Any>,
                                        successBlock: () -> Unit,
                                errorBlock: () -> Unit,
                                failBlock: () -> Unit) {
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

    fun showLoading(){
        mActionLiveData.postValue(ViewModelAction(ViewModelAction.SHOW_LOADING))
    }
    fun hiddenLoading(){
        mActionLiveData.postValue(ViewModelAction(ViewModelAction.DISMISS_LOADING))
    }
    fun showToast(content:String){
        mActionLiveData.postValue(ViewModelAction(ViewModelAction.SHOW_TOAST,content))
    }

}